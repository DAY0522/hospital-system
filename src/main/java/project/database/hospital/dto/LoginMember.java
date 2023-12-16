package project.database.hospital.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginMember {
    private Integer id;
    private String role;

    @Builder
    public LoginMember(Integer id, String role) {
        this.id = id;
        this.role = role;
    }
}
