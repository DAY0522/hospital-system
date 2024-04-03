package project.database.hospital.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id", nullable = false)
    private Nurse nurse;

    @Builder
    public Treatment(LocalDateTime datetime, String detail, Boolean isDeleted, Patient patient, Nurse nurse) {
        this.datetime = datetime;
        this.detail = detail;
        this.isDeleted = isDeleted;
        this.patient = patient;
        this.nurse = nurse;
    }

    public void update(String detail, LocalDateTime datetime) {
        this.detail = detail;
        this.datetime = datetime;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
