package com.flab.Mytube.kafka;

import com.google.common.util.concurrent.ListenableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {
  private final KafkaTemplate<Integer, String> kafkaTemplate;

  public String hello(String topic, String payload){
    log.info("topic = {}, payload = {}", topic,payload);
    kafkaTemplate.send(topic, payload);
    return "hello world";
  }
}
