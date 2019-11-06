package com.employeemanagment.dto;

import java.util.ArrayList;
import java.util.List;

import com.employeemanagment.model.Group;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoGroup {

	private long id;
	private String name;
	private String category;
	private String description;
	private String bio;
	private boolean isPrivate;
	private boolean showHistorical;
	private String inviteLink;
	private List<DtoMember> dtoGroupMembers;
	
	public DtoGroup() {
		// TODO Auto-generated constructor stub
	}
	
	public DtoGroup(Group group) {
		this.id = group.getId();
		this.name = group.getName();
		this.category =group.getCategory();
		this.description =group.getDescription();
		this.bio = group.getBio();
		this.isPrivate = group.isPrivate();
		this.showHistorical = group.isShowHistorical();
		this.inviteLink = group.getInviteLink();
		if(group.getGroupMembers() != null &&
				group.getGroupMembers().size() != 0) {
			List<DtoMember> dtoMembersObj = new ArrayList<DtoMember>();
			group.getGroupMembers().stream().forEach(memeber -> {
				DtoMember dtoMember = new DtoMember(memeber);
				dtoMembersObj.add(dtoMember);
			});
			this.dtoGroupMembers = dtoMembersObj;
		}

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public boolean isShowHistorical() {
		return showHistorical;
	}

	public void setShowHistorical(boolean showHistorical) {
		this.showHistorical = showHistorical;
	}

	public String getInviteLink() {
		return inviteLink;
	}

	public void setInviteLink(String inviteLink) {
		this.inviteLink = inviteLink;
	}

	public List<DtoMember> getDtoGroupMembers() {
		return dtoGroupMembers;
	}

	public void setDtoGroupMembers(List<DtoMember> dtoGroupMembers) {
		this.dtoGroupMembers = dtoGroupMembers;
	}
	
	

}
