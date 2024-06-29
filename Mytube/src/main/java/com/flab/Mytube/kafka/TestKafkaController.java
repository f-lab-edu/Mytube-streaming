package com.flab.Mytube.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestKafkaController {
  @Autowired
  Producer producer;

  @GetMapping("")
  public void testapi(){
    EncodingRequest request = EncodingRequest.builder()
        .key("hello")
        .topic("world")
        .path("is here").build();
    producer.sendPath(request);
  }
}
