package com.jpolivo.demogrpc;

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._

import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.grpc.protocol.GrpcProtocol
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.grpc.ManagedChannelBuilder
import com.jpolivo.demogrpc.core.GreetingService.{ GreetingServiceGrpc, GreetingRequest }
import com.jpolivo.demogrpc.core.GreetingService.GreetingRequest

import scala.concurrent.duration.FiniteDuration
import io.gatling.core.structure.ChainBuilder
//import com.jpolivo.demogrpc.core.GreetingServiceGrpc

class LoadTestgRPC extends Simulation {

  val grpcConfig: GrpcProtocol = grpc(ManagedChannelBuilder
    .forAddress("localhost", 8081)
    .usePlaintext())

  val grettingRequest: GreetingRequest = GreetingRequest.of("")

  object HelloWorldResource {
    val greeting: ChainBuilder = exec(grpc("Greeting - gRPC API")
      .rpc(GreetingServiceGrpc.METHOD_GREETING)
      .payload(grettingRequest))
  }

  val myScenario: ScenarioBuilder = scenario("RampUpUsers").exec(HelloWorldResource.greeting)

  setUp(myScenario.inject(
    incrementUsersPerSec(20)
      .times(5)
      .eachLevelLasting(5 seconds)
      .separatedByRampsLasting(5 seconds)
      .startingFrom(20))).protocols(grpcConfig)
    .assertions(global.successfulRequests.percent.is(100))

//  val scn4: ScenarioBuilder = scenario("gRPC call - 10.000 users over 15s")
//    .exec(
//      HelloWorldResource.greeting)
//      
//  setUp(scn4.inject(rampUsers(10000) during(FiniteDuration(15, TimeUnit.SECONDS))).protocols(grpcConfig))
}

