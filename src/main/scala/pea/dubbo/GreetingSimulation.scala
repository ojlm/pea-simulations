package pea.dubbo

import asura.pea.dubbo.Predef._
import asura.pea.gatling.PeaSimulation
import io.gatling.core.Predef._
import pea.dubbo.api.GreetingService

class GreetingSimulation extends PeaSimulation {

  override val description: String =
    """
      |Dubbo simulation example
      |""".stripMargin

  val dubboProtocol = dubbo
    .application("gatling-pea")
    .endpoint("127.0.0.1", 20880)
    .threads(10)

  val scn = scenario("dubbo")
    .exec(
      invoke(classOf[GreetingService]) { (service, _) =>
        service.sayHello("pea")
      }.check(simple { response => // 自定义检查, 可推导返回类型
        response.value == "hi, pea"
      }).check(
        jsonPath("$").is("hi, pea") // jsonPath 检查, 复杂对象的响应会转换 Map
      )
    )

  setUp(
    scn.inject(atOnceUsers(10000)) // 一次性模拟 10000 用户
  ).protocols(dubboProtocol)
}
