package com.jpolivo.demogrpc.controller;

import com.jpolivo.demogrpc.core.GreetingResponse;
import com.jpolivo.demogrpc.service.GrettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(GreetingRestController.class);

  @Autowired private GrettingService grettingService;

  @GetMapping("/greeting")
  public ResponseEntity<GreetingResponse> greeting(
      @RequestParam(value = "name", defaultValue = "World") String name) {
	  
	LOGGER.info("name {}", name);  
    return ResponseEntity.ok().body(grettingService.greeting(name));
  }
}
