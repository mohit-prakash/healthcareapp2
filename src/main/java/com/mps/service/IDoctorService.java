package com.mps.service;

import java.util.List;

import com.mps.entity.Doctor;

public interface IDoctorService {
	Long addDoctor(Doctor doc);
	List<Doctor> getAllDoctor();
	void removeDoctor(Long id);
	Doctor getOneDoctor(Long id);
	Long updateDoctor(Doctor doc);
}
