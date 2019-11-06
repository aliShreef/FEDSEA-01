package com.employeemanagment.controller;

import java.util.List;

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
import com.employeemanagment.service.GroupService;

@RestController
@RequestMapping(value = "/api/group")
public class GroupsController {
	
	@Autowired
	GroupService groupService;
	

//================================== Get All Group Information =========================================//
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ResponseEntity<List<DtoGroup>> getAllGroup(){
		List<DtoGroup> groupsList = this.groupService.getAllGroup();
		if(ValidateUtil.checkEmptyList(groupsList)) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(this.groupService.getAllGroup());
		}
	}
	
//================================== Get Groups Which User Member In ====================================//
	@RequestMapping(value = "{userId}",method = RequestMethod.GET)
	public ResponseEntity<List<DtoGroup>> getGeoupsByUserId(
			@PathVariable(name = "userId") long userId){
		List<DtoGroup> dtoGroupList = this.groupService.getGroupByUserId(userId);
		
		if(ValidateUtil.checkEmptyList(dtoGroupList))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(this.groupService.getGroupByUserId(userId));	
	}
	
//=================================  Get Group By User Id & Group Id ===================================//
	@RequestMapping(value = "{userId}/{groupId}",method = RequestMethod.GET)
	public ResponseEntity<DtoGroup> getGroupsInfoByUserGroupIds(@PathVariable(name = "userId")long userId,
			@PathVariable(name = "groupId")long groupId){
		DtoGroup dtoGroup = this.groupService.getGroupInfo(userId, groupId);
		if(ValidateUtil.checkNullObject(dtoGroup))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(dtoGroup);
	}
	
	
//================================== Delete Group Information By Group Id ==============================//
	@RequestMapping(value = "{userId}/{groupId}",method = RequestMethod.DELETE)
	public ResponseEntity<DtoGroup> deleteGroupById(
			@PathVariable(name = "groupId") long groupId,
			@PathVariable(name = "userId") long userId){
		DtoGroup dtoGroup = this.groupService.deleteGroupById(groupId,userId);
		if(dtoGroup == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(this.groupService.deleteGroupById(groupId,userId));
	}
	
//================================== Create Group With Admin User =====================================//
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResponseEntity<DtoGroup> createGroup(@RequestBody DtoGroup dtoGroup){
		dtoGroup = this.groupService.createGroup(dtoGroup);
		if(dtoGroup.getId() == 0)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(dtoGroup);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(dtoGroup);
	}
	
//================================= Update Group Info By User =======================================//
	@RequestMapping(value = "/{userId}/{groupId}",method = RequestMethod.PUT)
	public ResponseEntity<DtoGroup> updateGroup(@RequestBody DtoGroup dtoGroup
			,@PathVariable(name = "userId") long userId,
			@PathVariable(name = "groupId") long groupId){
		dtoGroup = this.groupService.updateGroup(userId,groupId,dtoGroup);
		if(dtoGroup == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dtoGroup);
		
		return ResponseEntity.status(HttpStatus.OK).body(dtoGroup);
	}

}
