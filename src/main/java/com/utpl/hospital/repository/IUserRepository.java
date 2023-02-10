package com.utpl.hospital.repository;
import com.utpl.hospital.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	Page<User> findAll(Pageable pageable);
	User findByEmail(String email);
}
