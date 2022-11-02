package io.nzbee.view.bag.item;

import java.math.BigDecimal;

public class BagItemViewOut {

	private Long itemId;
	
	private String itemUPC;
	
	private String itemDesc;
	
	private Long itemQty;
	
	private BigDecimal markdownPrice;
	
	private BigDecimal bagItemWeight;
	
	private BigDecimal bagItemDiscountPercentage = new BigDecimal(0);
	

	public BagItemViewOut() {
		// TODO Auto-generated constructor stub
	}
	
	private boolean hasQuantity() {
		return (this.itemQty != null && this.itemQty > 0);
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemUPC() {
		return itemUPC;
	}

	public void setItemUPC(String itemUPC) {
		this.itemUPC = itemUPC;
	}
	
	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Long getItemQty() {
		return itemQty;
	}

	public void setItemQty(Long quantity) {
		this.itemQty = quantity;
	}
	
	public BigDecimal getMarkdownPrice() {
		return markdownPrice;
	}

	public void setMarkdownPrice(BigDecimal bigDecimal) {
		this.markdownPrice = bigDecimal;
	}
	
	public BigDecimal getBagItemSubTotal() {
		return 	this.hasQuantity() 
		? this.getMarkdownPrice().multiply(new BigDecimal(this.itemQty))
		: BigDecimal.ZERO;
	}
	
	public BigDecimal getBagItemTotal() {
		return 	this.hasQuantity() 
				? getBagItemSubTotal().multiply(BigDecimal.ONE.subtract(this.bagItemDiscountPercentage))
				: BigDecimal.ZERO;
	}

	public BigDecimal getBagItemWeight() {
		return 	this.hasQuantity() 
				? bagItemWeight.multiply(new BigDecimal(this.itemQty))
				: BigDecimal.ZERO;
	}

	public void setBagItemWeight(BigDecimal bagItemWeight) {
		this.bagItemWeight = bagItemWeight;
	}

	public BigDecimal getBagItemDiscount() {
		return this.getMarkdownPrice().multiply(new BigDecimal(this.itemQty)).multiply(this.bagItemDiscountPercentage);
	}

	public void setBagItemDiscountPercentage(BigDecimal bagItemDiscountPercentage) {
		this.bagItemDiscountPercentage = bagItemDiscountPercentage;
	}
	
}
