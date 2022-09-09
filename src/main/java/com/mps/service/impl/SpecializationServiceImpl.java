package com.mps.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mps.entity.Specialization;
import com.mps.repository.SpecializationRepository;
import com.mps.service.ISpecializationService;

@Service
public class SpecializationServiceImpl implements ISpecializationService {

	@Autowired
	private SpecializationRepository repo;
	@Override
	public Long addSpecialization(Specialization s) {
		Specialization specialization = repo.save(s);
		return specialization.getId();
	}
	@Override
	public List<Specialization> getAllSpecialization() {
		List<Specialization> list = repo.findAll();
		return list;
	}
	@Override
	public void removeSpecialization(Long id) {
		repo.deleteById(id);
	}
	@Override
	public Optional<Specialization> getOneSpecialization(Long id) {
		Optional<Specialization> findById = repo.findById(id);
		return findById;
	}
	@Override
	public Long updateSpecialization(Specialization s) {
		Specialization specialization = repo.save(s);
		return specialization.getId();
	}
	@Override
	public boolean isSpecCodeExist(String specCode) {
		return repo.getSpecCodeCount(specCode)>0;
	}

}
