package com.mps.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mps.entity.Doctor;
import com.mps.exception.DoctorNotFoundException;
import com.mps.repository.DoctorRepository;
import com.mps.service.IDoctorService;
import com.mps.util.MyCollectionUtil;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repo;
	
	@Override
	public Long addDoctor(Doctor doc) {
		Doctor doctor = repo.save(doc);
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
