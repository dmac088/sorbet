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
	
	private BigDecimal bagWeight;
	
	@JsonIgnore
	private Set<BagItemViewOut> bagItems = new HashSet<BagItemViewOut>();
	
	private BagItemViewOut shippingItem;
	
	private List<String> coupons = new ArrayList<String>();
	
	public Boolean hasShippingItem() {
		return !(this.shippingItem == null);
	}

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
		return this.getBagItems().size();
	}

	public Long getTotalQuantity() {
		return this.getBagItems().stream().mapToLong(i -> i.getItemQty()).sum();
	}
	
	public BigDecimal getTotalAmount() {
		BigDecimal sum = BigDecimal.ZERO;
        for (BagItemViewOut bi : this.bagItems) {
            sum = sum.add(bi.getBagItemSubTotal());
        }
		return sum;
	}
	
	public BigDecimal getGrandTotalAmount() {
		if(this.hasShippingItem()) {
			return this.getSubTotalAmount().add(this.getShippingItem().getBagItemTotal());
		} 
		return this.getSubTotalAmount();
	}

	public BigDecimal getSubTotalAmount() {
		BigDecimal sum = BigDecimal.ZERO;
        for (BagItemViewOut bi : this.bagItems) {
            sum = sum.add(bi.getBagItemTotal());
        }
		return sum;
	}

	public BigDecimal getTotalWeight() {
		BigDecimal sum = BigDecimal.ZERO;
        for (BagItemViewOut bi : this.bagItems) {
            sum = sum.add(bi.getBagItemWeight());
        }
		return sum;
	}
	
	public BigDecimal getTotalDiscount() {
		BigDecimal sum = BigDecimal.ZERO;
        for (BagItemViewOut bi : this.bagItems) {
            sum = sum.add(bi.getBagItemDiscount());
        }
		return sum;
	}

	public BigDecimal getBagWeight() {
		return bagWeight;
	}

	public void setBagWeight(BigDecimal bagWeight) {
		this.bagWeight = bagWeight;
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
