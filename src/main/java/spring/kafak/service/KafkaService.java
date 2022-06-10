package spring.kafak.service;

import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaService {

  private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

  public void consumerStart() {
    MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer("tokenContainer");
    container.start();
  }

  public void consumerStop() {
    MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer("tokenContainer");
    container.stop();
  }
}
