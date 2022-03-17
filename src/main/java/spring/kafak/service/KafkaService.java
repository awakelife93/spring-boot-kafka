package spring.kafak.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.kafak.component.Producer;

@Service
@RequiredArgsConstructor
public class KafkaService {

  private final Producer producer;

  public String publisher(String topic, String message) {
    producer.sendMessage(topic, message);
    return "completed send message";
  }
}
