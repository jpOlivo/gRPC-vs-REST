package com.jpolivo.demogrpc.service;

import com.jpolivo.demogrpc.bucket.core.BucketLegacyResponse;
import com.jpolivo.demogrpc.bucket.core.BucketLegacyResponse.Builder;
import com.jpolivo.demogrpc.model.Bucket;
import com.jpolivo.demogrpc.model.BucketLegacy;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BucketServiceImpl implements BucketService {

  private static final Logger LOGGER = LoggerFactory.getLogger(BucketServiceImpl.class);

  /**
   * Gets the buckets legacy.
   *
   * @param productId the product id
   * @return the buckets legacy
   */
  @Override
  public BucketLegacy getBucketsLegacy(String productId) {

    List<Bucket> buckets = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      buckets.add(new Bucket().toBuilder().build());
    }

    BucketLegacy bucketLegacy = new BucketLegacy();
    bucketLegacy.setBucketBalance(buckets);

    Bucket bucket = new Bucket().toBuilder().build();
    bucketLegacy.setBucketBalanceBill(bucket);

    return bucketLegacy;
  }

  @Override
  public BucketLegacyResponse getBucketsLegacy2(String productId) {

    Builder bucketLegacyBuilder = BucketLegacyResponse.newBuilder();
    for (int i = 0; i < 1000; i++) {
      bucketLegacyBuilder.addBucketBalance(com.jpolivo.demogrpc.bucket.core.Bucket.newBuilder());
    }

    bucketLegacyBuilder.setBucketBalanceBill(com.jpolivo.demogrpc.bucket.core.Bucket.newBuilder());

    return bucketLegacyBuilder.build();
  }

  //  byte[] bigDecimalToByte(BigDecimal num) {
  //    BigInteger sig = new BigInteger(num.unscaledValue().toString());
  //    int scale = num.scale();
  //    byte[] bscale =
  //        new byte[] {
  //          (byte) (scale >>> 24), (byte) (scale >>> 16), (byte) (scale >>> 8), (byte) (scale)
  //        };
  //    return Bytes.concat(bscale, sig.toByteArray());
  //  }

  //  BigDecimal byteToBigDecimal(byte[] raw) {
  //    int scale =
  //        (raw[0] & 0xFF) << 24 | (raw[1] & 0xFF) << 16 | (raw[2] & 0xFF) << 8 | (raw[3] & 0xFF);
  //    BigInteger sig = new BigInteger(subset(raw, 4));
  //    return new BigDecimal(sig, scale);
  //  }

  //  public static void main (String []args) {
  //	  BucketLegacy l = new BucketServiceImpl().getBucketsLegacy("1212321");
  //	  System.out.println(l);
  //  }
}
