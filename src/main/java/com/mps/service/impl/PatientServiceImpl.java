package com.mps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mps.entity.Patient;
import com.mps.exception.PatientNotFoundException;
import com.mps.repository.PatientRepository;
import com.mps.service.IPatientService;
@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository repo;
	@Override
	public Long addPatient(Patient patient) {
		Patient p = repo.save(patient);
		return p.getId();
	}

	@Override
	public Patient getOnePatient(Long id) {
		return repo.findById(id).orElseThrow(()->new PatientNotFoundException("Patient "+id+" Not Found!!"));
	}

	@Override
	public List<Patient> getAllPatient() {
		List<Patient> findAll = repo.findAll();
		return findAll;
	}

	@Override
	public void removePatient(Long id) {
		repo.delete(getOnePatient(id));
	}

	@Override
	public Long updatePatient(Patient patient) {
		Patient p = repo.save(patient);
		return p.getId();
	}

}
