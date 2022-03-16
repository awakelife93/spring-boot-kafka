package spring.kafak.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String topic, String message) {
    this.kafkaTemplate.send(topic, message);
  }
}
