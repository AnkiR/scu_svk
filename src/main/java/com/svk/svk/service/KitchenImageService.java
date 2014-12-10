package com.svk.svk.service;

import java.util.List;

import com.svk.svk.model.KitchenImage;
import com.svk.svk.model.Member;

public interface KitchenImageService {

	public void addKitchenImage(KitchenImage ki);
	
	public List<KitchenImage> getKitchenImageByMember(Member m);
}
