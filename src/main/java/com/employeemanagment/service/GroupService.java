package com.employeemanagment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagment.Util.ValidateUtil;
import com.employeemanagment.dto.DtoGroup;
import com.employeemanagment.dto.DtoMember;
import com.employeemanagment.dto.DtoUser;
import com.employeemanagment.model.Group;
import com.employeemanagment.model.Member;
import com.employeemanagment.model.User;
import com.employeemanagment.repository.GroupRepository;
import com.employeemanagment.repository.MemberRepository;
import com.employeemanagment.repository.UserRepository;

@Service
public class GroupService {

	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	// get All Group 
	public List<DtoGroup> getAllGroup(){
		List<Group> groups =   this.groupRepository.findAll();
		List<DtoGroup> dtoGroups = new ArrayList<DtoGroup>();
		
		if(ValidateUtil.checkEmptyList(groups))
			return null;
		
		groups.stream().forEach(group -> {
			dtoGroups.add(new DtoGroup(group));
		});
		return dtoGroups;
	}
	
	
	 
	// get Groups By user Id
	public List<DtoGroup> getGroupByUserId(long userId){
		List<Group> groupsByUserId = groupRepository.findByGroupMembersUserId(userId);
		if(ValidateUtil.checkEmptyList(groupsByUserId))
			return null;
		
		List<DtoGroup> dtoGroups = new ArrayList<DtoGroup>();
		groupsByUserId.stream().forEach(group -> {
			dtoGroups.add(new DtoGroup(group));
		});
		return dtoGroups;
	}
	
	// get Groups Info By User & Group Id
	public DtoGroup getGroupInfo(long userId , long groupId) {
		Group group = this.groupRepository.findById(groupId).orElse(null);
		if(ValidateUtil.checkNullObject(group))
			return null;
		DtoGroup dtoGroup = new DtoGroup(group);
		return dtoGroup;
	}
	
	//  delete Group By Id
	public DtoGroup deleteGroupById(long groupId,long userId) {
		
		Group group = groupRepository.findById(groupId).orElse(null);
		if(group == null)
			return null;
		// member check
		Member member = group.getGroupMembers().stream().filter(m -> m.getUser().getId() == userId).findFirst().orElse(null);
		if(ValidateUtil.checkNullObject(member))
			return null;
		// admin check
		if(!member.isAdmin())
			return null;
		
		DtoGroup dtoGroup = new DtoGroup(group);
		groupRepository.delete(group);
		return dtoGroup;
	}
	
	// create group by admin user
	 public DtoGroup createGroup (DtoGroup dtoGroup) {
		 
		 // create Group
		 Group group = new Group(dtoGroup);
		 
		 // create Members
		 List<Member> members = new ArrayList<Member>();

		 dtoGroup.getDtoGroupMembers().stream().forEach(dtoMembers -> {
			
			 // create Members
			 Member member = new Member();
			 member.setGroup(group);
			 member.setAdmin(true);
			 
			 // find User
			 User user =userRepository.findById(dtoMembers.getDtoUser().getId()).get();
			 if(user != null)
				 member.setUser(user);
			 
			 members.add(member);
		 });
		 group.setGroupMembers(members);
		 this.groupRepository.save(group);
		 if(group.getId() != 0)
			 dtoGroup  = new DtoGroup(group);
		 
		 return dtoGroup;
	 }
	 
	// update group by admin 
	 public DtoGroup updateGroup (long userId,long groupId,DtoGroup dtoGroup) {
		 Group group = this.groupRepository.findById(groupId).orElse(null);
		 if(ValidateUtil.checkNullObject(group))
			 return null;
		 
		 Member member = group.getGroupMembers().stream().filter(m -> m.getUser().getId() == userId).findFirst().orElse(null);
		 if(ValidateUtil.checkNullObject(member))
			return null;
		
		 // admin check
		 if(!member.isAdmin())
			return null;
		 
		 group = new Group(dtoGroup);
		 group.setId(groupId);
		 this.groupRepository.save(group);
		 dtoGroup = new DtoGroup(group);
		 return dtoGroup;
	 }
	
//================================ Admin Operation =========================================//
	 
	 // Add Adming To Group
	 public DtoMember addAdminToGroup(DtoMember dtoMemberReq,long userId , long groupId) {
		 // check the user Adding is  Admin
		 Member userMember = this.memberRepository.findByUserIdAndGroupId(userId, groupId);
		 if(!userMember.isAdmin())
			 return null;
		 
		 User admin = userRepository.findById(dtoMemberReq.getDtoUser().getId()).get();
		 Group group = groupRepository.findById(groupId).get();
		 Member member  = new Member();
		 //List<Member> listOfMembers = new ArrayList<Member>();
		 member.setAdmin(true);
		 member.setGroup(group);
		 member.setUser(admin);
		 member = memberRepository.save(member);
		 group.getGroupMembers().add(member);
		 group = groupRepository.save(group);
		 DtoMember dtoMember = new DtoMember(member);
		 
		 
		 return dtoMember;
	 }
	 
	 // Get All Admin For Group 
	 public DtoGroup getAllAdminMember(DtoGroup dtoGroup ,long userId,long groupId) {
		 Member userMember = this.memberRepository.findByUserIdAndGroupId(userId, groupId);
		 if(!userMember.isAdmin())
			 return null;
		 
		 Group group = this.groupRepository.findById(dtoGroup.getId()).get();
		 group.setGroupMembers( group.getGroupMembers().stream().filter(m -> m.isAdmin())
		 .collect(Collectors.toList()));
		 DtoGroup dtoGroup2 = new DtoGroup(group);
		 return dtoGroup2;
	 }
	 
	 
	 // Delete Admin From Group
	 public DtoUser deleteAminFromGroup(DtoUser dtoUser,long groupId,long userId) {
		 Member userMember = this.memberRepository.findByUserIdAndGroupId(userId, groupId);
		 if(!userMember.isAdmin())
			 return null;
		 
		 User user = this.userRepository.findById(dtoUser.getId()).get();
		 List<Member> members = user.getGroupsMember().stream().filter(m -> 
		 m.getGroup().getId() ==groupId && m.getUser().getId() == userId )
		 		.collect(Collectors.toList());
		 this.memberRepository.deleteAll(members);
		 dtoUser = new DtoUser(user);
		 return dtoUser;
	 }
	 
	 
	 // Add User To Group
	 public DtoMember addUserToGroup(DtoMember dtoMemberReq,long userId , long groupId) {
		 // check the user Adding is  Admin
		 Member userMember = this.memberRepository.findByUserIdAndGroupId(userId, groupId);
		 if(!userMember.isAdmin())
			 return null;
		 
		 User admin = userRepository.findById(dtoMemberReq.getDtoUser().getId()).get();
		 Group group = groupRepository.findById(groupId).get();
		 Member member  = new Member();
		 //List<Member> listOfMembers = new ArrayList<Member>();
		 member.setAdmin(false);
		 member.setGroup(group);
		 member.setUser(admin);
		 member = memberRepository.save(member);
		 group.getGroupMembers().add(member);
		 group = groupRepository.save(group);
		 DtoMember dtoMember = new DtoMember(member);
		 return dtoMember;

	 }
	 
	 // TODO
	 // delete User By admin
	 public DtoUser deleteUserFromGroup(DtoUser dtoUser,long groupId,long userId) {
		 Member userMember = this.memberRepository.findByUserIdAndGroupId(userId, groupId);
		 if(!userMember.isAdmin())
			 return null;
		 
		 User user = this.userRepository.findById(dtoUser.getId()).get();
		 List<Member> members = user.getGroupsMember().stream().filter(m -> 
		 m.getGroup().getId() ==groupId  && m.getUser().getId() == userId)
		 		.collect(Collectors.toList());
		 this.memberRepository.deleteAll(members);
		 dtoUser = new DtoUser(user);
		 return dtoUser;
	 }
	 
	 //========================= media Services =====================================================//
}
