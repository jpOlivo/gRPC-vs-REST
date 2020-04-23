package com.jpolivo.demogrpc.service;

import com.jpolivo.demogrpc.bucket.core.BucketLegacyResponse;
import com.jpolivo.demogrpc.model.BucketLegacy;

public interface BucketService {
  BucketLegacy getBucketsLegacy(String productId);

  BucketLegacyResponse getBucketsLegacy2(String productId);
}
