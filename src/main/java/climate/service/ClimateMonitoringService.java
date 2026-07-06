package climate.service;

import service.AlertEmailService;
import service.ClimateAlertEvaluator;
import climate.model.ClimateRecord;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClimateMonitoringService {

  private static final Logger log = LoggerFactory.getLogger(ClimateMonitoringService.class);

  private final WeatherApiClient weatherApiClient;
  private final ClimateRecordService climateRecordService;
  private final ClimateAlertEvaluator climateAlertEvaluator;
  private final AlertEmailService alertEmailService;

  public ClimateMonitoringService(
      WeatherApiClient weatherApiClient,
      ClimateRecordService climateRecordService,
      ClimateAlertEvaluator climateAlertEvaluator,
      AlertEmailService alertEmailService
  ) {
    this.weatherApiClient = weatherApiClient;
    this.climateRecordService = climateRecordService;
    this.climateAlertEvaluator = climateAlertEvaluator;
    this.alertEmailService = alertEmailService;
  }

  public ClimateRecord fetchAndStoreCurrentWeather() {
    ClimateRecord record = climateRecordService.saveFromWeatherApi(weatherApiClient.fetchCurrentWeather());
    log.info(
        "Registro guardado: ubicacion={}, temperatura={}C, humedad={}%",
        record.getLocationName(),
        record.getTemperatureCelsius(),
        record.getHumidity()
    );
    return record;
  }

  public void analyzeLatestWeather() {
    climateRecordService.findLatest().ifPresentOrElse(this::analyzeRecord, () ->
        log.info("Todavia no hay registros climaticos para analizar"));
  }

  private void analyzeRecord(ClimateRecord climateRecord) {
    if (!climateAlertEvaluator.isCritical(climateRecord)) {
      log.info("Ultimo registro {} dentro de parametros normales", climateRecord.getId());
      return;
    }

    if (climateRecord.alertWasSent()) {
      log.info("El registro {} ya fue notificado previamente", climateRecord.getId());
      return;
    }

    alertEmailService.sendAlert(climateRecord);
    climateRecord.markAlertSent(LocalDateTime.now());
    climateRecordService.save(climateRecord);
    log.warn("Se detecto una alerta critica para el registro {}", climateRecord.getId());
  }
}
