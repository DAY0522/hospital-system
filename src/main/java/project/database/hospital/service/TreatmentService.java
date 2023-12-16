package project.database.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.database.hospital.Repository.NurseRepository;
import project.database.hospital.Repository.PatientRepository;
import project.database.hospital.Repository.TreatmentRepository;
import project.database.hospital.domain.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;
    private final NurseRepository nurseRepository;

    public List<Treatment> findByNurseId(Integer nurseId) {
        return treatmentRepository.findByNurseId(nurseId);
    }

    @Transactional(readOnly = false)
    public void updateTreatment(Integer id, String detail, String datetime) {
        System.out.printf("id: %d", id);
        Treatment treatment = treatmentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 검사가 없습니다.")
        );

        treatment.update(detail, LocalDateTime.parse(datetime));
    }

    @Transactional(readOnly = false)
    public void deleteTreatment(Integer id) {
        Treatment treatment = treatmentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 검사가 없습니다.")
        );

        treatment.delete();
    }

    @Transactional(readOnly = false)
    public void addTreatment(Integer doctorId, String name, String detail){

        Patient patient = patientRepository.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("해당 환자가 없습니다.")
        );

        Nurse nurse = nurseRepository.findById(doctorId).orElseThrow(
                () -> new IllegalArgumentException("해당 의사가 없습니다.")
        );

        Treatment examination = Treatment.builder()
                .nurse(nurse)
                .detail(detail)
                .datetime(LocalDateTime.now())
                .isDeleted(false)
                .patient(patient)
                .build();

        treatmentRepository.save(examination);
    }

    public List<Treatment> findByPatientName(String name) {
        return treatmentRepository.findByPatientName(name);
    }
}
