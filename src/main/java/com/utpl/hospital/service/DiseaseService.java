package com.utpl.hospital.service;

import com.utpl.hospital.model.Disease;
import com.utpl.hospital.repository.IDiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

//servicio para la nueva entidad Enfermedades
@Service
public class DiseaseService extends GenericService<Disease, Long> {
	@Autowired
	private IDiseaseRepository repository;


	public CrudRepository<Disease, Long> getDao() {
		// TODO Auto-generated method stub
		return repository;
	}

	public Disease save(Disease element) {
		if(element.getRegisterDate()==null){
			element.setRegisterDate(LocalDate.now());
		}
		Disease ob=repository.save(element);
		return ob;
	}
	public Page<Disease> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}