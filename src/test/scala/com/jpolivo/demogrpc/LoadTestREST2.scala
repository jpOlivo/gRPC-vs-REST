package com.jpolivo.demogrpc;

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.FiniteDuration

import io.gatling.core.Predef._
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef.atOnceUsers
import io.gatling.core.Predef.configuration
import io.gatling.core.Predef.exec
import io.gatling.core.Predef.openInjectionProfileFactory
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef.stringToExpression
import io.gatling.core.Predef.value2Expression
import io.gatling.core.structure.ChainBuilder
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

class LoadTestREST2 extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    //    .baseUrl("http://localhost:8080")
    .baseUrl("https://localhost:8443")

  val sec30: FiniteDuration = FiniteDuration(30, TimeUnit.SECONDS)
  val sec15: FiniteDuration = FiniteDuration(15, TimeUnit.SECONDS)
  val sec120: FiniteDuration = FiniteDuration(120, TimeUnit.SECONDS)

  object BucketLegacyResource {
    val bucket: ChainBuilder = exec(http("Bucket - REST API")
      .get("/bucket"))
  }

  //  val myScenario: ScenarioBuilder = scenario("RampUpUsers")
  //    .exec(BucketLegacyResource.bucket)
  //    setUp(myScenario.inject(
  //      incrementUsersPerSec(20)
  //        .times(5)
  //        .eachLevelLasting(5 seconds)
  //        .separatedByRampsLasting(5 seconds)
  //        .startingFrom(20)
  //    )).protocols(httpProtocol)
  //      .assertions(global.successfulRequests.percent.is(100))

  //    val scn1: ScenarioBuilder = scenario("REST call - 1 user repeated 100 times")
  //      .repeat(100) {
  //        exec(
  //          BucketLegacyResource.bucket)
  //      }
  //    setUp(scn1.inject(atOnceUsers(1)).protocols(httpProtocol))

  val scn2: ScenarioBuilder = scenario("REST call - 50 users repeated 100 times")
    .repeat(100) {
      exec(
        BucketLegacyResource.bucket)
    }
  setUp(scn2.inject(atOnceUsers(50)).protocols(httpProtocol))

  //  // ~ 333 users/s
  //    val scn3: ScenarioBuilder = scenario("REST call - 10.000 users over 30s")
  //      .exec(
  //        BucketLegacyResource.bucket)
  //    setUp(scn3.inject(rampUsers(10000) during (sec30)).protocols(httpProtocol))

  //     ~ 667 users/s
  //      val scn4: ScenarioBuilder = scenario("REST call - 10.000 users over 15s")
  //        .exec(
  //          BucketLegacyResource.bucket)
  //      setUp(scn4.inject(rampUsers(10000) during (sec15)).protocols(httpProtocol))

  //  val scn5: ScenarioBuilder = scenario("REST call - 5.000 users over 30s")
  //    .exec(
  //      BucketLegacyResource.bucket)
  //  setUp(scn5.inject(rampUsers(5000) during (sec30)).protocols(httpProtocol))

}