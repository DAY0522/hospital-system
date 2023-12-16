package project.database.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.database.hospital.Repository.DepartmentRepository;
import project.database.hospital.Repository.NurseRepository;
import project.database.hospital.domain.Department;
import project.database.hospital.domain.Doctor;
import project.database.hospital.domain.Nurse;
import project.database.hospital.dto.NurseResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NurseService {
    private final NurseRepository nurseRepository;
    private final DepartmentRepository departmentRepository;

    public Nurse findNurseById(Integer id) {
        return nurseRepository.findById(id).orElse(null);
    }

    public List<NurseResponseDto> getNurses() {
        return nurseRepository.findAll().stream()
                .map(NurseResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = false)
    public void updateNurse(Integer id, String name, String address, String phoneNumber, String departmentName) {
        Nurse nurse = nurseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 의사가 없습니다.")
        );

        Department department = departmentRepository.findByName(departmentName).orElseThrow(
                () -> new IllegalArgumentException("해당 부서가 없습니다.")
        );

        if (!nurse.getDepartment().getId().equals(department.getId())) {
            nurse.setDepartment(department);
        }

        nurse.update(name, address, phoneNumber);
    }

    @Transactional(readOnly = false)
    public void deleteNurse(Integer id) {
        Nurse nurse = nurseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 간호사가 없습니다.")
        );

        nurse.delete();
    }

    @Transactional(readOnly = false)
    public void addNurse(String name, String address, String phoneNumber, String password, String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow(
                () -> new IllegalArgumentException("해당 부서가 없습니다.")
        );

        Nurse nurse = Nurse.builder()
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .password(password)
                .isDeleted(false)
                .department(department)
                .build();

        nurseRepository.save(nurse);
    }
}
