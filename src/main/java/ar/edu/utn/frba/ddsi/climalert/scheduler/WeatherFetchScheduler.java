package ar.edu.utn.frba.ddsi.climalert.scheduler;

import ar.edu.utn.frba.ddsi.climalert.climate.service.ClimateMonitoringService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherFetchScheduler {

  private final ClimateMonitoringService climateMonitoringService;

  public WeatherFetchScheduler(ClimateMonitoringService climateMonitoringService) {
    this.climateMonitoringService = climateMonitoringService;
  }

  @Scheduled(fixedDelayString = "${climalert.scheduler.fetch-delay-ms:300000}")
  public void fetchCurrentWeather() {
    climateMonitoringService.fetchAndStoreCurrentWeather();
  }
}
