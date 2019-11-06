package com.employeemanagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagment.dto.DtoUser;
import com.employeemanagment.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST,value = "")
	public ResponseEntity<DtoUser> createUser(@RequestBody DtoUser dtoUser){
		dtoUser = this.userService.createUser(dtoUser);
		if(dtoUser.getId() == 0)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(dtoUser);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoUser);
	}
}
