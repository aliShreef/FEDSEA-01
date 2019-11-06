package com.employeemanagment.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.employeemanagment.dto.DtoMember;

@Entity
@Table(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Group group;
	
	@Column(name = "isadmin")
	private boolean isAdmin;
	
	@Column(name = "isFavorit")
	private boolean isFavorit;

	public Member() {
		// TODO Auto-generated constructor stub
	}



	public Member(long id, User user, Group group, boolean isAdmin, boolean isFavorit) {
		super();
		this.id = id;
		this.user = user;
		this.group = group;
		this.isAdmin = isAdmin;
		this.isFavorit = isFavorit;
	}

	public Member (DtoMember dtoMember) {
		if(dtoMember.getId() != 0 ) {
			this.id = dtoMember.getId();
		}
		this.user = new User(dtoMember.getDtoUser());
		this.group = new Group(dtoMember.getDtoGroup());
		this.isAdmin = dtoMember.isAdmin();
		this.isFavorit = dtoMember.isFavorit();
		
	}


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}

	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Group getGroup() {
		return group;
	}



	public void setGroup(Group group) {
		this.group = group;
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
