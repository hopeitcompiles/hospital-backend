package com.utpl.hospital.controller;

import com.utpl.hospital.model.Schedule;
import com.utpl.hospital.model.Specialization;
import com.utpl.hospital.service.ScheduleService;
import com.utpl.hospital.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/schedule")
@RestController
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @GetMapping("/list")
    public ResponseEntity<Page<Schedule>> fetchSpecializationPaginated(@RequestParam(defaultValue = "1") int page,
                                                                             @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "") String search) {
        try {
            System.out.println("searching:"+search);
            page=page>0?page-1:0;
            Pageable paging = PageRequest.of(page, size);
            Page<Schedule> page_schedules;
            page_schedules=scheduleService.getAll(paging);
            return new ResponseEntity<>(page_schedules, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<Schedule>> fetchAllSpecialization() {
        try {
           List<Schedule> schedules=scheduleService.getAll();
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('schedule:write')")
    public ResponseEntity<Schedule> registerSpecialization(@RequestBody Schedule schedule) {
        try {
            if(schedule.getId()==0){
                schedule.setId(null);
            }
            Schedule saved_schedule=scheduleService.save(schedule);
            return new ResponseEntity<>(saved_schedule, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('schedule:delete')")
    public ResponseEntity<String> deleteSchedule(@PathVariable(name = "id") String id) {
        try {
            Long specialization_id=Long.parseLong(id);
            scheduleService.delete(specialization_id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable(name = "id") String id) {
        try {
            Long specialization_id=Long.parseLong(id);
            Schedule schedule=scheduleService.findById(specialization_id);
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
