package com.utpl.hospital.controller;

import com.utpl.hospital.model.Specialization;
import com.utpl.hospital.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/specialization")
@RestController
public class SpecializationController {
    @Autowired
    SpecializationService specializationService;
    @GetMapping("/list")
    public ResponseEntity<Page<Specialization>> fetchSpecializationPaginated(@RequestParam(defaultValue = "1") int page,
                                                                             @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "") String search) {
        try {
            System.out.println("searching:"+search);
            page=page>0?page-1:0;
            Pageable paging = PageRequest.of(page, size);
            Page<Specialization> page_specailizations;
            page_specailizations=specializationService.getAll(paging);
//            List<Specialization> spe=specializationService.getAll();
//            System.out.println(spe);
            return new ResponseEntity<>(page_specailizations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<Specialization>> fetchAllSpecialization() {
        try {
           List<Specialization> specializations=specializationService.getAll();
            return new ResponseEntity<>(specializations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('patient:write')")
    public ResponseEntity<Specialization> registerSpecialization(@RequestBody Specialization specialization) {
        try {
            if(specialization.getId()==0){
                specialization.setId(null);
            }
            Specialization saved_specialization=specializationService.save(specialization);
            return new ResponseEntity<>(saved_specialization, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpecialization(@PathVariable(name = "id") String id) {
        try {
            Long specialization_id=Long.parseLong(id);
            specializationService.delete(specialization_id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Specialization> getSpecialization(@PathVariable(name = "id") String id) {
        try {
            Long specialization_id=Long.parseLong(id);
            Specialization specialization=specializationService.findById(specialization_id);
            return new ResponseEntity<>(specialization, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
