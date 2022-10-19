package com.mps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mps.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
