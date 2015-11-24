package org.example.vertx.maven

import org.vertx.groovy.platform.Verticle

import static org.vertx.groovy.core.streams.Pump.createPump


/**
 * Created by mrseasons on 1/26/15.
 */
class GroovyEchoServer extends Verticle{

    //vertx run groovy:GroovyEchoServer
    def start() {
        vertx.createNetServer().connectHandler { socket ->
            createPump(socket, socket).start()
        }.listen(1234)
    }
}
