package com.employeemanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanagment.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	 Member findByUserIdAndGroupId(long userId,long groupId);
}
