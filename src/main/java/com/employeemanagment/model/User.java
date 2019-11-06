package com.employeemanagment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.employeemanagment.dto.DtoUser;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "fullname")
	private String fullName;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Member> groupsMember;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(long id, String email, String fullName) {
		super();
		this.id = id;
		this.email = email;
		this.fullName = fullName;
	}
	
	public User(DtoUser dtoUser) {
		if(dtoUser.getId() != 0) {
			this.id = dtoUser.getId();
		}
		this.email = dtoUser.getEmail();
		this.fullName = dtoUser.getFullName();
		if(dtoUser.getDtoGroupsMember() != null &&
				dtoUser.getDtoGroupsMember().size() != 0 &&
				!dtoUser.getDtoGroupsMember().isEmpty()
				) {
			List<Member> listOfMember = new ArrayList<Member>();
			dtoUser.getDtoGroupsMember().stream().forEach(dtoMember -> {
				Member member = new Member(dtoMember);
				listOfMember.add(member);
			});		
			this.groupsMember = listOfMember;
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

	public List<Member> getGroupsMember() {
		return groupsMember;
	}

	public void setGroupsMember(List<Member> groupsMember) {
		this.groupsMember = groupsMember;
	}
	
	

}
