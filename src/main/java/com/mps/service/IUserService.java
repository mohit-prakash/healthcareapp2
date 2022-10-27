package com.mps.service;

import java.util.Optional;

import com.mps.entity.User;

public interface IUserService {
	Long saveUser(User user);
	Optional<User> findByUsername(String username);
}
