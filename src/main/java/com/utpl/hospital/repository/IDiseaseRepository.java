package com.utpl.hospital.repository;
import com.utpl.hospital.model.Disease;
import com.utpl.hospital.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//interfaz de repositorio para la entidad enfermedades
@Repository
public interface IDiseaseRepository extends JpaRepository<Disease, Long> {

	Page<Disease> findAll(Pageable pageable);
}
