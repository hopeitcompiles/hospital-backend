package com.utpl.hospital.controller;

import com.utpl.hospital.model.Doctor;
import com.utpl.hospital.model.Patient;
import com.utpl.hospital.service.DoctorService;
import com.utpl.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/patient")
@RestController
public class PatientController {
    @Autowired
    PatientService patientService;
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('patient:read')")
    public ResponseEntity<Page<Patient>> fetchPatientsPaginated(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "") String search) {
        try {
            System.out.println("searching:"+search);
            page=page>0?page-1:0;
            Pageable paging = PageRequest.of(page, size);
            Page<Patient> pages;
            //page_users=userService.getAll(paging);
            pages=patientService.getAll(paging);
            return new ResponseEntity<>(pages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('patient:write')")
    @PostMapping("/register")
    public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient) {
        try {
            if(patient.getId()==0){
                patient.setId(null);
            }
            Patient saved_doctor=patientService.save(patient);
            return new ResponseEntity<>(saved_doctor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable(name = "id") String id) {
        try {
            Long specialization_id=Long.parseLong(id);
            patientService.delete(specialization_id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
