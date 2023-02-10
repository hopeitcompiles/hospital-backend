package com.utpl.hospital.service;

import com.utpl.hospital.authentication.ApplicationRole;
import com.utpl.hospital.model.Role;
import com.utpl.hospital.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RoleService extends GenericService<Role, Long> {
	@Autowired
	private IRoleRepository repository;


	public CrudRepository<Role, Long> getDao() {
		// TODO Auto-generated method stub
		return repository;
	}

	public Role save(Role element) {
		Role ob=repository.save(element);
		return ob;
	}
	public Role findByENUM(ApplicationRole role) {
		return repository.findByRole(role.name());
	}
	public Page<Role> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}