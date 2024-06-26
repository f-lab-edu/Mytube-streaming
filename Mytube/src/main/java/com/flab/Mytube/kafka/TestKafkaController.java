package com.flab.Mytube.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestKafkaController {
  Producer producer;
  @GetMapping("/")
  public void testApi(){
    producer.hello("hello", "world");
  }
}
