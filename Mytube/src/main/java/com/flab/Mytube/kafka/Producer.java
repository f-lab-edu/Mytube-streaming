package com.flab.Mytube.kafka;

import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

  @Autowired
  private static KafkaTemplate<String, String> template;

  public void sendMessage(final MyOutputData data) {
    ProducerRecord<String, String> record = createRecord(data);
    CompletableFuture<SendResult<String, String>> future = template.send(record);

    future.whenComplete((result, ex) -> {
      if (ex == null) {
        handleSuccess(data);
      }
      else {
        handleFailure(data, record, ex);
      }
    });
  }

  public static void sendPath(final String topic,final String path){
    CompletableFuture<SendResult<String, String>> future = template.send(topic, path);
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        handleSuccess(path);
      }
      else {
        handleFailure(path, new ProducerRecord<>(topic, path), ex);
      }
    });
  }


  private ProducerRecord<String, String> createRecord(MyOutputData data) {
    ProducerRecord<String, String> result = new ProducerRecord(data.name, data.school);
    return result;
  }

  private void handleFailure(MyOutputData data, ProducerRecord<String, String> record,
      Throwable ex) {
    System.out.println(">> >>>> >>> fail: " + data.toString());
    System.out.println(">> >>>> >>> record: " + record.toString());
    System.out.println(ex);
  }

  public void handleSuccess(MyOutputData data) {
    System.out.println(">> >>>> >>> success: " + data.toString());
  }
  private static void handleFailure(String data, ProducerRecord<String, String> record,
      Throwable ex) {
    System.out.println(">> >>>> >>> fail: " + data);
    System.out.println(">> >>>> >>> record: " + record.toString());
    System.out.println(ex);
  }

  public static void handleSuccess(String data) {
    System.out.println(">> >>>> >>> success: " + data);
  }
}
