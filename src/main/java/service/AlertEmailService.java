package service;

import climate.model.ClimateRecord;
import config.ClimalertProperties;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AlertEmailService {

  private static final Logger log = LoggerFactory.getLogger(AlertEmailService.class);

  private final JavaMailSender javaMailSender;
  private final ClimalertProperties properties;
  @Value("${spring.mail.username:}")
  private String sender;

  public AlertEmailService(JavaMailSender javaMailSender, ClimalertProperties properties) {
    this.javaMailSender = javaMailSender;
    this.properties = properties;
  }

  public void sendAlert(ClimateRecord climateRecord) {
    List<String> recipients = properties.mail().recipients();
    SimpleMailMessage message = new SimpleMailMessage();
    if (!sender.isBlank()) {
      message.setFrom(sender);
    }
    message.setTo(recipients.toArray(String[]::new));
    message.setSubject("Alerta meteorologica - Climalert");
    message.setText(buildBody(climateRecord));
    javaMailSender.send(message);
    log.info("Alerta enviada a {} destinatarios para registro {}", recipients.size(), climateRecord.getId());
  }

  private String buildBody(ClimateRecord climateRecord) {
    return """
        Se detectaron condiciones climaticas criticas.

        Ubicacion: %s, %s, %s
        Hora local observada: %s
        Momento de consulta: %s
        Temperatura: %.1f C
        Humedad: %d%%
        Condicion: %s
        """
        .formatted(
            climateRecord.getLocationName(),
            climateRecord.getRegion(),
            climateRecord.getCountry(),
            climateRecord.getLocalObservationTime(),
            climateRecord.getFetchedAt(),
            climateRecord.getTemperatureCelsius(),
            climateRecord.getHumidity(),
            climateRecord.getConditionDescription()
        );
  }
}
