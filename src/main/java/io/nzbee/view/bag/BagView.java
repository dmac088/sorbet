package io.nzbee.view.bag;

import java.math.BigDecimal;
import java.util.Set;

import io.nzbee.view.bag.item.BagItemViewOut;

public class BagView {

	private String bagStatusCode;
	
	private String bagStatusDesc;
	
	private int totalItems;
	
	private int totalQuantity;
	
	private BigDecimal grandTotalAmount;
	
	private BigDecimal subTotalAmount;
	
	private BigDecimal totalWeight;
	
	private Set<BagItemViewOut> bagItems;
	
	private BagItemViewOut shippingItem;
	
	

	public String getBagStatusCode() {
		return bagStatusCode;
	}

	public void setBagStatusCode(String bagStatusCode) {
		this.bagStatusCode = bagStatusCode;
	}

	public String getBagStatusDesc() {
		return bagStatusDesc;
	}

	public void setBagStatusDesc(String bagStatusDesc) {
		this.bagStatusDesc = bagStatusDesc;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getGrandTotalAmount() {
		return grandTotalAmount;
	}

	public void setGrandTotalAmount(BigDecimal grandTotalAmount) {
		this.grandTotalAmount = grandTotalAmount;
	}

	public BigDecimal getSubTotalAmount() {
		return subTotalAmount;
	}

	public void setSubTotalAmount(BigDecimal subTotalAmount) {
		this.subTotalAmount = subTotalAmount;
	}

	public BigDecimal getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Set<BagItemViewOut> getBagItems() {
		return bagItems;
	}

	public void setBagItems(Set<BagItemViewOut> bagItems) {
		this.bagItems = bagItems;
	}

	public BagItemViewOut getShippingItem() {
		return shippingItem;
	}

	public void setShippingItem(BagItemViewOut shippingItem) {
		this.shippingItem = shippingItem;
	}
	
}
