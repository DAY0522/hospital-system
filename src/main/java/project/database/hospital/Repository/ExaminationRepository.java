package project.database.hospital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.database.hospital.domain.Doctor;
import project.database.hospital.domain.Examination;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Integer> {

    List<Examination> findByDoctorId(Integer doctorId);
    @Query("SELECT e FROM Examination e WHERE e.patient.name = :name")
    List<Examination> findByPatientName(@Param("name") String name);
}
