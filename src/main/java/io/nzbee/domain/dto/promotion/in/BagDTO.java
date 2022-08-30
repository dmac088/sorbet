package io.nzbee.domain.dto.promotion.in;

import java.util.ArrayList;
import java.util.List;

public class BagDTO {

	private String coupon;
	
	private List<BagItemQuantityDTO> items = new ArrayList<BagItemQuantityDTO>();
	
	public BagDTO() {
	}

	public List<BagItemQuantityDTO> getItems() {
		return items;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public void setItems(List<BagItemQuantityDTO> items) {
		this.items = items;
	}
	
}
