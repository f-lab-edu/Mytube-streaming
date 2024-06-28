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

//  public static CompletableFuture<SendResult<String, String>> sendMessage(String rootpath){
//    ProducerRecord<String, String> record = new ProducerRecord<>("videoEncoding", rootpath);
//    CompletableFuture<SendResult<String, String>> future = template.send(record);
//    future.whenComplete((result, ex)->{
//      if(ex == null){
//        System.out.println("성공적으로 동영상을 업로드했습니다.");
//      }
//      else{
//        System.err.println("동영상 업로드 중 문제가 발생했습니다.");
//        ex.printStackTrace();
//      }
//    });
//    return future;
//  }

  public static void sendMessage(final String data) {
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


  private static ProducerRecord<String, String> createRecord(String data) {
    ProducerRecord<String, String> result = new ProducerRecord("videoEncoding", data);
    return result;
  }

  private static void handleFailure(MyOutputData data, ProducerRecord<String, String> record,
      Throwable ex) {
    System.out.println(">> >>>> >>> fail: " + data.toString());
    System.out.println(">> >>>> >>> record: " + record.toString());
    System.out.println(ex);
  }

  public static void handleSuccess(MyOutputData data) {
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
