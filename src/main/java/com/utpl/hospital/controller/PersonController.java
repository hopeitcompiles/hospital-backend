package com.utpl.hospital.controller;

import com.utpl.hospital.model.Doctor;
import com.utpl.hospital.model.Person;
import com.utpl.hospital.service.DoctorService;
import com.utpl.hospital.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/person")
@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/list")
    public ResponseEntity<Page<Person>> fetchDoctors(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "") String search) {
        try {
            System.out.println("searching:" + search);
            page = page > 0 ? page - 1 : 0;
            Pageable paging = PageRequest.of(page, size);
            Page<Person> page_people;
            //page_users=userService.getAll(paging);
            page_people = personService.getAll(paging);
            return new ResponseEntity<>(page_people, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

