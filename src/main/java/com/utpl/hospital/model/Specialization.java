package com.utpl.hospital.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity(name = "specializations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Specialization implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private String image;
    private boolean enabled;
    private boolean active;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
    @ManyToMany(mappedBy = "specializations")
    private Set<Doctor> doctors;
    @OneToMany(mappedBy = "specialization")
    private Set<Appointment> appointments;
}
