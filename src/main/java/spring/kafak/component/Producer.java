package spring.kafak.component;

import java.util.concurrent.ExecutionException;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Producer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String topic, String message) {
    try {
      this.kafkaTemplate.send(topic, message).get();
    } catch (InterruptedException | ExecutionException exception) {
      // exception 하나 만들기
    }
  }
}
