package com.employeemanagment.dto;

import com.employeemanagment.model.Member;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoMember {

	private long id;
	private DtoUser dtoUser;
	private DtoGroup dtoGroup;
	private boolean isAdmin;
	private boolean isFavorit;
	
	public DtoMember() {
		// TODO Auto-generated constructor stub
	}
	
	public DtoMember(Member member ) {
		this.id = member.getId();
		this.dtoUser = new DtoUser(member.getUser());
		//this.dtoGroup = new DtoGroup(member.getGroup());
		this.isAdmin = member.isAdmin();
		this.isFavorit = member.isFavorit();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DtoUser getDtoUser() {
		return dtoUser;
	}

	public void setDtoUser(DtoUser dtoUser) {
		this.dtoUser = dtoUser;
	}

	public DtoGroup getDtoGroup() {
		return dtoGroup;
	}

	public void setDtoGroup(DtoGroup dtoGroup) {
		this.dtoGroup = dtoGroup;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isFavorit() {
		return isFavorit;
	}

	public void setFavorit(boolean isFavorit) {
		this.isFavorit = isFavorit;
	}
	
	

}
