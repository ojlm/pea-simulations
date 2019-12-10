package pea.example.dubbo

import org.apache.dubbo.config.{ApplicationConfig, ReferenceConfig}
import pea.example.ext.dubbo.api.GreetingService

object GreetingConsumerApp {

  def main(args: Array[String]): Unit = {
    val reference = new ReferenceConfig[GreetingService]()
    reference.setApplication(new ApplicationConfig("pea-dubbo-consumer"))
    reference.setTimeout(3000)
    reference.setInterface(classOf[GreetingService])
    reference.setUrl(s"dubbo://127.0.0.1:20880/${classOf[GreetingService].getName}")
    val service = reference.get()
    val response = service.sayHello("pea")
    println(s"Got: ${response}")
    reference.destroy()
    System.exit(0)
  }
}
