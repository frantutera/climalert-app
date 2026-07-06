package climate.service;

import climate.dto.WeatherApiCurrentResponse;
import config.ClimalertProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WeatherApiClient {

  private final RestClient restClient;
  private final ClimalertProperties properties;

  public WeatherApiClient(RestClient weatherRestClient, ClimalertProperties properties) {
    this.restClient = weatherRestClient;
    this.properties = properties;
  }

  public WeatherApiCurrentResponse fetchCurrentWeather() {
    return restClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/current.json")
            .queryParam("key", properties.weather().apiKey())
            .queryParam("q", properties.weather().location())
            .build())
        .retrieve()
        .body(WeatherApiCurrentResponse.class);
  }
}
