package com.awakelife93.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

  @Value("${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  @Value("${kafka.groupId}")
  private String groupId;

  private final String consumerContainerId = "tokenContainer";

  private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

  private Map<String, Object> generateConsumerConfig() {
    Map<String, Object> config = new HashMap<String, Object>();

    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapAddress);
    config.put(ConsumerConfig.GROUP_ID_CONFIG, this.groupId);
    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    return config;
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(generateConsumerConfig());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  @Bean
  public MessageListenerContainer messageListenerContainer() {
    // * consumer는 일단 1개만... 만들어서 사용 중
    return kafkaListenerEndpointRegistry.getListenerContainer(consumerContainerId);
  }

}
