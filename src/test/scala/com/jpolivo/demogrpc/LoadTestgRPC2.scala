package com.jpolivo.demogrpc;

import java.util.concurrent.TimeUnit
import java.io.File;

import scala.concurrent.duration.FiniteDuration

import com.github.phisgr.gatling.grpc.Predef.grpc
import com.github.phisgr.gatling.grpc.protocol.GrpcProtocol

import io.gatling.core.Predef._
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef.exec
import io.gatling.core.Predef.openInjectionProfileFactory
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ChainBuilder
import io.gatling.core.structure.ScenarioBuilder
import io.grpc.ManagedChannelBuilder
import com.jpolivo.demogrpc.bucket.core.BucketService.BucketServiceGrpc
import com.jpolivo.demogrpc.bucket.core.BucketService.BucketLegacyRequest
import io.grpc.netty.GrpcSslContexts
import io.grpc.netty.NettyChannelBuilder

class LoadTestgRPC2 extends Simulation {

  val grpcConfig: GrpcProtocol = grpc(NettyChannelBuilder.forAddress("localhost", 8081)
    .useTransportSecurity()
    .sslContext(GrpcSslContexts
      .forClient()
      .trustManager(new File("localhost.crt"))
      .build()))

  val sec30: FiniteDuration = FiniteDuration(30, TimeUnit.SECONDS)
  val sec15: FiniteDuration = FiniteDuration(15, TimeUnit.SECONDS)
  val sec120: FiniteDuration = FiniteDuration(120, TimeUnit.SECONDS)

  val bucketRequest: BucketLegacyRequest = BucketLegacyRequest.of("123")

  object BucketLegacyResource {
    val bucket: ChainBuilder = exec(grpc("Bucket - gRPC API")
      .rpc(BucketServiceGrpc.METHOD_BUCKET)
      .payload(bucketRequest))
  }

  //  val myScenario: ScenarioBuilder = scenario("RampUpUsers").exec(BucketLegacyResource.bucket)
  //  setUp(myScenario.inject(
  //    incrementUsersPerSec(20)
  //      .times(5)
  //      .eachLevelLasting(5 seconds)
  //      .separatedByRampsLasting(5 seconds)
  //      .startingFrom(20))).protocols(grpcConfig)
  //    .assertions(global.successfulRequests.percent.is(100))

  //    val scn1: ScenarioBuilder = scenario("gRPC call - 1 user repeated 100 times")
  //      .repeat(100) {
  //        exec(
  //          BucketLegacyResource.bucket)
  //      }
  //    setUp(scn1.inject(atOnceUsers(1)).protocols(grpcConfig))

  val scn2: ScenarioBuilder = scenario("gRPC call - 50 users repeated 100 times")
    .repeat(100) {
      exec(
        BucketLegacyResource.bucket)
    }
  setUp(scn2.inject(atOnceUsers(50)).protocols(grpcConfig))

  // ~ 333 users/s
  //    val scn3: ScenarioBuilder = scenario("gRPC call - 10.000 users over 30s")
  //      .exec(
  //        BucketLegacyResource.bucket)
  //    setUp(scn3.inject(rampUsers(10000) during (sec30)).protocols(grpcConfig))

  // ~ 667 users/s
  //    val scn4: ScenarioBuilder = scenario("gRPC call - 10.000 users over 15s")
  //      .exec(
  //        BucketLegacyResource.bucket)
  //    setUp(scn4.inject(rampUsers(10000) during (sec15)).protocols(grpcConfig))

  //  val scn5: ScenarioBuilder = scenario("gRPC call - 5.000 users over 30s")
  //    .exec(
  //      BucketLegacyResource.bucket)
  //  setUp(scn5.inject(rampUsers(5000) during (sec30)).protocols(grpcConfig))

}