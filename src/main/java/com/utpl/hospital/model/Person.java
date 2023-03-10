package com.utpl.hospital.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "people")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String lastName;
    private String dni;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private Gender gender;
    private String address;
    private String phone;
    private String image;
    @OneToOne
    @JoinColumn(name="id_user")
    private User user;
    private boolean active;
    private boolean enabled;
    public int getAge(){
        if(birthdate!=null){
            Period edad = Period.between(birthdate, LocalDate.now());
            return edad.getYears();
        }
        return 0;
    }
}
