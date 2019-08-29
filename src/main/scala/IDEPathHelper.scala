import java.nio.file.{Path, Paths}

import io.gatling.commons.util.PathHelper._

object IDEPathHelper {

  val projectRootDir: Path = Paths.get(".")
  val binariesFolder = projectRootDir / "build" / "classes" / "scala" / "main"
  val resultsFolder = projectRootDir / "results"
}
