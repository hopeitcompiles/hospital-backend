package com.utpl.hospital.model;


import lombok.*;

import javax.persistence.*;
@Entity(name = "medical_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;
}
