package com.flab.Mytube.kafka;

import com.google.common.util.concurrent.ListenableFuture;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class Producer<EncodingRequest> {

  @Autowired
  private KafkaTemplate<String, EncodingRequest> template;

  public void send(String topic, String key, EncodingRequest data) {
    template.send(topic, key, data);
    template.flush();
  }

//  public void sendPath(final EncodingRequest data) {
//    String key = Paths.get(data.path).getFileName().toString().split("\\.")[0];
//    ProducerRecord<String, Object> record = createRecord(key, data);
//    CompletableFuture<SendResult<String, Object>> future = template.send(key, record);
//
//    future.whenComplete((result, ex) -> {
//      if (ex == null) {
//        handleSuccess(data);
//      }
//      else {
//        handleFailure(data, record, ex);
//      }
//    });
//  }
//
//
//  private ProducerRecord<String, Object> createRecord(String key, EncodingRequest data) {
//    ProducerRecord<String, Object> result = new ProducerRecord(data.topic, key, data.path);
//    return result;
//  }

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
