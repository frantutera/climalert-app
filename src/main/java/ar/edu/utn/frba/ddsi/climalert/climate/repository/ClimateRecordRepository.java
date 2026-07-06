package ar.edu.utn.frba.ddsi.climalert.climate.repository;

import ar.edu.utn.frba.ddsi.climalert.climate.model.ClimateRecord;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClimateRecordRepository extends JpaRepository<ClimateRecord, Long> {

  Optional<ClimateRecord> findFirstByOrderByFetchedAtDescIdDesc();
}
