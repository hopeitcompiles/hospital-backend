package com.utpl.hospital.controller;

import com.utpl.hospital.model.Disease;
import com.utpl.hospital.model.Doctor;
import com.utpl.hospital.model.Patient;
import com.utpl.hospital.model.User;
import com.utpl.hospital.service.DiseaseService;
import com.utpl.hospital.service.PatientService;
import com.utpl.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//controlador para el manejo de la nueva entidad Enfermedades
@RequestMapping("/patient")
@RestController
public class DiseaseController {
    //injección del servicio de pacientes para persistencia de datos
    @Autowired
    private PatientService patientService;
    //injección del servicio de enfermedades para persistencia de datos
    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private UserService userService;
    @PostMapping("/disease/register")
    public ResponseEntity<Patient> registerDisease(@RequestBody Disease disease,
                                                  Authentication authentication) {
        System.out.println("saving"+disease);
        try {
            if(disease.getId()==0){
                disease.setId(null);
            }
            User user = userService.findByEmail(authentication.getName());
            Patient patient=patientService.findById(user.getPerson().getId());
            disease.setPatient(patient);
            Disease disease1=diseaseService.save(disease);
            patient.getDiseases().add(disease1);
            Patient saved=patientService.save(patient);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/disease/delete/{id}")
    public ResponseEntity<String> deleteDisease(@PathVariable(name = "id") String id,
                                                   Authentication authentication){
        try {
            Long id_delete=Long.parseLong(id);
            diseaseService.delete(id_delete);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
