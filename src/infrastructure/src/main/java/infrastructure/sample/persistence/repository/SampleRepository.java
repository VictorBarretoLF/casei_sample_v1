package infrastructure.sample.persistence.repository;

import infrastructure.sample.persistence.table.SampleTable;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<SampleTable, UUID> {
}
