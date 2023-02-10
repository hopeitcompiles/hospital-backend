package com.utpl.hospital.repository;
import com.utpl.hospital.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

	Page<Patient> findAll(Pageable pageable);
}
