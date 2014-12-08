package com.svk.svk.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.svk.svk.model.KitchenImage;
import com.svk.svk.model.Member;

@Repository
public class KitchenImageDaoImpl implements KitchenImageDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addKitchenImage(KitchenImage ki) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(ki);
	}

	@Override
	public void updateKitchenImage(KitchenImage ki) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(ki);
	}

	@Override
	public KitchenImage getKitchenImageByMember(Member m) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from KitchenImage where MemberId = :memberId ");
		query.setParameter("memberId", m.getMemberId());
		List list = query.list();
		if (list.isEmpty()) {
			return null;
		}
		return (KitchenImage) list.get(0);
	}

}
