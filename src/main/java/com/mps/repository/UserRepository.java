package com.mps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mps.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
