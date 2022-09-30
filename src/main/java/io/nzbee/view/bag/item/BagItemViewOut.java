package io.nzbee.view.bag.item;

import java.math.BigDecimal;

public class BagItemViewOut {

	private Long itemId;
	
	private String itemUPC;
	
	private String itemDesc;
	
	private Long itemQty;
	
	private BigDecimal markdownPrice;
	
	private BigDecimal bagItemWeight;
	
	private BigDecimal bagItemDiscountPercentage;
	

	public BagItemViewOut() {
		// TODO Auto-generated constructor stub
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
		return this.getMarkdownPrice().multiply(new BigDecimal(this.itemQty));
	}
	
	public BigDecimal getBagItemTotal() {
		return this.getMarkdownPrice().multiply(new BigDecimal(this.itemQty)).multiply(BigDecimal.ONE.subtract(this.bagItemDiscountPercentage));
	}

	public BigDecimal getBagItemWeight() {
		return bagItemWeight.multiply(new BigDecimal(this.itemQty));
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
