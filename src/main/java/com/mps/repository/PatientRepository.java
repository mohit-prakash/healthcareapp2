package com.mps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mps.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
