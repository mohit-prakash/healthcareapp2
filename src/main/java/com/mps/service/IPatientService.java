package com.mps.service;

import java.util.List;

import com.mps.entity.Patient;

public interface IPatientService {
	
	Long addPatient(Patient patient);
	Patient getOnePatient(Long id);
	List<Patient> getAllPatient();
	void removePatient(Long id);
	Long updatePatient(Patient patient);
}
