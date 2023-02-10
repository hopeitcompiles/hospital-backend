package com.utpl.hospital.repository;
import com.utpl.hospital.model.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISpecializationRepository extends JpaRepository<Specialization, Long> {
	Page<Specialization> findAll(Pageable pageable);
}
