package com.flab.Mytube.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;


// automatically add topics to the broker.
@Configuration
public class KafkaTopicConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public KafkaAdmin admin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic topic1() {
    return TopicBuilder.name("thing1")
        .partitions(10)
        .replicas(1) // 복제 팩터를 1로 설정
        .compact()
        .build();
  }

  @Bean
  public NewTopic videoEncoding() {
    return TopicBuilder.name("videoEncoding")
        .partitions(10)
        .replicas(1) // 복제 팩터를 1로 설정
        .compact()
        .build();
  }

}
