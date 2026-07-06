import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"climate", "config", "scheduler", "service"})
@EntityScan("climate.model")
@EnableJpaRepositories("climate.repository")
public class ClimalertApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClimalertApplication.class, args);
  }
}
