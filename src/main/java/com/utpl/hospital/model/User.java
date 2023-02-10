package com.utpl.hospital.model;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String password;
    private boolean enabled;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
    @OneToOne(mappedBy = "user")
    private Person person;


}
