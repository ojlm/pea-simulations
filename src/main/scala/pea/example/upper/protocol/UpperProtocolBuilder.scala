package pea.example.upper.protocol

case class UpperProtocolBuilder(address: String, port: Int) {

  def build() = UpperProtocol(address, port)
}

object UpperProtocolBuilder {

  def endpoint(address: String, port: Int): UpperProtocolBuilder = UpperProtocolBuilder(address, port)
}
