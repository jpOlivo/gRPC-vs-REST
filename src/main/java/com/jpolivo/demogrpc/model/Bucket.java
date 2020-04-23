package com.jpolivo.demogrpc.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Bucket {

  private String name = "myBucket";

  private String description = "Some description";

  private BigDecimal remainedAmount = BigDecimal.ZERO;

  private BigDecimal totalAmount = BigDecimal.TEN;

  private String validFor = "2019-10-23T21:00:10.200-03:00";

  private String status = "active";

  private String bucketType = "recarga";

  private String action = "myAction";

  private BigDecimal balanceAmount = BigDecimal.ONE;

  private Boolean allowed = true;
}
