package io.nzbee.view.bag.item;

public class BagItemViewIn {

	private String itemUPC;
	
	private Long itemQty;

	public BagItemViewIn() {
		
	}

	public String getItemUPC() {
		return itemUPC;
	}

	public void setItemUPC(String itemUPC) {
		this.itemUPC = itemUPC;
	}

	public Long getItemQty() {
		return itemQty;
	}

	public void setItemQty(Long qty) {
		this.itemQty = qty;
	}
	
}
