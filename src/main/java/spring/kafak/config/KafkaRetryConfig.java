package spring.kafak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

// todo: retry 적용
@Configuration
public class KafkaRetryConfig {

  @Bean
  private RetryTemplate retryTemplate() {
    RetryTemplate retryTemplate = new RetryTemplate();
    // 재시도시 1초 후에 재 시도하도록 backoff delay 시간을 설정한다.
    FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
    fixedBackOffPolicy.setBackOffPeriod(1000L);
    retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
    // 최대 재시도 횟수 설정
    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
    retryPolicy.setMaxAttempts(2);
    retryTemplate.setRetryPolicy(retryPolicy);
    return retryTemplate;
  }
}
