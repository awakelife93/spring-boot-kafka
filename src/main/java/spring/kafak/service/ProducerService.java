package spring.kafak.service;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class ProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String topic, String message) throws InterruptedException, ExecutionException {
    ListenableFuture<SendResult<String, String>> listenable = this.kafkaTemplate.send(topic, message);
    RecordMetadata metadata = listenable.get().getRecordMetadata();
    System.out.println(metadata.offset() + " " + metadata.partition());
  }
}
