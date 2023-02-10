package com.utpl.hospital.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "doctors")
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Doctor extends Person{
    private boolean enabled;
    private boolean active;
    private boolean isOnVacation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "specialization_detail",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    Set<Specialization> specializations;
    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;
    @OneToOne
    @JoinColumn(name="id_schedule")
    private Schedule schedule;
}
