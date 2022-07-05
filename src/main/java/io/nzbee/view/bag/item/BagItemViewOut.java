package io.nzbee.view.bag.item;

import java.math.BigDecimal;

import io.nzbee.entity.bag.view.BagViewDTO;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;

public class BagItemViewOut {

	private String itemUPC;
	
	private String itemDesc;
	
	private int itemQty;
	
	private BigDecimal markdownPrice;
	
	private BigDecimal bagItemTotal;
	
	private BigDecimal bagItemWeight;
	
	public BagItemViewOut(BagViewDTO bDo, PhysicalProductFullView p, int quantity) {
		this.itemUPC = p.getProductUPC();
		this.itemDesc = p.getProductDesc();
		this.itemQty = quantity;
		this.markdownPrice = p.getProductMarkdown();
		this.bagItemWeight = p.getWeight();
		
	}

	public BagItemViewOut() {
		// TODO Auto-generated constructor stub
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

	public int getItemQty() {
		return itemQty;
	}

	public void setItemQty(int qty) {
		this.itemQty = qty;
	}
	
	public BigDecimal getMarkdownPrice() {
		return markdownPrice;
	}

	public void setMarkdownPrice(BigDecimal bigDecimal) {
		this.markdownPrice = bigDecimal;
	}

	public BigDecimal getBagItemTotal() {
		return bagItemTotal;
	}

	public void setBagItemTotal(BigDecimal bagItemTotal) {
		this.bagItemTotal = bagItemTotal;
	}

	public BigDecimal getBagItemWeight() {
		return bagItemWeight;
	}

	public void setBagItemWeight(BigDecimal bagItemWeight) {
		this.bagItemWeight = bagItemWeight;
	}
	
}
