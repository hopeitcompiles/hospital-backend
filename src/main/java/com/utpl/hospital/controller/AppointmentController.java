package com.utpl.hospital.controller;

import com.utpl.hospital.model.Appointment;
import com.utpl.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @GetMapping("/list")
    public ResponseEntity<Page<Appointment>> fetchAppointmentsPaginated(@RequestParam(defaultValue = "1") int page,
                                                                          @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "") String search) {
        try {
            System.out.println("searching:"+search);
            page=page>0?page-1:0;
            Pageable paging = PageRequest.of(page, size);
            Page<Appointment> pages;
            pages=appointmentService.getAll(paging);
            return new ResponseEntity<>(pages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<Appointment>> fetchAllAppointments() {
        try {
           List<Appointment> appointments=appointmentService.getAll();
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('appointment:write')")
    public ResponseEntity<Appointment> registerAppointment(@RequestBody Appointment element) {
        try {
            if(element.getId()==0){
                element.setId(null);
            }
            Appointment saved=appointmentService.save(element);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('appointment:delete')")
    public ResponseEntity<String> deleteAppointment(@PathVariable(name = "id") String id) {
        try {
            Long delete_id=Long.parseLong(id);
            appointmentService.delete(delete_id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getSchedule(@PathVariable(name = "id") String id) {
        try {
            Long element_id=Long.parseLong(id);
            Appointment element=appointmentService.findById(element_id);
            return new ResponseEntity<>(element, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
