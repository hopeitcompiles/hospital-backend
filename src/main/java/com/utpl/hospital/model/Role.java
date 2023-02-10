package com.utpl.hospital.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.utpl.hospital.authentication.ApplicationRole;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.beans.Transient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String color;
    @Setter(AccessLevel.NONE)
    private int level;
    @Setter(AccessLevel.NONE)
    @Column(unique=true)
    private ApplicationRole role;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> users;
    @Transient
    public void setRole(ApplicationRole role){
        this.role=role;
        this.level=role.getLevel();
    }
    public Set<String> getClaims(){
        Set<String> values=new HashSet<>();
        role.getGrantedAuthorities().forEach(simpleGrantedAuthority ->values.add(simpleGrantedAuthority.getAuthority()));
        return  values;
    }
}
