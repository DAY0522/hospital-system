package project.database.hospital.dto;

import lombok.Getter;
import project.database.hospital.domain.Department;
import project.database.hospital.domain.Nurse;

@Getter
public class NurseResponseDto {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private String password;
    private Boolean isDeleted;
    private Department department;

    public NurseResponseDto(Nurse nurse) {
        this.id = nurse.getId();
        this.name = nurse.getName();
        this.address = nurse.getAddress();
        this.phoneNumber = nurse.getPhoneNumber();
        this.password = nurse.getPassword();
        this.isDeleted = nurse.getIsDeleted();
        this.department = nurse.getDepartment();
    }
}
