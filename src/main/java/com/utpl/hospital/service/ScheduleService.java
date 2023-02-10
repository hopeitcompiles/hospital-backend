package com.utpl.hospital.service;

import com.utpl.hospital.model.Schedule;
import com.utpl.hospital.repository.IScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class ScheduleService extends GenericService<Schedule, Long> {
	@Autowired
	private IScheduleRepository repository;


	public CrudRepository<Schedule, Long> getDao() {
		// TODO Auto-generated method stub
		return repository;
	}

	public Schedule save(Schedule element) {
		Schedule ob=repository.save(element);
		return ob;
	}

	public Page<Schedule> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}