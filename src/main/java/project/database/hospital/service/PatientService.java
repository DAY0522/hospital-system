package project.database.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.database.hospital.Repository.PatientRepository;
import project.database.hospital.domain.Patient;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {
    private final PatientRepository patientRepository;

    public Patient findPatientById(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }
}
