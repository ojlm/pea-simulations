import asura.common.util.FutureUtils.RichFuture
import asura.common.util.StringUtils
import asura.pea.PeaConfig
import asura.pea.actor.GatlingRunnerActor
import asura.pea.actor.GatlingRunnerActor.StartMessage
import com.typesafe.scalalogging.StrictLogging
import pea.grpc.HelloGrpcSimulation

import scala.concurrent.ExecutionContext.global

object SimulationApp extends StrictLogging {

  PeaConfig.defaultSimulationOutputFolder = IDEPathHelper.binariesFolder.toAbsolutePath.toString

  def main(args: Array[String]): Unit = {
    val simulation = classOf[HelloGrpcSimulation].getName
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
