package com.utpl.hospital.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(mappedBy = "schedule")
    private Doctor doctor;
    @OneToMany(mappedBy = "schedule",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ScheduleEvent> events;
}
