package pea.example.upper.protocol

import io.gatling.core.protocol.ProtocolComponents
import io.gatling.core.session.Session

case class UpperComponents(upperProtocol: UpperProtocol) extends ProtocolComponents {

  override lazy val onStart: Session => Session = (session: Session) => session

  override lazy val onExit: Session => Unit = (_: Session) => {}
}
