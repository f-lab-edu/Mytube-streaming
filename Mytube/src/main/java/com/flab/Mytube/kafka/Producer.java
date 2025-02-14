package com.flab.Mytube.kafka;

import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {

  @Autowired
  private KafkaTemplate<String, String> template;

  public CompletableFuture<SendResult<String, String>> send(String topicName, String key, String data){
    return template.send(topicName, key, data);
  }
}
