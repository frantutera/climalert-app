package climate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiCurrentResponse(
    Location location,
    Current current
) {

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Location(
      String name,
      String region,
      String country,
      @JsonProperty("localtime") String localTime
  ) {
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Current(
      @JsonProperty("temp_c") double temperatureCelsius,
      int humidity,
      Condition condition
  ) {
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Condition(
      String text
  ) {
  }
}
