package ar.edu.utn.frba.ddsi.climalert.alert.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ar.edu.utn.frba.ddsi.climalert.climate.model.ClimateRecord;
import ar.edu.utn.frba.ddsi.climalert.config.ClimalertProperties;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

class ClimateAlertEvaluatorTest {

  private final ClimateAlertEvaluator evaluator = new ClimateAlertEvaluator(
      new ClimalertProperties(
          new ClimalertProperties.Weather("key", "https://api.weatherapi.com/v1", "CABA"),
          new ClimalertProperties.Alert(35, 60),
          new ClimalertProperties.Mail(List.of("admin@clima.com"))
      )
  );

  @Test
  void shouldReturnTrueWhenTemperatureAndHumidityExceedThreshold() {
    ClimateRecord record = new ClimateRecord(
        "CABA",
        "Buenos Aires",
        "Argentina",
        LocalDateTime.now(),
        LocalDateTime.now(),
        36.5,
        61,
        "Soleado"
    );

    assertTrue(evaluator.isCritical(record));
  }

  @Test
  void shouldReturnFalseWhenOnlyOneThresholdIsExceeded() {
    ClimateRecord record = new ClimateRecord(
        "CABA",
        "Buenos Aires",
        "Argentina",
        LocalDateTime.now(),
        LocalDateTime.now(),
        36.5,
        50,
        "Soleado"
    );

    assertFalse(evaluator.isCritical(record));
  }
}
