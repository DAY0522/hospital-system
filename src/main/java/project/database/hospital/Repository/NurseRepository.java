package project.database.hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.database.hospital.domain.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Integer> {
}
