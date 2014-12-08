package com.svk.svk.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.svk.svk.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	private static final Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addMember(Member m) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(m);
		logger.info("Member saved successfully, Person Details="+m);
	}

	@Override
	public void updateMember(Member m) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(m);
		logger.info("Member updated successfully, Person Details="+m);
	}

	@Override
	public Member getMemberById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Member m = (Member) session.load(Member.class, new Integer(id));
		logger.info("Member loaded successfully, Member details="+m);
		return m;
	}

	@Override
	public Member getMemberByEmailId(String emailId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Member where EmailId = :emailId ");
		query.setParameter("emailId", emailId);
		List list = query.list();
		if (list.isEmpty()) {
			return null;
		}
		return (Member) list.get(0);
	}
}
