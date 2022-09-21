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
		return this.getBagItems().size();
	}

	public Long getTotalQuantity() {
		return this.getBagItems().stream().mapToLong(i -> i.getItemQty()).sum();
	}

	public BigDecimal getGrandTotalAmount() {
		BigDecimal sum = BigDecimal.ZERO;
        for (BagItemViewOut bi : this.bagItems) {
            sum = sum.add(bi.getBagItemTotal());
        }
		return sum;
	}

	public BigDecimal getSubTotalAmount() {
		BigDecimal sum = BigDecimal.ZERO;
        for (BagItemViewOut bi : this.bagItems) {
            sum = sum.add(bi.getBagItemSubTotal());
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
