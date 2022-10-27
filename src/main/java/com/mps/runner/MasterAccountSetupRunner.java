package com.mps.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mps.constants.UserRoles;
import com.mps.entity.User;
import com.mps.service.IUserService;
import com.mps.util.UserUtil;
@Component
public class MasterAccountSetupRunner implements CommandLineRunner {

	@Value("${master.user.name}")
	private String displayName;
	@Value("${master.user.email}")
	private String username;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserUtil util;
	@Override
	public void run(String... args) throws Exception {
		//This method run only first time
		if(!userService.findByUsername(username).isPresent())
		{
				User user = new User();
				user.setDisplayName(displayName);
				user.setUsername(username);
				user.setPassword(util.genPwd());
				user.setRole(UserRoles.ADMIN.name());
				userService.saveUser(user);
				//TODO: Email Service
		}
	}
}
