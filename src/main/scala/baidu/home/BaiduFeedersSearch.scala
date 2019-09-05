package baidu.home

import asura.pea.gatling.PeaSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BaiduFeedersSearch extends PeaSimulation {

  // 简单描述, 页面展示
  override val description: String =
    """
      |百度首页.
      |依次检索csv/json文件中的关键字.
      |判断响应是 200 , 返回包括 feeder 中内容.
      |并发用户: 每秒 10 个, 持续 10 秒.
      |""".stripMargin

  // http协议公用信息
  val httpProtocol = http
    .baseUrl("https://www.baidu.com") // 公共起始地址
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 公共头
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  // https://gatling.io/docs/current/session/feeder#feeder
  // 每次生成随机关键字
  // val feeder = Iterator.continually((Map("wd" -> (Random.alphanumeric.take(10).mkString))))
  // val feeder = csv("data/baidu.json").circular
  // 使用 csv 中的数据, 循环取
  val feeder = csv("data/baidu.csv").circular

  val search = feed(feeder)
    .exec(
      http("检索文件中内容")
        .get("/s?wd=${wd}")
        .check(
          status.is(200), // 响应码, 是200
          substring("${wd}").exists, // 返回的 html 中包括 feeder 中内容
        )
    )

  // 创建场景
  val homeScn = scenario("百度检索").exec(search)

  // 设置虚拟用户数
  setUp(
    homeScn.inject(constantUsersPerSec(10) during (10 seconds))
  ).protocols(httpProtocol)
}
