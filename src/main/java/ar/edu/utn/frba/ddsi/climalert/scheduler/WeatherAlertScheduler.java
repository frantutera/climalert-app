package ar.edu.utn.frba.ddsi.climalert.scheduler;

import ar.edu.utn.frba.ddsi.climalert.climate.service.ClimateMonitoringService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherAlertScheduler {

  private final ClimateMonitoringService climateMonitoringService;

  public WeatherAlertScheduler(ClimateMonitoringService climateMonitoringService) {
    this.climateMonitoringService = climateMonitoringService;
  }

  @Scheduled(fixedDelayString = "${climalert.scheduler.analysis-delay-ms:60000}")
  public void analyzeLatestWeather() {
    climateMonitoringService.analyzeLatestWeather();
  }
}
