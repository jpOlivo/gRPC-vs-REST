package com.jpolivo.demogrpc.service;

import com.jpolivo.demogrpc.core.GreetingResponse;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GrettingService {
  private static final String TEMPLATE = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Override
  public GreetingResponse greeting(String name) {
    return GreetingResponse.newBuilder()
        .setId(counter.incrementAndGet())
        .setContent(String.format(TEMPLATE, name))
        .build();
  }
}
