import org.vertx.scala.core.net.NetSocket
import org.vertx.scala.core.streams.Pump
import org.vertx.scala.platform.Verticle

/**
 * Created by mrseasons on 1/26/15.
 */
class ScalaEchoServer extends Verticle{

  override def start() {
    vertx.createNetServer().connectHandler({ socket: NetSocket =>
      Pump.createPump(socket, socket).start
    }).listen(1234)
  }
}
