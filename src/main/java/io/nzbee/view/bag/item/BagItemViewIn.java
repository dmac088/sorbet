package io.nzbee.view.bag.item;

public class BagItemViewIn {

	private String itemUPC;
	
	private int itemQty;

	public BagItemViewIn() {
		
	}

	public String getItemUPC() {
		return itemUPC;
	}

	public void setItemUPC(String itemUPC) {
		this.itemUPC = itemUPC;
	}

	public int getItemQty() {
		return itemQty;
	}

	public void setItemQty(int qty) {
		this.itemQty = qty;
	}
	
}
