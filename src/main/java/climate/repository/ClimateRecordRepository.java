package climate.repository;

import climate.model.ClimateRecord;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClimateRecordRepository extends JpaRepository<ClimateRecord, Long> {

  Optional<ClimateRecord> findFirstByOrderByFetchedAtDescIdDesc();
}
