package spring.kafak.service;

import java.util.concurrent.ExecutionException;

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

  public String publisher(String topic, String message) throws InterruptedException, ExecutionException {
    try {
      long offset = producer.sendMessage(topic, message);
      return "completed send message= " + message + " offset= " + offset;
    } catch (InterruptedException | ExecutionException exception) {
      throw exception;
    }
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
