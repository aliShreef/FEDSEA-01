package com.employeemanagment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagment.dto.DtoUser;
import com.employeemanagment.model.User;
import com.employeemanagment.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public DtoUser createUser(DtoUser dtoUser) {
		User user = new User(dtoUser);
		this.userRepository.save(user);
		dtoUser.setId(user.getId());
		return dtoUser;
	}
}
