package ar.edu.utn.frba.ddsi.climalert.climate.service;

import ar.edu.utn.frba.ddsi.climalert.climate.dto.WeatherApiCurrentResponse;
import ar.edu.utn.frba.ddsi.climalert.climate.model.ClimateRecord;
import ar.edu.utn.frba.ddsi.climalert.climate.repository.ClimateRecordRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ClimateRecordService {

  private static final DateTimeFormatter WEATHER_API_LOCAL_TIME =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  private final ClimateRecordRepository climateRecordRepository;

  public ClimateRecordService(ClimateRecordRepository climateRecordRepository) {
    this.climateRecordRepository = climateRecordRepository;
  }

  public ClimateRecord saveFromWeatherApi(WeatherApiCurrentResponse response) {
    WeatherApiCurrentResponse.Location location = response.location();
    WeatherApiCurrentResponse.Current current = response.current();

    ClimateRecord record = new ClimateRecord(
        location.name(),
        location.region(),
        location.country(),
        LocalDateTime.parse(location.localTime(), WEATHER_API_LOCAL_TIME),
        LocalDateTime.now(),
        current.temperatureCelsius(),
        current.humidity(),
        current.condition() != null ? current.condition().text() : "Sin descripcion"
    );

    return climateRecordRepository.save(record);
  }

  public Optional<ClimateRecord> findLatest() {
    return climateRecordRepository.findFirstByOrderByFetchedAtDescIdDesc();
  }

  public ClimateRecord save(ClimateRecord climateRecord) {
    return climateRecordRepository.save(climateRecord);
  }
}
