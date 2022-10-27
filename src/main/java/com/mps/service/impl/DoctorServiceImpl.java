package com.mps.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mps.constants.UserRoles;
import com.mps.entity.Doctor;
import com.mps.entity.User;
import com.mps.exception.DoctorNotFoundException;
import com.mps.repository.DoctorRepository;
import com.mps.service.IDoctorService;
import com.mps.service.IUserService;
import com.mps.util.MyCollectionUtil;
import com.mps.util.UserUtil;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repo;
	@Autowired
	private UserUtil util;
	@Autowired
	private IUserService userService;
	@Override
	public Long addDoctor(Doctor doc) {
		Doctor doctor = repo.save(doc);
		if(doctor.getId()!=null)
		{
			User user=new User();
			user.setDisplayName(doctor.getFirstName()+" "+doctor.getLastName());
			user.setUsername(doctor.getEmail());
			user.setPassword(util.genPwd());
			user.setRole(UserRoles.DOCTOR.name());
			//TODO: Email part is pending
			userService.saveUser(user);
		}
		return doctor.getId();
	}

	@Override
	public List<Doctor> getAllDoctor() {
		List<Doctor> list = repo.findAll();
		return list;
	}

	@Override
	public void removeDoctor(Long id) {
			repo.delete(getOneDoctor(id));
	}

	@Override
	public Doctor getOneDoctor(Long id) {
		Doctor doctor = repo.findById(id).orElseThrow(()->new DoctorNotFoundException("Doctor "+id+" not found!!"));
		return doctor;
	}

	@Override
	public Long updateDoctor(Doctor doc) {
		return repo.save(doc).getId();
	}

	@Override
	public Map<Long, String> getDoctorIdAndNames() {
		return MyCollectionUtil.convertToMapFromList(repo.getDoctorIdAndNames());
	}

}
