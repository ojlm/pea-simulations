package pea.example.upper

import pea.example.upper.action.Upper
import pea.example.upper.protocol.{UpperProtocol, UpperProtocolBuilder}

object Predef {

  val upper = UpperProtocolBuilder

  implicit def upperBuilderToProtocol(builder: UpperProtocolBuilder): UpperProtocol = builder.build()
  
  def upper(name: String) = new Upper(name)
}
