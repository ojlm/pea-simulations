package pea.example.upper

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Tcp.{IncomingConnection, ServerBinding}
import akka.stream.scaladsl.{Flow, Source, Tcp}
import akka.util.ByteString

import scala.concurrent.Future

/**
 * 将输入转为大写返回. 测试自定义协议
 */
object TcpServer {

  implicit val system = ActorSystem("UpperCaseTcpServer")
  implicit val materializer = ActorMaterializer()

  def main(args: Array[String]): Unit = {
    val address = "127.0.0.1"
    val port = 8888
    val echo = Flow[ByteString].map(_.utf8String.toUpperCase).map(ByteString(_))
    val connections: Source[IncomingConnection, Future[ServerBinding]] = Tcp().bind(address, port)
    connections.runForeach(connection => {
      println(s"New connection: ${connection.remoteAddress}")
      connection.handleWith(echo)
    })
    println(s"Server start at: ${address}:${port}")
  }
}
