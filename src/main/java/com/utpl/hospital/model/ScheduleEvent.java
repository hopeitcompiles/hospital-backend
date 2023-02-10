package com.utpl.hospital.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Schedule schedule;
    @OneToOne
    @JoinColumn(name = "appointment_id",nullable = true)
    private Appointment appointment;
}
