package spring.kafak.service;

import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.kafak.component.Producer;

@Service
@RequiredArgsConstructor
public class KafkaService {

  private final Producer producer;

  private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

  public String publisher(String topic, String message) throws Exception {
    long offset = producer.sendMessage(topic, message);
    return "completed send message= " + message + " offset= " + offset;
  }

  public void subscribeStart() {
    MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer("tokenContainer");
    container.start();
  }

  public void SubscribeStop() {
    MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer("tokenContainer");
    container.stop();
  }
}
