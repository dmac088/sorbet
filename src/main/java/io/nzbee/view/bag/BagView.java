package io.nzbee.view.bag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.view.bag.item.BagItemViewOut;

public class BagView {

	private String bagStatusCode;
	
	private String bagStatusDesc;
	
	private int totalItems;
	
	private Long totalQuantity;
	
	private BigDecimal grandTotalAmount;
	
	private BigDecimal subTotalAmount;
	
	private BigDecimal totalWeight;
	
	@JsonIgnore
	private Set<BagItemViewOut> bagItems = new HashSet<BagItemViewOut>();
	
	private BagItemViewOut shippingItem;
	
	private List<String> coupons = new ArrayList<String>();
	

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

	public Long getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Long totalQuantity) {
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

	public List<String> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<String> coupons) {
		this.coupons = coupons;
	}
	
}
