package net.primodev.gestion_des_patients_app.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String last_name;
    @Column(length = 30)
    private String first_name;
    @Temporal(TemporalType.DATE)
    private Date birth_day;
    private Boolean isSick;


}
