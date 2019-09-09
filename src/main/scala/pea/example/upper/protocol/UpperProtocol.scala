package pea.example.upper.protocol

import io.gatling.core.CoreComponents
import io.gatling.core.config.GatlingConfiguration
import io.gatling.core.protocol.{Protocol, ProtocolKey}

class UpperProtocol(val address: String, val port: Int) extends Protocol {

  type Components = UpperComponents
}

object UpperProtocol {

  def apply(address: String, port: Int): UpperProtocol = new UpperProtocol(address, port)

  val UpperProtocolKey = new ProtocolKey[UpperProtocol, UpperComponents] {
    override def protocolClass: Class[Protocol] = classOf[UpperProtocol].asInstanceOf[Class[Protocol]]

    override def defaultProtocolValue(configuration: GatlingConfiguration): UpperProtocol = {
      throw new IllegalArgumentException("Can't provide a default value for UpperProtocol")
    }

    override def newComponents(coreComponents: CoreComponents): UpperProtocol => UpperComponents = {
      upperProtocol => UpperComponents(upperProtocol)
    }
  }
}
