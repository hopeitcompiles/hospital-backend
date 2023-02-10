package com.utpl.hospital.service;

import com.utpl.hospital.model.Doctor;
import com.utpl.hospital.model.Patient;
import com.utpl.hospital.repository.IDoctorRepository;
import com.utpl.hospital.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class PatientService extends GenericService<Patient, Long> {
	@Autowired
	private IPatientRepository repository;


	public CrudRepository<Patient, Long> getDao() {
		// TODO Auto-generated method stub
		return repository;
	}

	public Patient save(Patient element) {
		Patient ob;
		if(element.getId()==null ||element.getId()==0 ){
			ob=repository.save(element);

		}else{
			Patient actual=findById(element.getId());
			if(actual!=null){
				actual.setName(element.getName());
				actual.setLastName(element.getLastName());
				actual.setDni(element.getDni());
				actual.setAddress(element.getAddress());
				actual.setEnabled(element.isEnabled());
				actual.setBirthdate(element.getBirthdate());
				ob=repository.save(actual);
			}else{
				ob=null;
			}
		}
		return ob;
	}

	public Page<Patient> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}