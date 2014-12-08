package com.svk.svk.dao;

import com.svk.svk.model.KitchenImage;
import com.svk.svk.model.Member;

public interface KitchenImageDao {

	public void addKitchenImage(KitchenImage ki);
	public void updateKitchenImage(KitchenImage ki);
	public KitchenImage getKitchenImageByMember(Member m);
}
