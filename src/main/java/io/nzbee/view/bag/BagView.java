package io.nzbee.view.bag;

import java.math.BigDecimal;
import java.util.Set;

import io.nzbee.view.bag.item.BagItemViewOut;

public class BagView {

	private String bagStatusCode;
	
	private String bagStatusDesc;
	
	private int totalItems;
	
	private int totalQuantity;
	
	private BigDecimal totalAmount;
	
	private BigDecimal totalWeight;
	
	private Set<BagItemViewOut> bagItems;
	

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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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
	
	
	
}
