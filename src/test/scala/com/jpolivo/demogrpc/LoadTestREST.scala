package com.jpolivo.demogrpc;

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class LoadTestREST extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")

  object HelloWorldResource {
    val greeting: ChainBuilder = exec(http("Greeting - REST API")
      .get("/greeting"))
  }

  val myScenario: ScenarioBuilder = scenario("RampUpUsers")
    .exec(HelloWorldResource.greeting)

  setUp(myScenario.inject(
    incrementUsersPerSec(20)
      .times(5)
      .eachLevelLasting(5 seconds)
      .separatedByRampsLasting(5 seconds)
      .startingFrom(20)
  )).protocols(httpProtocol)
    .assertions(global.successfulRequests.percent.is(100))
  
//   val scn4: ScenarioBuilder = scenario("gRPC call - 10.000 users over 15s")
//    .exec(
//      HelloWorldResource.greeting)
//      
//  setUp(scn4.inject(rampUsers(10000) during(FiniteDuration(15, TimeUnit.SECONDS))).protocols(httpProtocol))
}

