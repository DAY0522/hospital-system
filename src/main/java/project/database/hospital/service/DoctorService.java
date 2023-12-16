package project.database.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.database.hospital.Repository.DepartmentRepository;
import project.database.hospital.Repository.DoctorRepository;
import project.database.hospital.domain.Department;
import project.database.hospital.domain.Doctor;
import project.database.hospital.dto.DoctorResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public Doctor findDoctorById(Integer id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<DoctorResponseDto> getDoctors() {
        return doctorRepository.findAll().stream()
                .map(DoctorResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = false)
    public void updateDoctor(Integer id, String name, String address, String phoneNumber, String departmentName) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 의사가 없습니다.")
        );

        Department department = departmentRepository.findByName(departmentName).orElseThrow(
                () -> new IllegalArgumentException("해당 부서가 없습니다.")
        );

        if (!doctor.getDepartment().getId().equals(department.getId())) {
            doctor.setDepartment(department);
        }

        doctor.update(name, address, phoneNumber);
    }

    @Transactional(readOnly = false)
    public void deleteDoctor(Integer id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 의사가 없습니다.")
        );

        doctor.delete();
    }

    @Transactional(readOnly = false)
    public void addDoctor(String name, String address, String phoneNumber, String password, String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow(
                () -> new IllegalArgumentException("해당 부서가 없습니다.")
        );

        Doctor doctor = Doctor.builder()
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .password(password)
                .isDeleted(false)
                .department(department)
                .build();

        doctorRepository.save(doctor);
    }
}
