package io.nzbee.domain.bag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.IDiscountableBagItem;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.promotion.ports.IBnGnFreePromotionPort;
import io.nzbee.domain.promotion.ports.IDiscountThresholdPromotionPort;
import io.nzbee.domain.promotion.ports.IPctgDiscountPromotionPort;

public class Bag implements IDiscountThresholdPromotionPort, IPctgDiscountPromotionPort, IBnGnFreePromotionPort {
	
	private ShippingBagItem shippingItem;
	
	private Customer customer;
	
	private BagIssues bagIssues = new BagIssues();
	
	private final List<String> coupons; 
	
	private final List<RegularBagItem> bagItems;

	public Bag(Customer customer) {
		this.customer = customer;
		this.bagItems = new ArrayList<>();
		this.coupons = new ArrayList<String>();
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public List<RegularBagItem> getBagItems() {
		return bagItems;
	}
	
	@Override
	public List<IDiscountableBagItem> getDiscountableItems() {
		return this.getBagItems().stream().map(i -> (IDiscountableBagItem) i).collect(Collectors.toList());
	}
	
	public void addItem(RegularBagItem p, Long qty) {
		System.out.println("adding quantity " + qty + " for product " + p.getBagItem().getProductUPC());
		
		Optional<RegularBagItem> obi = this.getBagItems().stream()
		.filter(bi -> bi.getBagItem().getProductUPC().equals(p.getBagItem().getProductUPC()))
		.findAny();
		
		if(obi.isPresent()) {
			obi.get().getBagItem().addToQuantity(qty);
		} else {
			this.getBagItems().add(new RegularBagItem(new BagItem(this, p.getBagItem().getProductUPC(), qty, p.getBagItem().getMarkdownPrice()), p.getBagItemWeight(), p.isInStock()));
		}
	}
	
	public void addShippingItem(ShippingBagItem p) {	
		this.shippingItem = p;	
	}
	
	public boolean bagItemExists(String productUPC) {
		return this.getBagItems().stream().filter(bi -> bi.getBagItem().getProductUPC().equals(productUPC)).findAny().isPresent();
	}
	
	public RegularBagItem getBagItem(String productUPC) {
		return this.getBagItems().stream().filter(bi -> bi.getBagItem().getProductUPC().equals(productUPC)).findAny().get();
	}
	
	public void removeItem(RegularBagItem bi) {
		this.getBagItems().remove(bi);
	}
	
	public int getTotalItems() {
		return this.getBagItems().size();
	}
	
	@Override
	public Long getTotalQuantity() {
		return new Long(this.getBagItems().stream()
				.mapToInt(r -> r.getBagItem().getQuantity().intValue()).sum());
	}
	
	public ShippingBagItem getShippingItem() {
		return shippingItem;
	}
	
	public Boolean hasShippingItem() {
		return !(shippingItem == null);
	}

	public BigDecimal getTotalWeight() {
		BigDecimal sum = BigDecimal.ZERO;
        for (RegularBagItem bi : this.getBagItems()) {
            sum = sum.add(bi.getBagItemWeight().multiply(new BigDecimal(bi.getBagItem().getQuantity())));
        }
		return sum;
	}
	
	public BigDecimal getGrandTotalAmount() {
		BigDecimal sum = BigDecimal.ZERO;
        for (RegularBagItem bi : this.getBagItems()) {
            sum = sum.add(bi.getBagItem().getBagItemTotal());
        }
        if(hasShippingItem()) {
        	System.out.println("the bag has a shipping item");
        	System.out.println("the amount is: " + shippingItem.getBagItem().getBagItemTotal());
        	sum = sum.add(shippingItem.getBagItem().getBagItemTotal());
        }
		return sum;
	}
	
	public BigDecimal getSubTotalAmount() {
		BigDecimal sum = BigDecimal.ZERO;
        for (RegularBagItem bi : this.getBagItems()) {
            sum = sum.add(bi.getBagItem().getBagItemTotal());
        }
		return sum;
	}
	
	public void logItemError(String key, BagItem bagItem) {
		bagIssues.logItemError(key, bagItem);
		bagItem.setBagItemStatus("PND01");
	}
	
	public BagIssues getBagIssues() {
		return bagIssues;
	}

	public void addCoupon(String couponCode) {
		if(!this.getCoupons().contains(couponCode)) {
			this.getCoupons().add(couponCode);
		}
	}

	public List<String> getCoupons() {
		return coupons;
	}

	public boolean hasCoupon() {
		return this.coupons.size() > 0;
	}
	
	public boolean hasIssues() {
		return bagIssues.hasIssues();
	}
	
	public void removeShippingItem() {
		this.shippingItem = null;
	}

	@Override
	public List<IDiscountableBagItem> getItems() {
		return this.getBagItems().stream().map(i -> (IDiscountableBagItem) i).collect(Collectors.toList());
	}

	@Override
	public BigDecimal getTotalAmount() {
		return this.getSubTotalAmount();
	}


}
