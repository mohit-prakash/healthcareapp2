package com.mps.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mps.entity.Specialization;
import com.mps.exception.SpecializationNotFoundException;
import com.mps.repository.SpecializationRepository;
import com.mps.service.ISpecializationService;
import com.mps.util.MyCollectionUtil;

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
		//repo.deleteById(id);
		repo.delete(getOneSpecialization(id));
	}
	
	@Override
	public Specialization getOneSpecialization(Long id) {
		/*
		 * Optional<Specialization> findById = repo.findById(id); if
		 * (findById.isPresent()) return findById.get(); else throw new
		 * SpecializationNotFoundException(id+" Not Found!!");
		 */
		
		return repo.findById(id).orElseThrow(() -> new SpecializationNotFoundException(id+" Not Found!!"));
//		orElseThrow(Supplier) & Supplier is functional interface and we can use lambda function ( T get() )
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
	@Override
	public boolean isSpecCodeExistForEdit(String specCode, Long id) {
		return repo.getSpecCodeCountForEdit(specCode, id)>0;
	}
	@Override
	public Map<Long, String> getSpecIdAndName() {
		Map<Long, String> map = MyCollectionUtil.convertToMap(repo.getSpecIdAndName());
		return map;
	}

}
