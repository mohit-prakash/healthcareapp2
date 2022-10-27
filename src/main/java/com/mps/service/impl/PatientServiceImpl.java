package com.mps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mps.constants.UserRoles;
import com.mps.entity.Patient;
import com.mps.entity.User;
import com.mps.exception.PatientNotFoundException;
import com.mps.repository.PatientRepository;
import com.mps.service.IPatientService;
import com.mps.service.IUserService;
import com.mps.util.UserUtil;
@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository repo;
	@Autowired
	private UserUtil util;
	@Autowired
	private IUserService userService;
	@Override
	public Long addPatient(Patient patient) {
		Patient p = repo.save(patient);
		if(p.getId()!=null)
		{
			User user=new User();
			user.setDisplayName(p.getFirstName()+" "+p.getLastName());
			user.setUsername(p.getEmail());
			user.setPassword(util.genPwd());
			user.setRole(UserRoles.PATIENT.name());
			//TODO: Email part is pending
			userService.saveUser(user);
		}
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
