package project.database.hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.database.hospital.domain.Examination;
import project.database.hospital.domain.Treatment;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
    List<Treatment> findByNurseId(Integer nurseId);
    @Query("SELECT t FROM Treatment t WHERE t.patient.name = :name")
    List<Treatment> findByPatientName(@Param("name") String name);
}
