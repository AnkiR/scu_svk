package com.svk.svk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="KITCHENIMAGE")
public class KitchenImage {

	@Id
	@Column(name="KitchenImageId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int kitchenImageId;
	
	@Column(name="MemberId")
	private int memberId;
	
	@Column(name="ImageIndex")
	private int imageIndex;
	
	@Column(name="Image")
	private byte[] image;
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getImageIndex() {
		return imageIndex;
	}
	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getKitchenImageId() {
		return kitchenImageId;
	}
	public void setKitchenImageId(int kitchenImageId) {
		this.kitchenImageId = kitchenImageId;
	}
}
