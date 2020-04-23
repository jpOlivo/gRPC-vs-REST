package com.jpolivo.demogrpc.controller;

import com.jpolivo.demogrpc.model.BucketLegacy;
import com.jpolivo.demogrpc.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BucketRestController {

  @Autowired private BucketService bucketService;

  @GetMapping(value = "/bucket")
  public ResponseEntity<BucketLegacy> getBuckets(
      @RequestParam(value = "productId", defaultValue = "123") String productId) {
    return ResponseEntity.ok().body(bucketService.getBucketsLegacy(productId));
  }

  //  @GetMapping(value = "/bucket")
  //  public ResponseEntity<BucketLegacyResponse> getBuckets(
  //      @RequestParam(value = "productId", defaultValue = "123") String productId) {
  //    return ResponseEntity.ok().body(bucketService.getBucketsLegacy2(productId));
  //  }
}
