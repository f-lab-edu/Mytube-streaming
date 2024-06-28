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



  @KafkaListener(topics = "videoPath", groupId = "myGroup", containerFactory = "kafkaListenerContainerFactory")
  public CompletableFuture<String> listen(String path) {
    CompletableFuture<String> future = new CompletableFuture<>();
    future.complete("done");
    return future;
  }
}
