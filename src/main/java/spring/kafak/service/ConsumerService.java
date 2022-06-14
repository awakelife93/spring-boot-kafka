package spring.kafak.service;

import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumerService {

  private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
  
  // todo: 추후 컨테이너별로 확장.
  private final String consumerContainerId = "tokenContainer";

  public Boolean start() {
    MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer(consumerContainerId);

    if(container.isRunning()) {
      return false;
    }
    
    container.start();
    return true;
  }

  public Boolean stop() {
    MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer(consumerContainerId);
    
    if(!container.isRunning()) {
      return false;
    }

    container.stop();
    return true;
  }

  public Boolean pause() {
    MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer(consumerContainerId);
    
    if(container.isContainerPaused()) {
      return false;
    }

    container.pause();
    return true;
  }

  public Boolean resume() {
    MessageListenerContainer container = kafkaListenerEndpointRegistry.getListenerContainer(consumerContainerId);
    
    if(!container.isContainerPaused()) {
      return false;
    }

    container.resume();
    return true;
  }
}
