package com.mps.service;

import java.util.List;
import java.util.Optional;

import com.mps.entity.Specialization;

public interface ISpecializationService {
	Long addSpecialization(Specialization s);
	List<Specialization> getAllSpecialization();
	void removeSpecialization(Long id);
	Optional<Specialization> getOneSpecialization(Long id);
	Long updateSpecialization(Specialization s);
	boolean isSpecCodeExist(String specCode);
}
