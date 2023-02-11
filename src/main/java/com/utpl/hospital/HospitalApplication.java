package com.utpl.hospital;

import com.utpl.hospital.authentication.ApplicationRole;
import com.utpl.hospital.model.Doctor;
import com.utpl.hospital.model.Role;
import com.utpl.hospital.model.Specialization;
import com.utpl.hospital.model.User;
import com.utpl.hospital.repository.IDoctorRepository;
import com.utpl.hospital.service.RoleService;
import com.utpl.hospital.service.SpecializationService;
import com.utpl.hospital.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.Color;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class HospitalApplication {
	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserService userService, SpecializationService specializationService, RoleService roleService, IDoctorRepository doctorService) {
		return args -> {
			List<Role> roles=roleService.getAll();
			if(roles.isEmpty()){
				Role admin=new Role();
				admin.setId(1L);
				admin.setRole(ApplicationRole.ADMIN);
				admin.setName("Administrador");
				admin.setColor(Color.RED.toString());
				Role n=roleService.save(admin);
				Role patient=new Role();
				patient.setName("Paciente");
				patient.setId(2L);
				patient.setRole(ApplicationRole.PATIENT);
				roleService.save(patient);
				admin.setColor(Color.GREEN.toString());
				User user=new User() ;
				user.setId(1L);
				user.setRole(n);
				user.setEmail("admin@admin.com");
				user.setPassword("admin");
				user=userService.save(user);
				Specialization specialization=new Specialization();
				specialization.setId(1L);
				specialization.setDescription("Created on Command Line Runner");
				specialization.setName("Medicina General");
				Specialization specialization2=new Specialization();
				specialization2.setId(2L);
				specialization2.setDescription("Created on Command Line Runner");
				specialization2.setName("Dermatologìa");

				Doctor doctor=new Doctor();
				doctor.setId(1L);
				doctor.setDni("1314719608");
				doctor.setActive(true);
				doctor.setName("Raúl");
				doctor.setLastName("Mina");
				doctor.setUser(user);
				specialization=specializationService.save(specialization);
				specialization2=specializationService.save(specialization2);
				HashSet<Specialization> set=new HashSet<>();
				set.add(specialization2);
				set.add(specialization);
				doctor.setSpecializations(set);
				doctorService.save(doctor);
			}
		};
	}
}