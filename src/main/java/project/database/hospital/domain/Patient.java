package project.database.hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String socialSecurityNumber;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String bloodType;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            @JoinColumn(name = "doctor_department_id", referencedColumnName = "department_id")
    })
    private Doctor doctor;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "nurse_id", referencedColumnName = "id"),
            @JoinColumn(name = "nurse_department_id", referencedColumnName = "department_id")
    })
    private Nurse nurse;
}
