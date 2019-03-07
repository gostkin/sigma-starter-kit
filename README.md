# Sigma-stater-kit
To make the example runnable you should:
1. install java-9 (or higher)
2. clone ergo and sigmastate-interpreter
3. select `v2.0` branch in both projects
4. replace `build.sbt` files in both project roots or change publishing settings manually
5. run `sbt publishLocal` in both projects
6. now it's possible to build and run our test file (`src/test/scala/OracleExampleSpecification.scala`) to ensure that 
    everything goes fine