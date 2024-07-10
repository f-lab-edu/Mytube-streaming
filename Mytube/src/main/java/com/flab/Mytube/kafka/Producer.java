package com.flab.Mytube.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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
    System.err.println("요청값을 확인해주세요.");
    System.err.println(">> >>>> >>> fail: " + data.toString());
    System.err.println(">> >>>> >>> record: " + record.toString());
    System.err.println(ex);
  }

  public void handleSuccess(EncodingRequest data) {
    System.err.println(">> >>>> >>> success: " + data.toString());
  }
}
