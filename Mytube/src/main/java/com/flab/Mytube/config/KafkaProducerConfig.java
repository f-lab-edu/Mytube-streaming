package com.flab.Mytube.config;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
  @Value("${kafka.bootstrapAddress}")
  private String bootstrapServers;

  /**
   * ack: all
   * In-Sync-Replica에 모두 event가 저장되었음이 확인 되어야 ack 신호를 보냄 가장 성능은 떨어지지만
   * event produce를 보장할 수 있음.
   */
  @Value("${kafka.producer.acksConfig}")
  private String acksConfig;

  @Value("${kafka.producer.retry}")
  private Integer retry;

  @Value("${kafka.producer.enable-idempotence}")
  private Boolean enableIdempotence;
  @Value("${kafka.producer.max-in-flight-requests-per-connection}")
  private Integer maxInFlightRequestsPerConnection;


  /* ProducerFactory<?, ?>
  SpringBoot 에 의해 auto-configured
  When you use the methods with a Message<?> parameter,
  the topic, partition, key and timestamp information is provided in a *message header* that includes the following items:
  KafkaHeaders.TOPIC
  KafkaHeaders.PARTITION
  KafkaHeaders.KEY
  KafkaHeaders.TIMESTAMP

  KafkaProducer 객체 초기화
   */
  @Bean // factory 객체 구성하기, 설정 삽입,Producer를 생성해주는 공장
  public ProducerFactory<Integer, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean // factory에 들어갈 세부 설정
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    props.put(ProducerConfig.RETRIES_CONFIG, retry);
    props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotence);
    props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, maxInFlightRequestsPerConnection);

    // See https://kafka.apache.org/documentation/#producerconfigs for more properties
    return props;
  }

  @Bean // factory 객체 발행
  public KafkaTemplate<Integer, String> kafkaTemplate() {
    return new KafkaTemplate<Integer, String>(producerFactory());
  }

//    create templates with different producer configurations from the same factory.
//    @Bean
//    public ProducerFactory<String, Object> producerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
}
