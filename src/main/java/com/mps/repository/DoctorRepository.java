package com.mps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mps.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
