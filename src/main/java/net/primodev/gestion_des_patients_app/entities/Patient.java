package net.primodev.gestion_des_patients_app.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 4,max = 30)
    @Column(length = 30)
    private String lastName;
    @NotBlank
    @Size(min = 4,max = 30)
    @Column(length = 30)
    private String firstName;
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    private Boolean isSick;
}
