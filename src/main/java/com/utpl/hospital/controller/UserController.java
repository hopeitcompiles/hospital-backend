package com.utpl.hospital.controller;

import com.utpl.hospital.model.*;
import com.utpl.hospital.service.PatientService;
import com.utpl.hospital.service.RoleService;
import com.utpl.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PatientService patientService;
    @GetMapping("")
    public ResponseEntity<String> home(){
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to Hospital de la Zona de El Oro");
    }
    @GetMapping("/whoami")
    public User whoAmI(Authentication authentication){
        System.out.println("who am i");
        User user = userService.findByEmail(authentication.getName());
        return user;
    }
    @PostMapping("/register")
    public ResponseEntity<Patient> registerDoctor(@RequestBody Patient patient) {
        System.out.println(patient);
        if(patient.getId()==null||patient.getId()==0L){
            patient.setId(null);
            Role role=roleService.findById(2L);
            System.out.println(role);
            User user=new User();
            user.setRole(role);
            user.setEmail(patient.getUser().getEmail());
            user.setPassword(patient.getUser().getPassword());
            user=userService.save(user);
            System.out.println(user);
            patient.setUser(user);
        }
        try {

            Patient saved=patientService.save(patient);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
