//package com.jpolivo.demogrpc;
//
//import java.util.concurrent.TimeUnit
//
//import scala.concurrent.duration.FiniteDuration
//
//import com.github.phisgr.gatling.grpc.Predef.grpc
//import com.github.phisgr.gatling.grpc.protocol.GrpcProtocol
//import com.jpolivo.demogrpc.core.GreetingService.GreetingRequest
//import com.jpolivo.demogrpc.core.GreetingService.GreetingServiceGrpc
//
//import io.gatling.core.Predef._
//import io.gatling.core.Predef.Simulation
//import io.gatling.core.Predef.atOnceUsers
//import io.gatling.core.Predef.exec
//import io.gatling.core.Predef.openInjectionProfileFactory
//import io.gatling.core.Predef.scenario
//import io.gatling.core.Predef.stringToExpression
//import io.gatling.core.Predef.value2Expression
//import io.gatling.core.structure.ChainBuilder
//import io.gatling.core.structure.ScenarioBuilder
//import io.grpc.ManagedChannelBuilder
//
//class LoadTestgRPC extends Simulation {
//
//  val grpcConfig: GrpcProtocol = grpc(ManagedChannelBuilder
//    .forAddress("localhost", 8081)
//    .usePlaintext())
//
//  val sec30: FiniteDuration = FiniteDuration(30, TimeUnit.SECONDS)
//  val sec15: FiniteDuration = FiniteDuration(15, TimeUnit.SECONDS)
//  val sec120: FiniteDuration = FiniteDuration(120, TimeUnit.SECONDS)
//
//  val grettingRequest: GreetingRequest = GreetingRequest.of("")
//
//  object HelloWorldResource {
//    val greeting: ChainBuilder = exec(grpc("Greeting - gRPC API")
//      .rpc(GreetingServiceGrpc.METHOD_GREETING)
//      .payload(grettingRequest))
//  }
//
//  //  val myScenario: ScenarioBuilder = scenario("RampUpUsers").exec(HelloWorldResource.greeting)
//  //  setUp(myScenario.inject(
//  //    incrementUsersPerSec(20)
//  //      .times(5)
//  //      .eachLevelLasting(5 seconds)
//  //      .separatedByRampsLasting(5 seconds)
//  //      .startingFrom(20))).protocols(grpcConfig)
//  //    .assertions(global.successfulRequests.percent.is(100))
//
//  //  val scn1: ScenarioBuilder = scenario("gRPC call - 1 user repeated 100 times")
//  //    .repeat(100) {
//  //      exec(
//  //        HelloWorldResource.greeting)
//  //    }
//  //  setUp(scn1.inject(atOnceUsers(1)).protocols(grpcConfig))
//
//  //  val scn2: ScenarioBuilder = scenario("gRPC call - 50 users repeated 100 times")
//  //    .repeat(100) {
//  //      exec(
//  //        HelloWorldResource.greeting)
//  //    }
//  //
//  //  setUp(scn2.inject(atOnceUsers(50)).protocols(grpcConfig))
//
//  //  // ~ 333 users/s
//  //  val scn3: ScenarioBuilder = scenario("gRPC call - 10.000 users over 30s")
//  //    .exec(
//  //      HelloWorldResource.greeting)
//  //
//  //  setUp(scn3.inject(rampUsers(10000) during (sec30)).protocols(grpcConfig))
//
//  //  // ~ 667 users/s
//  //  val scn4: ScenarioBuilder = scenario("gRPC call - 10.000 users over 15s")
//  //    .exec(
//  //      HelloWorldResource.greeting)
//  //
//  //  setUp(scn4.inject(rampUsers(10000) during (sec15)).protocols(grpcConfig))
//
//  val scn5: ScenarioBuilder = scenario("gRPC call - 500 users over 120s")
//    .exec(
//      HelloWorldResource.greeting)
//
//  setUp(scn5.inject(rampUsers(500) during (sec120)).protocols(grpcConfig))
//}
//
