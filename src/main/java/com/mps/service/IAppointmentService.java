package com.mps.service;

import java.util.List;

import com.mps.entity.Appointment;

public interface IAppointmentService {
	Long addAppointment(Appointment appointment);
	Appointment getOneAppointment(Long id);
	void removeAppointment(Long id);
	List<Appointment> getAllAppointment();
	Long updateAppointment(Appointment appointment);
}
