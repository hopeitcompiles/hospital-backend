package com.utpl.hospital.service;

import com.utpl.hospital.model.User;
import com.utpl.hospital.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, Long> {
	@Autowired
	private IUserRepository repository;
	@Autowired
	private BCryptPasswordEncoder bcrypt;


	public CrudRepository<User, Long> getDao() {
		// TODO Auto-generated method stub
		return repository;
	}

	public User save(User element) {
		element.setPassword(bcrypt.encode(element.getPassword()));
		User ob=repository.save(element);
		return ob;
	}

	public User findByEmail(String email){
		return repository.findByEmail(email);
	}
	public Page<User> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}