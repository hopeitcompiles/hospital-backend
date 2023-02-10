package com.utpl.hospital.service;

import com.utpl.hospital.model.Appointment;
import com.utpl.hospital.repository.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class AppointmentService extends GenericService<Appointment, Long> {
	@Autowired
	private IAppointmentRepository repository;


	public CrudRepository<Appointment, Long> getDao() {
		// TODO Auto-generated method stub
		return repository;
	}

	public Appointment save(Appointment element) {
		Appointment ob=repository.save(element);
		return ob;
	}

	public Page<Appointment> getAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}