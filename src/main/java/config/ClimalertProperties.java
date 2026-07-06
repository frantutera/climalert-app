package config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "climalert")
public record ClimalertProperties(
    @NotNull Weather weather,
    @NotNull Alert alert,
    @NotNull Mail mail
) {

  public record Weather(
      @NotBlank(message = "climalert.weather.api-key must be set. Provide WEATHER_API_KEY in the environment.")
      String apiKey,
      @NotBlank String baseUrl,
      @NotBlank String location
  ) {
  }

  public record Alert(
      double maxTemperatureCelsius,
      int maxHumidityPercentage
  ) {
  }

  public record Mail(
      @NotEmpty List<@NotBlank String> recipients
  ) {
  }
}
