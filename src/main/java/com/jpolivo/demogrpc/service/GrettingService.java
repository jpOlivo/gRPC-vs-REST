package com.jpolivo.demogrpc.service;

import com.jpolivo.demogrpc.core.GreetingResponse;

public interface GrettingService {
  GreetingResponse greeting(String name);
}
