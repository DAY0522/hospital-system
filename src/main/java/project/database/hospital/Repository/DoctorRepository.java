package project.database.hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.database.hospital.domain.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
