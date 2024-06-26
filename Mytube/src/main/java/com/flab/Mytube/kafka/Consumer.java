package com.flab.Mytube.kafka;

import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Consumer {

  @KafkaListener(topics = "thing1", groupId = "myGroup", containerFactory = "kafkaListenerContainerFactory")
  public CompletableFuture<String> listen(String data) {
    CompletableFuture<String> future = new CompletableFuture<>();
    future.complete("done");
    return future;
  }

  public String hello(String message) {
    log.info(message);
    return "hello world";
  }

  @KafkaListener(topics = "thing1", groupId = "myGroup", containerFactory = "consumerFactory")
  public void processMessage(String content) {
    // ...
  }

//  @KafkaListener(id = "myListener", topics = "myTopic")
//  public CompletableFuture<String> listen(String data) {
//    CompletableFuture<String> future = new CompletableFuture<>();
//    future.complete("done");
//    return future;
//  }

}
