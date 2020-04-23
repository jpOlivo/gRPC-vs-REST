package com.jpolivo.demogrpc.controller;

import com.jpolivo.demogrpc.core.GreetingRequest;
import com.jpolivo.demogrpc.core.GreetingResponse;
import com.jpolivo.demogrpc.core.GreetingServiceGrpc.GreetingServiceImplBase;
import com.jpolivo.demogrpc.service.GrettingService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class GreetingGrpcController extends GreetingServiceImplBase {
  private static final Logger LOGGER = LoggerFactory.getLogger(GreetingGrpcController.class);

  @Autowired private GrettingService grettingService;

  @Override
  public void greeting(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {

    String name = request.getName();
    LOGGER.info("name {}", name);

    responseObserver.onNext(grettingService.greeting(name));
    responseObserver.onCompleted();
  }
}
