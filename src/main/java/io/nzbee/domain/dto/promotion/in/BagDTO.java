package io.nzbee.domain.dto.promotion.in;

import java.util.ArrayList;
import java.util.List;

public class BagDTO {

	private final String coupon;
	
	private final List<BagItemQuantityDTO> items = new ArrayList<BagItemQuantityDTO>();
	
	public BagDTO(String coupon) {
		super();
		this.coupon = coupon;
	}

	public List<BagItemQuantityDTO> getItems() {
		return items;
	}

	public String getCoupon() {
		return coupon;
	}
	
}
