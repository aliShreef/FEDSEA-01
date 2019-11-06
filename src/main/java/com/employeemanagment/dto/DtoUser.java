package com.employeemanagment.dto;

import java.util.ArrayList;
import java.util.List;

import com.employeemanagment.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoUser {

	private long id;
	private String email;
	private String fullName;
	private List<DtoMember> dtoGroupsMember;
	
	public DtoUser() {
		// TODO Auto-generated constructor stub
	}
	
	public DtoUser(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		if(user.getGroupsMember() != null &&
				user.getGroupsMember().size() != 0 &&
				!user.getGroupsMember().isEmpty()
				) {
			List<DtoMember> dtoGroupMembersObj = new ArrayList<DtoMember>();
			user.getGroupsMember().stream().forEach(e -> {
				DtoMember dtoMember = new DtoMember(e);
				dtoGroupMembersObj.add(dtoMember);
			});
			this.dtoGroupsMember = dtoGroupMembersObj;	
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<DtoMember> getDtoGroupsMember() {
		return dtoGroupsMember;
	}

	public void setDtoGroupsMember(List<DtoMember> dtoGroupsMember) {
		this.dtoGroupsMember = dtoGroupsMember;
	}
	
	
}
