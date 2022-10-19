package com.mps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mps.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query("Select id,firstName,lastName from Doctor")
	public List<Object[]> getDoctorIdAndNames();
}
