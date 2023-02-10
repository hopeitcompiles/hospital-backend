package com.utpl.hospital.repository;
import com.utpl.hospital.model.Doctor;
import com.utpl.hospital.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
	Page<Doctor> findAll(Pageable pageable);
	@Modifying
	@Query("update doctors u set u.name = ?2, u.lastName = ?3, u.dni = ?4, u.phone = ?5, u.birthdate = ?6, u.startDate = ?7, u.address = ?8  where u.id = ?1")
	void updateDoctor(Long id, String name, String lastName, String dni, String phone, LocalDate birthdate, LocalDate join, String address );
}
