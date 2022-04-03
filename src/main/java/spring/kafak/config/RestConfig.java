package spring.kafak.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

  private HttpClient generateHttpClient() {
    return HttpClientBuilder.create()
        .setMaxConnTotal(50)
        .setMaxConnPerRoute(20).build();
  }

  private HttpComponentsClientHttpRequestFactory generateFactory() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

    factory.setReadTimeout(5000);
    factory.setConnectTimeout(3000);
    factory.setHttpClient(generateHttpClient());

    return factory;
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate(generateFactory());
  }
}