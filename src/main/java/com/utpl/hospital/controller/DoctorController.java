package com.utpl.hospital.controller;

import com.utpl.hospital.model.Doctor;
import com.utpl.hospital.model.Specialization;
import com.utpl.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping("/doctor")
@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @GetMapping("/list")
    public ResponseEntity<Page<Doctor>> fetchDoctorsPaginated(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "") String search) {
        try {
            System.out.println("searching:"+search);
            page=page>0?page-1:0;
            Pageable paging = PageRequest.of(page, size);
            Page<Doctor> page_doctors;
            //page_users=userService.getAll(paging);
            page_doctors=doctorService.getAll(paging);
            return new ResponseEntity<>(page_doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('doctor:write')")
    public ResponseEntity<Doctor> registerDoctor(@RequestBody Doctor doctor) {
        System.out.println(doctor);

        try {
            if(doctor.getId()==0){
                doctor.setId(null);
            }
            Doctor saved_doctor=doctorService.save(doctor);
            return new ResponseEntity<>(saved_doctor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable(name = "id") String id) {
        try {
            Long specialization_id=Long.parseLong(id);
            doctorService.delete(specialization_id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable(name = "id") String id) {
        try {
            Long specialization_id=Long.parseLong(id);
            Doctor doctor=doctorService.findById(specialization_id);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{id}/specializations")
    @PreAuthorize("hasAuthority('doctor:write')")
    public ResponseEntity<Doctor> saveSpecializationsDoctor(@RequestBody List<Specialization> specializations,@PathVariable(name = "id") String id) {
        System.out.println(specializations);
        try {
            Long doctor_id=Long.parseLong(id);
            Doctor doctor=doctorService.findById(doctor_id);
            if(specializations.size()==0){
                doctor.setSpecializations(null);
            }else{
                Set<Specialization> set=new HashSet<>();
                for (Specialization specialization:specializations) {
                    set.add(specialization);
                }
                doctor.setSpecializations(set);
            }
            doctor=doctorService.save(doctor);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
