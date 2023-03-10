package com.utpl.hospital.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

///NUEVA ENTIDAD
///RELACION CON PACIENTES
@Entity(name = "diseases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    //nombre de la enfermedad
    private String name;
    //posible comentario del paciente
    private String patientComment;
    //comentarios
    private Boolean isActive;
    private String comments;
    //fecha de diagnostico

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate diagnosticDate;
    //fecha de registro, esta se agrega automaticamente al crear el dato
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
    //relacion entre paciente y enfermedad, muchos a uno
    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;
}
