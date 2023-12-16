package project.database.hospital.dto;

import lombok.Getter;
import project.database.hospital.domain.Department;
import project.database.hospital.domain.Doctor;

@Getter
public class DoctorResponseDto {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private String password;
    private Boolean isDeleted;
    private Department department;

    public DoctorResponseDto(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.address = doctor.getAddress();
        this.phoneNumber = doctor.getPhoneNumber();
        this.password = doctor.getPassword();
        this.isDeleted = doctor.getIsDeleted();
        this.department = doctor.getDepartment();
    }
}
