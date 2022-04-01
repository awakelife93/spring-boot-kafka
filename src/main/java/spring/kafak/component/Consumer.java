package spring.kafak.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import lombok.RequiredArgsConstructor;
import spring.kafak.component.utils.HttpUtils;

@Component
@RequiredArgsConstructor
public class Consumer {
  private final HttpUtils httpUtils;

  @Value("${endpoint.deleteUserToken}")
  private String deleteUserTokenEndPoint;

  @KafkaListener(id = "tokenContainer", topics = "tokenStorage", groupId = "${kafka.groupId}", autoStartup = "false")
  public void listenMessage(String message) {
    System.out.println("Received Message : " + message);
    sendMessageToActionServer(message);
  }

  private void sendMessageToActionServer(String message) {
    try {
      System.out.println("Send Message To Action Server : " + message);
      Map<String, Object> response = new HashMap<String, Object>();

      MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

      parameters.add("params", message);

      response = httpUtils.request(HttpMethod.POST, deleteUserTokenEndPoint, parameters);

      System.out.println("status : " + response.get("status") + "data : " + response.get("data") );
    } catch (RestClientException exception) {
      System.out.println("RestClientException : " + exception.getMessage());
      throw exception;
    }
  }
}
