package project.database.hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.database.hospital.domain.Inpatient;

@Repository
public interface InpatientRepository extends JpaRepository<Inpatient, Integer> {
}
