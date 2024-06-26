package com.flab.Mytube.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestKafkaController {
  Producer producer;
//  @GetMapping("/")
//  public void testApi(){
//    producer.hello("hello", "world");
//  }

  @GetMapping("/")
  public void testapi(){
    producer.sendMessage(new MyOutputData("hello", "world"));
  }
}
