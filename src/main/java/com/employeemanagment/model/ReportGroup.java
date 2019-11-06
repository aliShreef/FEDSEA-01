package com.employeemanagment.model;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "reportgroup")
public class ReportGroup {
	
	private long id;
	
	private String description;
	
	private User user;
	
	private Group group;

}
