package pea.example.upper

import asura.pea.gatling.PeaSimulation
import io.gatling.core.Predef._
import pea.example.upper.Predef._

// 简单如何自定义协议实现
class UpperExampleSimulation extends PeaSimulation {

  override val description: String =
    """
      |自定义协议模拟
      |""".stripMargin

  val upperProtocol = upper.endpoint("127.0.0.1", 8888)

  val scn = scenario("UpperProtocol")
    .exec(upper("user").connect())

  setUp(
    scn.inject(atOnceUsers(10))
  ).protocols(upperProtocol)
}
