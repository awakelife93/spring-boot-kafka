package spring.kafak.component;

import java.util.concurrent.ExecutionException;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Producer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public long sendMessage(String topic, String message) throws InterruptedException, ExecutionException {
    try {
      SendResult<String, String> sendResult = this.kafkaTemplate.send(topic, message).get();
      return sendResult.getRecordMetadata().offset();
    } catch (InterruptedException | ExecutionException exception) {
      System.out.println("sendMessage" + exception);
      throw exception;
    }
  }
}
