package config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(ClimalertProperties.class)
public class HttpClientConfig {

  @Bean
  public RestClient weatherRestClient(RestClient.Builder builder, ClimalertProperties properties) {
    return builder.baseUrl(properties.weather().baseUrl()).build();
  }
}
