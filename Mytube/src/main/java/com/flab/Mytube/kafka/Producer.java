package com.flab.Mytube.kafka;

import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer<EncodingRequest> {

  @Autowired
  private KafkaTemplate<String, EncodingRequest> template;

  public void send(String topicName, String key, EncodingRequest data){
    template.send(topicName, key, data);
    template.flush();
  }

  private void handleFailure(EncodingRequest data, ProducerRecord<String, String> record,
      Throwable ex) {
    System.out.println(">> >>>> >>> fail: " + data.toString());
    System.out.println(">> >>>> >>> record: " + record.toString());
    System.out.println(ex);
  }

  public void handleSuccess(EncodingRequest data) {
    System.out.println(">> >>>> >>> success: " + data.toString());
  }
}
