import asura.pea.actor.GatlingRunnerActor
import asura.pea.actor.GatlingRunnerActor.StartMessage
import com.typesafe.scalalogging.StrictLogging
import pea.example.BasicSimulation

import scala.concurrent.ExecutionContext.global

object SimulationApp extends StrictLogging {

  def main(args: Array[String]): Unit = {
    val simulation = classOf[BasicSimulation].getName
    val message = StartMessage(
      IDEPathHelper.binariesFolder.toAbsolutePath.toString,
      simulation,
      true,
      IDEPathHelper.resultsFolder.toAbsolutePath.toString,
    )
    val code = GatlingRunnerActor.start(message)(global)
    logger.info(s"Exit: ${code}")
  }
}
