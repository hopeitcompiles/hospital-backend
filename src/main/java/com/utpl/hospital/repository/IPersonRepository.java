package com.utpl.hospital.repository;
import com.utpl.hospital.model.Doctor;
import com.utpl.hospital.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

	Page<Person> findAll(Pageable pageable);
}
