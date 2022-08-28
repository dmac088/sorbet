package io.nzbee.domain.dto.promotion.in;

public class BagItemQuantityDTO {

	private final String itemUPC;
	
	private final Long itemQuantity;

	public BagItemQuantityDTO(String itemUPC, Long itemQuantity) {
		super();
		this.itemUPC = itemUPC;
		this.itemQuantity = itemQuantity;
	}

	public String getItemUPC() {
		return itemUPC;
	}

	public Long getItemQuantity() {
		return itemQuantity;
	}
	
}
