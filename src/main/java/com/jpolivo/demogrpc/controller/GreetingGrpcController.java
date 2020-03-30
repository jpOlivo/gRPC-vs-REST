package com.jpolivo.demogrpc.controller;

import com.jpolivo.demogrpc.core.GreetingRequest;
import com.jpolivo.demogrpc.core.GreetingResponse;
import com.jpolivo.demogrpc.core.GreetingServiceGrpc.GreetingServiceImplBase;
import com.jpolivo.demogrpc.service.GrettingService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class GreetingGrpcController extends GreetingServiceImplBase {
  @Autowired private GrettingService grettingService;

  @Override
  public void greeting(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {

    responseObserver.onNext(grettingService.greeting(request.getName()));
    responseObserver.onCompleted();
  }
}
