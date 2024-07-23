package com.flab.Mytube.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//public class CustomAcknowledgingMessageListener implements AcknowledgingMessageListener<String, Object> {
public class CustomAcknowledgingMessageListener{

//  @Override
  @KafkaListener(topics = "videoPath", groupId = "myGroup", containerFactory = "kafkaListenerContainerFactory")
  public void onMessage(EncodingRequest data, Acknowledgment acknowledgment) {
    try{
      log.info("Cunsumer Data >>> "+ data.toString());
      acknowledgment.acknowledge();
    }catch(Exception e){
      log.info("Custom Consumer Exception >>> "+e);
    }
  }


//  @Override
  public void onMessage(ConsumerRecord data){
    try{
      log.info("Cunsumer Data >>> "+ data.toString());
    }catch(Exception e){
      log.info("Custom Consumer Exception >>> "+e);
    }
  }
}
