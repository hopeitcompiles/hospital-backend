package com.utpl.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "appointments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long timeStart;
    private Long timeEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
    private AppointmentState state;
    private boolean isActive;
    private boolean enabled;
    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "id_specialization")
    private Specialization specialization;
    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @OneToOne(mappedBy = "appointment")
    private ScheduleEvent event;

}
