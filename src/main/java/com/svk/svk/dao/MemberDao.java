package com.svk.svk.dao;

import com.svk.svk.model.Member;

public interface MemberDao {

	public void addMember(Member m);
	public void updateMember(Member m);
	public Member getMemberById(int id);
	public Member getMemberByEmailId(String emailId);
}
