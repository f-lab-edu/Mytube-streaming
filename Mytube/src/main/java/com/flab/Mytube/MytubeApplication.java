package com.flab.Mytube;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@EnableCaching
@SpringBootApplication
public class MytubeApplication {

  public static void main(String[] args) {
    SpringApplication.run(MytubeApplication.class, args);
  }
}