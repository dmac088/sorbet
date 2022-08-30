package io.nzbee.domain.dto.promotion.in;

public class BagItemQuantityDTO {

	private  String itemUPC;
	
	private  Long itemQuantity;

	public BagItemQuantityDTO() {
	
	}

	public String getItemUPC() {
		return itemUPC;
	}

	public Long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemUPC(String itemUPC) {
		this.itemUPC = itemUPC;
	}

	public void setItemQuantity(Long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
}
