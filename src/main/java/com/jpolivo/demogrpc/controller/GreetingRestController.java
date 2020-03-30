package com.jpolivo.demogrpc.controller;

import com.jpolivo.demogrpc.core.GreetingResponse;
import com.jpolivo.demogrpc.service.GrettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

  @Autowired private GrettingService grettingService;

  @GetMapping("/greeting")
  public GreetingResponse greeting(
      @RequestParam(value = "name", defaultValue = "World") String name) {
    return grettingService.greeting(name);
  }
}
