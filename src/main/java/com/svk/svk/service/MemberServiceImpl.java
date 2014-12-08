package com.svk.svk.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.svk.svk.dao.MemberDao;
import com.svk.svk.model.Member;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	@Transactional
	public Member getMemberByEmailId(String emailId) {
		return memberDao.getMemberByEmailId(emailId);
	}

}
