package com.mps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mps.entity.Appointment;
import com.mps.exception.AppointmentNotFoundException;
import com.mps.repository.AppointmentRepository;
import com.mps.service.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private AppointmentRepository repo;
	@Override
	public Long addAppointment(Appointment appointment) {
		Appointment app = repo.save(appointment);
		return app.getId();
	}

	@Override
	public Appointment getOneAppointment(Long id) {
		return repo.findById(id).orElseThrow(()->new AppointmentNotFoundException("Appointment "+id+" not found!!"));
	}

	@Override
	public void removeAppointment(Long id) {
		repo.delete(getOneAppointment(id));
	}

	@Override
	public List<Appointment> getAllAppointment() {
		List<Appointment> list = repo.findAll();
		return list;
	}

	@Override
	public Long updateAppointment(Appointment appointment) {
		return repo.save(appointment).getId();
	}

}
