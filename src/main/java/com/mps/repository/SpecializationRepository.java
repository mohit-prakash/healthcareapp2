package com.mps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mps.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

	@Query("select count(specCode) from Specialization where specCode=:specCode")
	Integer getSpecCodeCount(String specCode);
	@Query("select count(specCode) from Specialization where specCode=:specCode and id!=:id")
	Integer getSpecCodeCountForEdit(String specCode,Long id);
	@Query("select id,specName from Specialization ")
	List<Object[]> getSpecIdAndName();
}
