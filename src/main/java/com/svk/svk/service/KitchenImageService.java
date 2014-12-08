package com.svk.svk.service;

import com.svk.svk.model.KitchenImage;
import com.svk.svk.model.Member;

public interface KitchenImageService {

	public void addKitchenImage(KitchenImage ki);
	
	public KitchenImage getKitchenImageByMember(Member m);
}
