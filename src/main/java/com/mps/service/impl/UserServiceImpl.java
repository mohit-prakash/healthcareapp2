package com.mps.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mps.entity.User;
import com.mps.repository.UserRepository;
import com.mps.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repo;
	@Override
	public Long saveUser(User user) {
		User u = repo.save(user);
		return u.getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

}
