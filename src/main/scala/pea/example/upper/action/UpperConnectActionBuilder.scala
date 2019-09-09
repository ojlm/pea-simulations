package pea.example.upper.action

import io.gatling.commons.stats.OK
import io.gatling.commons.util.Clock
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.action.{Action, ExitableAction}
import io.gatling.core.session.Session
import io.gatling.core.stats.StatsEngine
import io.gatling.core.structure.ScenarioContext
import io.gatling.core.util.NameGen
import pea.example.upper.action.UpperConnectActionBuilder.UpperConnect
import pea.example.upper.protocol.UpperProtocol

class UpperConnectActionBuilder(requestName: String) extends ActionBuilder {

  override def build(ctx: ScenarioContext, next: Action): Action = {
    val statsEngine = ctx.coreComponents.statsEngine
    val upperComponents = ctx.protocolComponentsRegistry.components(UpperProtocol.UpperProtocolKey)
    new UpperConnect(upperComponents.upperProtocol, statsEngine, ctx.coreComponents.clock, next)
  }
}

object UpperConnectActionBuilder {

  class UpperConnect(
                      protocol: UpperProtocol,
                      val statsEngine: StatsEngine,
                      val clock: Clock,
                      val next: Action
                    ) extends ExitableAction with NameGen {

    override def name: String = genName("upperConnect")

    override def execute(session: Session): Unit = {
      val client = new UpperServiceClient(protocol.address, protocol.port)
      val start = System.currentTimeMillis()
      client.run
      val end = System.currentTimeMillis()
      statsEngine.logResponse(session, name, start, end, OK, None, None)
      next ! session
    }
  }

}
