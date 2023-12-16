package project.database.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.database.hospital.Repository.DoctorRepository;
import project.database.hospital.Repository.ExaminationRepository;
import project.database.hospital.Repository.PatientRepository;
import project.database.hospital.domain.Doctor;
import project.database.hospital.domain.Examination;
import project.database.hospital.domain.Patient;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExaminationService {
    private final ExaminationRepository examinationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public List<Examination> findByDoctorId(Integer doctorId) {
        return examinationRepository.findByDoctorId(doctorId);
    }

    @Transactional(readOnly = false)
    public void updateExamination(Integer id, String detail, String datetime) {
        System.out.printf("id: %d", id);
        Examination examination = examinationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 검사가 없습니다.")
        );

        examination.update(detail, LocalDateTime.parse(datetime));
    }

    @Transactional(readOnly = false)
    public void deleteExamination(Integer id) {
        Examination examination = examinationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 검사가 없습니다.")
        );

        examination.delete();
    }

    @Transactional(readOnly = false)
    public void addExamination(Integer doctorId, String name, String detail){

        Patient patient = patientRepository.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("해당 환자가 없습니다.")
        );

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new IllegalArgumentException("해당 의사가 없습니다.")
        );

        Examination examination = Examination.builder()
                .doctor(doctor)
                .detail(detail)
                .datetime(LocalDateTime.now())
                .isDeleted(false)
                .patient(patient)
                .build();

        examinationRepository.save(examination);
    }

    public List<Examination> findByPatientName(String name) {
        return examinationRepository.findByPatientName(name);
    }
}
