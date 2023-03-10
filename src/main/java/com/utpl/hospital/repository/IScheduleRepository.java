package com.utpl.hospital.repository;
import com.utpl.hospital.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IScheduleRepository extends JpaRepository<Schedule, Long> {
	Page<Schedule> findAll(Pageable pageable);
}
