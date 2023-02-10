package com.utpl.hospital.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "patients")
@Getter
@Setter
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Patient extends Person{
    private boolean isDonor;
    private boolean isAlive;
    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;
    @OneToOne(mappedBy = "patient")
    private MedicalHistory medicalHistory;
}
