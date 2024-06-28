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
//    producer.sendMessage(new MyOutputData("hello", "world"));
  }
}
