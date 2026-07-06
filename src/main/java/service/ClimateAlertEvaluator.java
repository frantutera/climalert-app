package service;

import climate.model.ClimateRecord;
import config.ClimalertProperties;
import org.springframework.stereotype.Component;

@Component
public class ClimateAlertEvaluator {

  private final ClimalertProperties properties;

  public ClimateAlertEvaluator(ClimalertProperties properties) {
    this.properties = properties;
  }

  public boolean isCritical(ClimateRecord climateRecord) {
    return climateRecord.getTemperatureCelsius() > properties.alert().maxTemperatureCelsius()
        && climateRecord.getHumidity() > properties.alert().maxHumidityPercentage();
  }
}
