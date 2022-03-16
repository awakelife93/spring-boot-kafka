package spring.kafak.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String topic, String message) {
    ListenableFuture<SendResult<String, String>> listenable = this.kafkaTemplate.send(topic, message);
    listenable.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

      @Override
      public void onSuccess(SendResult<String, String> result) {
      }

      @Override
      public void onFailure(Throwable ex) {
      }
    });
  }
}
