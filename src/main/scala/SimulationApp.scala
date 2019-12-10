import com.typesafe.scalalogging.StrictLogging
import pea.app.PeaConfig
import pea.app.actor.GatlingRunnerActor
import pea.app.actor.GatlingRunnerActor.StartMessage
import pea.common.util.FutureUtils.RichFuture
import pea.common.util.StringUtils
import pea.example.dubbo.GreetingSimulation

import scala.concurrent.ExecutionContext.global

object SimulationApp extends StrictLogging {

  PeaConfig.defaultSimulationOutputFolder = IDEPathHelper.binariesFolder.toAbsolutePath.toString

  def main(args: Array[String]): Unit = {
    val simulation = classOf[GreetingSimulation].getName
    val message = StartMessage(
      IDEPathHelper.binariesFolder.toAbsolutePath.toString,
      simulation,
      true,
      IDEPathHelper.resultsFolder.toAbsolutePath.toString,
      StringUtils.EMPTY,
    )
    val code = GatlingRunnerActor.start(message)(global).result.await.code
    logger.info(s"Exit: ${code}")
  }
}
