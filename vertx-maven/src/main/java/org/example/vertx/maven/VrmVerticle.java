package org.example.vertx.maven;

import io.vertx.rxcore.RxSupport;
import io.vertx.rxcore.java.eventbus.RxEventBus;
import io.vertx.rxcore.java.eventbus.RxMessage;
import org.vertx.java.core.Handler;
import org.vertx.java.core.MultiMap;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Vert.x + RxJava + MongoDB
 * <p/>
 * Created by mrseasons on 1/28/15.
 */
public class VrmVerticle extends Verticle {

    @Override
    public void start() {

        // load the general config object, loaded by using -config on command line
        JsonObject appConfig = container.config();

        // deploy the mongo-persistor module, which we'll use for persistence
        container.deployModule("io.vertx~mod-mongo-persistor~2.1.1", appConfig.getObject("mongo-persistor"));

        final RxEventBus rxEventBus = new RxEventBus(vertx.eventBus());
        RouteMatcher matcher = new RouteMatcher();

        // the matcher for the complete list and the search
        matcher.get("/zips", new Handler<HttpServerRequest>() {
            public void handle(final HttpServerRequest req) {

                System.out.println(1212353);

                JsonObject json = new JsonObject();
                MultiMap params = req.params();

                if (params.size() > 0 && params.contains("state") || params.contains("city")) {
                    // create the matcher configuration
                    JsonObject matcher = new JsonObject();
                    if (params.contains("state")) matcher.putString("state", params.get("state"));
                    if (params.contains("city")) matcher.putString("city", params.get("city"));

                    // create the message for the mongo-persistor verticle
                    json = new JsonObject().putString("collection", "zips")
                            .putString("action", "find")
                            .putObject("matcher", matcher);

                } else {
                    // create the query
                    json = new JsonObject().putString("collection", "zips")
                            .putString("action", "find")
                            .putObject("matcher", new JsonObject());
                }

                JsonObject data = new JsonObject();
                data.putArray("results", new JsonArray());
                // and call the event we want to use
                vertx.eventBus().send("mongodb-persistor", json, new ReplyHandler(req, data));
            }
        });

        matcher.post("/zips/:id", new Handler<HttpServerRequest>() {
            public void handle(final HttpServerRequest req) {

                // process the body
                req.bodyHandler(new Handler<Buffer>() {

                    @Override
                    public void handle(Buffer event) {
                        // normally we'd validate the input, for now just assume it is correct.
                        final String body = event.getString(0, event.length());

                        System.out.println(24242);

                        // create the query
                        JsonObject newObject = new JsonObject(body);
                        JsonObject matcher = new JsonObject().putString("_id", req.params().get("id"));
                        JsonObject json = new JsonObject().putString("collection", "zips")
                                .putString("action", "update")
                                .putObject("criteria", matcher)
                                .putBoolean("upsert", false)
                                .putBoolean("multi", false)
                                .putObject("objNew", newObject);

                        // and call the event we want to use
                        vertx.eventBus().send("mongodb-persistor", json, new Handler<Message<JsonObject>>() {
                            @Override
                            public void handle(Message<JsonObject> event) {
                                // we could handle the errors here, but for now
                                // assume everything went ok, and return the original
                                // and updated json
                                req.response().end(body);
                            }
                        });
                    }
                });
            }
        });

        matcher.post("/rxzips/:id", new Handler<HttpServerRequest>() {
            public void handle(final HttpServerRequest req) {
                // first access the buffer as an observable. We do this this way, since
                // we want to keep using the matchhandler and we can't do that with rxHttpServer
                Observable<Buffer> reqDataObservable = RxSupport.toObservable(req);

                // after we have the body, we update the element in the database
                Observable<RxMessage<JsonObject>> updateObservable = reqDataObservable.flatMap(new Func1<Buffer, Observable<RxMessage<JsonObject>>>() {
                    @Override
                    public Observable<RxMessage<JsonObject>> call(Buffer buffer) {
                        System.out.println("buffer = " + buffer);
                        // create the message
                        JsonObject newObject = new JsonObject(buffer.getString(0, buffer.length()));
                        JsonObject matcher = new JsonObject().putString("_id", req.params().get("id"));
                        JsonObject json = new JsonObject().putString("collection", "zips")
                                .putString("action", "update")
                                .putObject("criteria", matcher)
                                .putBoolean("upsert", false)
                                .putBoolean("multi", false)
                                .putObject("objNew", newObject);

                        // and return an observable
                        return rxEventBus.send("mongodb-persistor", json);
                    }
                });

                // use the previous input again, so we could see whether the update was successful.
                Observable<RxMessage<JsonObject>> getLatestObservable = updateObservable.flatMap(new Func1<RxMessage<JsonObject>, Observable<RxMessage<JsonObject>>>() {
                    @Override
                    public Observable<RxMessage<JsonObject>> call(RxMessage<JsonObject> jsonObjectRxMessage) {
                        System.out.println("jsonObjectRxMessage = " + jsonObjectRxMessage);
                        // next we get the latest version from the database, after the update has succeeded
                        // this isn't dependent on the previous one. It just has to wait till the previous
                        // one has updated the database, but we could check whether the previous one was successfully
                        JsonObject matcher = new JsonObject().putString("_id", req.params().get("id"));
                        JsonObject json2 = new JsonObject().putString("collection", "zips")
                                .putString("action", "find")
                                .putObject("matcher", matcher);
                        return rxEventBus.send("mongodb-persistor", json2);
                    }
                });

                // after we've got the latest version we return this in the response.
                getLatestObservable.subscribe(new Action1<RxMessage<JsonObject>>() {
                    @Override
                    public void call(RxMessage<JsonObject> jsonObjectRxMessage) {
                        req.response().end(jsonObjectRxMessage.body().encodePrettily());
                    }
                });
            }
        });


        // create and run the server
        HttpServer server = vertx.createHttpServer().requestHandler(matcher).listen(1234);

//        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
//            @Override
//            public void handle(final HttpServerRequest httpServerRequest) {
//
//                // we send the response from the mongo query back to the client.
//                // first create the query
//                JsonObject matcher = new JsonObject().putString("state", "AL");
//                JsonObject json = new JsonObject().putString("collection", "zips")
//                        .putString("action", "find")
//                        .putObject("matcher", matcher);
//
//                // send it over the bus
//                vertx.eventBus().send("mongodb-persistor", json, new Handler<Message<JsonObject>>() {
//
//                    @Override
//                    public void handle(Message<JsonObject> message) {
//                        // send the response back, encoded as string
//                        httpServerRequest.response().end(message.body().encodePrettily());
//                    }
//                });
//            }
//        }).listen(1234);

        System.out.println("afa111a");


        // output that the server is started
        container.logger().info("Webserver started, listening on port: 1234");
    }

    private static class ReplyHandler implements Handler<Message<JsonObject>> {

        private final HttpServerRequest request;
        private JsonObject data;

        private ReplyHandler(final HttpServerRequest request, JsonObject data) {
            this.request = request;
            this.data = data;
        }

        @Override
        public void handle(Message<JsonObject> event) {
            // if the response contains more message, we need to get the rest
            if (event.body().getString("status").equals("more-exist")) {
                JsonArray results = event.body().getArray("results");

                for (Object el : results) {
                    data.getArray("results").add(el);
                }

                event.reply(new JsonObject(), new ReplyHandler(request, data));
            } else {

                JsonArray results = event.body().getArray("results");
                for (Object el : results) {
                    data.getArray("results").add(el);
                }

                request.response().putHeader("Content-Type", "application/json");
                request.response().end(data.encodePrettily());
            }
        }
    }
}




