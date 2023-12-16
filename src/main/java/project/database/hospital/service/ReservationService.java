package project.database.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.database.hospital.Repository.DepartmentRepository;
import project.database.hospital.Repository.PatientRepository;
import project.database.hospital.Repository.ReservationRepository;
import project.database.hospital.domain.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;

    public List<Reservation> findByPatientId(Integer patientId) {
        return reservationRepository.findByPatientId(patientId);
    }

    @Transactional(readOnly = false)
    public void deleteReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 검사가 없습니다.")
        );

        reservation.delete();
    }

    @Transactional(readOnly = false)
    public void addReservation(Integer patientId, String departmentName, String datetime){

        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new IllegalArgumentException("해당 환자가 없습니다.")
        );

        Department department = departmentRepository.findByName(departmentName).orElseThrow(
                () -> new IllegalArgumentException("해당 부서가 없습니다.")
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(datetime, formatter);

        Reservation reservation = Reservation.builder()
                .department(department)
                .patient(patient)
                .datetime(localDateTime)
                .isDeleted(false)
                .build();

        reservationRepository.save(reservation);
    }
}
