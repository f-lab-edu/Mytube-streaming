package com.flab.Mytube.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Consumer {
  @KafkaListener(topics = "hello", groupId = "world", containerFactory="consumerFactory")
  public String hello(String message){
    log.info(message);
    return "hello world";
  }

}
