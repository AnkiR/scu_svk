package com.svk.svk.dao;

import java.util.List;

import com.svk.svk.model.KitchenImage;
import com.svk.svk.model.Member;

public interface KitchenImageDao {

	public void addKitchenImage(KitchenImage ki);
	public void updateKitchenImage(KitchenImage ki);
	public List<KitchenImage> getKitchenImageByMember(Member m);
	public void deleteAllImagesForMember(Member m);
}
