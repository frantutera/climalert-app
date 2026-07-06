package climate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "climate_records")
public class ClimateRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String locationName;

  @Column(nullable = false)
  private String region;

  @Column(nullable = false)
  private String country;

  @Column(nullable = false)
  private LocalDateTime localObservationTime;

  @Column(nullable = false)
  private LocalDateTime fetchedAt;

  @Column(nullable = false)
  private double temperatureCelsius;

  @Column(nullable = false)
  private int humidity;

  @Column(nullable = false)
  private String conditionDescription;

  private LocalDateTime alertSentAt;

  public ClimateRecord(
      String locationName,
      String region,
      String country,
      LocalDateTime localObservationTime,
      LocalDateTime fetchedAt,
      double temperatureCelsius,
      int humidity,
      String conditionDescription
  ) {
    this.locationName = locationName;
    this.region = region;
    this.country = country;
    this.localObservationTime = localObservationTime;
    this.fetchedAt = fetchedAt;
    this.temperatureCelsius = temperatureCelsius;
    this.humidity = humidity;
    this.conditionDescription = conditionDescription;
  }

  public boolean alertWasSent() {
    return this.alertSentAt != null;
  }

  public void markAlertSent(LocalDateTime alertSentAt) {
    this.alertSentAt = alertSentAt;
  }
}
