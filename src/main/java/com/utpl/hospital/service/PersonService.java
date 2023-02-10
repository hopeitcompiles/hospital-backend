package com.utpl.hospital.service;

import com.utpl.hospital.model.Person;
import com.utpl.hospital.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class PersonService extends GenericService<Person, Long> {
	@Autowired
	private IPersonRepository repository;


	public CrudRepository<Person, Long> getDao() {
		// TODO Auto-generated method stub
		return repository;
	}

	public Person save(Person element) {
		Person ob=repository.save(element);
		return ob;
	}

	public Page<Person> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}