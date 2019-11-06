package com.employeemanagment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.employeemanagment.dto.DtoGroup;

@Entity
@Table(name = "groups")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "bio")
	private String bio;
	
	@Column(name = "isprivate")
	private boolean isPrivate;
	
	@Column(name = "showhistorical")
	private boolean showHistorical;
	
	@Column(name = "invitelink")
	private String inviteLink;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Member> groupMembers;

	public Group() {
		// TODO Auto-generated constructor stub
	}
	
	public Group(DtoGroup dtoGroup) {
		if(dtoGroup.getId() != 0 ) {
			this.id = dtoGroup.getId();
		}
		this.name = dtoGroup.getName();
		this.category = dtoGroup.getCategory();
		this.description = dtoGroup.getDescription();
		this.bio = dtoGroup.getBio();
		this.isPrivate = dtoGroup.isPrivate();
		this.showHistorical = dtoGroup.isShowHistorical();
		this.inviteLink = dtoGroup.getInviteLink();
		
//		if(dtoGroup.getDtoGroupMembers() != null&& 
//				dtoGroup.getDtoGroupMembers().size() != 0 && 
//				!dtoGroup.getDtoGroupMembers().isEmpty() 
//				) {
//			List<Member> listOfMember = new ArrayList<Member>();
//			dtoGroup.getDtoGroupMembers().stream().forEach(dtoMember -> {
//				Member member = new Member(dtoMember);
//				listOfMember.add(member);
//			});
//			this.groupMembers = listOfMember;
//		}
		
	}
	
	public Group(long id, String name, String category, String description, String bio, boolean isPrivate,
			boolean showHistorical, String inviteLink, List<Member> groupMembers) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.bio = bio;
		this.isPrivate = isPrivate;
		this.showHistorical = showHistorical;
		this.inviteLink = inviteLink;
		this.groupMembers = groupMembers;
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

	public List<Member> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(List<Member> groupMembers) {
		this.groupMembers = groupMembers;
	}
	
	
}
