// options: https://github.com/thesamet/sbt-protoc
PB.protoSources in Compile := Seq(
  baseDirectory.value / "src/main/protobuf"
)
PB.targets in Compile := Seq(
  scalapb.gen(grpc = true) -> baseDirectory.value / "src/main/scalapb"
)
unmanagedSourceDirectories in Compile += baseDirectory.value / "src/main/scalapb"
sourceGenerators in Compile -= (PB.generate in Compile).taskValue
