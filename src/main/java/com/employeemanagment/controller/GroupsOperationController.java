package com.employeemanagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagment.Util.ValidateUtil;
import com.employeemanagment.dto.DtoGroup;
import com.employeemanagment.dto.DtoMember;
import com.employeemanagment.dto.DtoUser;
import com.employeemanagment.service.GroupService;

@RestController
@RequestMapping(value = "/api/group/admin")
public class GroupsOperationController {

	@Autowired
	GroupService groupService;

//================================== Add Admin To Group ===============================================//
	@RequestMapping(value = "/{userId}/{groupId}", method = RequestMethod.POST)
	public ResponseEntity<DtoMember> addAdminToGroup(@PathVariable(name = "groupId") long groupId,
			@PathVariable(name = "userId")long userId,
			@RequestBody DtoMember dtoMember) {
		DtoMember dtoObj = this.groupService.addAdminToGroup(dtoMember,userId, groupId);
		
		if(ValidateUtil.checkNullObject(dtoObj))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(dtoMember);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoObj);

	}
	
//=================================== Get All Admins In Group =============================================//
	@RequestMapping(value = "/admin/{userId}/{groupId}",method = RequestMethod.GET)
	public ResponseEntity<DtoGroup> getAdminsForGroup(@RequestBody DtoGroup dtoGroup,
			@PathVariable(name = "userId") long userId, @PathVariable(name = "groupId") long groupId) {
		DtoGroup dtoObj = this.groupService.getAllAdminMember(dtoGroup,userId,groupId);
		if(ValidateUtil.checkNullObject(dtoObj))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(dtoGroup);
		
		
		return ResponseEntity.status(HttpStatus.FOUND).body(dtoObj);
	}
	
//=================================== Delete Admin From Group ==============================================//
	@RequestMapping(value = "/admin/{groupId}/{userId}",method = RequestMethod.DELETE)
	public ResponseEntity<DtoUser> deleteAdminFromGroup(@RequestBody DtoUser dtoUser,
			@PathVariable(name = "groupId") long groupId, @PathVariable(name = "userId") long userId) {
		DtoUser dtoObj = this.groupService.deleteAminFromGroup(dtoUser, groupId, userId);
		if(ValidateUtil.checkNullObject(dtoObj))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(dtoUser);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(dtoObj);
	}

	// ================================== Add User To Group  ===============================================//
	@RequestMapping(value = "/user/{userId}/{groupId}", method = RequestMethod.POST)
	public ResponseEntity<DtoMember> addUserToGroup(@PathVariable(name = "groupId") long groupId,
			@PathVariable(name = "userId") long userId,@RequestBody DtoMember dtoMember) {
		DtoMember dtoObj = this.groupService.addUserToGroup(dtoMember,userId, groupId);
		if(ValidateUtil.checkNullObject(dtoObj))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(dtoMember);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoObj);

	}


//=================================== Media API =============================================//

}
