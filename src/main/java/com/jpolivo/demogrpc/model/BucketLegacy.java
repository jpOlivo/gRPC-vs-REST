package com.jpolivo.demogrpc.model;

import java.util.List;
import lombok.Data;

@Data
public class BucketLegacy {
  private List<Bucket> bucketBalance;
  private Bucket bucketBalanceBill;
}
