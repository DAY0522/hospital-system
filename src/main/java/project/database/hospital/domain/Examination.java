package project.database.hospital.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false),
            @JoinColumn(name = "doctor_department_id", referencedColumnName = "department_id", nullable = false)
    })
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Builder
    public Examination(String detail, LocalDateTime datetime, Boolean isDeleted, Doctor doctor, Patient patient) {
        this.detail = detail;
        this.datetime = datetime;
        this.isDeleted = isDeleted;
        this.doctor = doctor;
        this.patient = patient;
    }

    public void update(String detail, LocalDateTime datetime) {
        this.detail = detail;
        this.datetime = datetime;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
