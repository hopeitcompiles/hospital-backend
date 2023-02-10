package com.utpl.hospital.service;

import com.utpl.hospital.model.Specialization;
import com.utpl.hospital.repository.ISpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class SpecializationService extends GenericService<Specialization, Long> {
	@Autowired
	private ISpecializationRepository repository;


	public CrudRepository<Specialization, Long> getDao() {
		// TODO Auto-generated method stub
		return repository;
	}

	public Specialization save(Specialization element) {
		Specialization ob=repository.save(element);
		return ob;
	}

	public Page<Specialization> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}