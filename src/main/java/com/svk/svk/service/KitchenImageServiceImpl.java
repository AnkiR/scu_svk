package com.svk.svk.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.svk.svk.dao.KitchenImageDao;
import com.svk.svk.model.KitchenImage;
import com.svk.svk.model.Member;

@Service
public class KitchenImageServiceImpl implements KitchenImageService {

	private KitchenImageDao kitchenImageDao;
	
	public void setKitchenImageDao(KitchenImageDao kitchenImageDao) {
		this.kitchenImageDao = kitchenImageDao;
	}
	
	@Override
	@Transactional
	public void addKitchenImage(KitchenImage ki) {
		kitchenImageDao.addKitchenImage(ki);
	}

	@Override
	@Transactional
	public KitchenImage getKitchenImageByMember(Member m) {
		return kitchenImageDao.getKitchenImageByMember(m);
	}

}
