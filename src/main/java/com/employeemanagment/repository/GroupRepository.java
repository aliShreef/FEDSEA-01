package com.employeemanagment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanagment.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	List<Group> findByGroupMembersUserId(long userId);
	
}
