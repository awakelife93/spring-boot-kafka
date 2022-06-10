package spring.kafak.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.kafak.component.Producer;

@Service
@RequiredArgsConstructor
public class ProducerService {
  
  private final Producer producer;

  public String sendMessage(String topic, String message) throws InterruptedException, ExecutionException {
    try {
      long offset = producer.sendMessage(topic, message);
      return "completed send message= " + message + " offset= " + offset;
    } catch (InterruptedException | ExecutionException exception) {
      throw exception;
    }
  }
}
