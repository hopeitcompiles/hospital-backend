package com.utpl.hospital.service;

import com.utpl.hospital.authentication.ApplicationRole;
import com.utpl.hospital.model.Doctor;
import com.utpl.hospital.model.Person;
import com.utpl.hospital.model.Role;
import com.utpl.hospital.model.User;
import com.utpl.hospital.repository.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class DoctorService extends GenericService<Doctor, Long> {
	@Autowired
	private IDoctorRepository repository;
	@Autowired
	private RoleService roleService;


	public CrudRepository<Doctor, Long> getDao() {
		// TODO Auto-generated method stub
		return repository;
	}

	public Doctor save(Doctor element) {
		if(element.getRegisterDate()==null){
			element.setRegisterDate(LocalDate.now());
		}
		Doctor ob;
		if(element.getId()==null ||element.getId()==0 ){
			ob=repository.save(element);

		}else{
			Doctor actual=findById(element.getId());
			if(actual!=null){
				actual.setName(element.getName());
				actual.setLastName(element.getLastName());
				actual.setDni(element.getDni());
				actual.setAddress(element.getAddress());
				actual.setEnabled(element.isEnabled());
				actual.setBirthdate(element.getBirthdate());
				actual.setStartDate(element.getStartDate());
				ob=repository.save(actual);
			}else{
				ob=null;
			}
		}
		return ob;
	}

	public Page<Doctor> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}