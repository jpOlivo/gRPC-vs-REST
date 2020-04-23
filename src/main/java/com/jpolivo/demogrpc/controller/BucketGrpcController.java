package com.jpolivo.demogrpc.controller;

import com.jpolivo.demogrpc.bucket.core.BucketLegacyRequest;
import com.jpolivo.demogrpc.bucket.core.BucketLegacyResponse;
import com.jpolivo.demogrpc.bucket.core.BucketServiceGrpc.BucketServiceImplBase;
import com.jpolivo.demogrpc.service.BucketService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class BucketGrpcController extends BucketServiceImplBase {
  @Autowired private BucketService bucketService;

  @Override
  public void bucket(
      BucketLegacyRequest request, StreamObserver<BucketLegacyResponse> responseObserver) {
    responseObserver.onNext(bucketService.getBucketsLegacy2(request.getProductId()));
    responseObserver.onCompleted();
  }
}
