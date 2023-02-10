package com.utpl.hospital.repository;
import com.utpl.hospital.model.Role;
import com.utpl.hospital.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

	Page<Role> findAll(Pageable pageable);
	Role findByRole(String roleName);
}
