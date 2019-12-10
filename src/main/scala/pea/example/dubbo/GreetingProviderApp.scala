package pea.example.dubbo

import java.util.concurrent.CountDownLatch

import org.apache.dubbo.config.{ApplicationConfig, RegistryConfig, ServiceConfig}
import pea.example.dubbo.api.GreetingService
import pea.example.dubbo.provider.GreetingsServiceImpl

object GreetingProviderApp {

  def main(args: Array[String]): Unit = {
    val service = new ServiceConfig[GreetingService]()
    service.setApplication(new ApplicationConfig("pea-dubbo-provider"))
    service.setRegistry(new RegistryConfig("N/A"))
    service.setInterface(classOf[GreetingService])
    service.setRef(new GreetingsServiceImpl())
    service.export()
    println(s"${service.getInterface}: ${service.getExportedUrls}")
    new CountDownLatch(1).await()
  }
}
