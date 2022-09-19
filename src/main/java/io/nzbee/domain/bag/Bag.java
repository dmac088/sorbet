package io.nzbee.domain.bag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import io.nzbee.Constants;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.IBagItem;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;

public class Bag implements IBag {
	
	private IShippingBagItem shippingItem;
	
	private Customer customer;
	
	private BagIssues bagIssues = new BagIssues();
	
	private final List<CouponCode> coupons; 
	
	private final List<IRegularBagItem> bagItems;
	
	private final Locale locale;
	
	
	public Bag(Customer customer, Locale locale) {
		this.customer = customer;
		this.bagItems = new ArrayList<>();
		this.coupons = new ArrayList<CouponCode>();
		this.locale = locale;
	}
	
	public Bag(Customer customer) {
		this.customer = customer;
		this.bagItems = new ArrayList<>();
		this.coupons = new ArrayList<CouponCode>();
		this.locale = Constants.defaultLocale;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public List<IRegularBagItem> getBagItems() {
		return bagItems;
	}
	
	public void addItem(IRegularBagItem bagItem, Long qty) {
		System.out.println("adding quantity " + qty + " for product " + bagItem.getBagItem().getProductUPC());
		
		Optional<IRegularBagItem> obi = this.getBagItems().stream()
		.filter(bi -> bi.getBagItem().getProductUPC().sameAs(bagItem.getBagItem().getProductUPC()))
		.findAny();
		
		if(obi.isPresent()) {
			obi.get().getBagItem().addToQuantity(qty);
		} else {
			this.getBagItems().add(new RegularBagItem(new BagItem(this, bagItem.getBagItem().getProductUPC(), qty, bagItem.getBagItem().getMarkdownPrice()), bagItem.getBagItemWeight(), bagItem.isInStock()));
		}
	}
	
	public void addShippingItem(IShippingBagItem sbi) {	
		this.shippingItem = sbi;	
	}
	
	public Boolean bagItemExists(ProductUPC productUPC) {
		return this.getBagItems().stream().filter(bi -> bi.getBagItem().getProductUPC().sameAs(productUPC)).findAny().isPresent();
	}
	
	public IRegularBagItem getBagItem(ProductUPC productUPC) {
		return this.getBagItems().stream().filter(bi -> bi.getBagItem().getProductUPC().sameAs(productUPC)).findAny().get();
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
	
	public IShippingBagItem getShippingItem() {
		return shippingItem;
	}
	
	public Boolean hasShippingItem() {
		return !(shippingItem == null);
	}

	public BigDecimal getTotalWeight() {
		BigDecimal sum = BigDecimal.ZERO;
        for (IRegularBagItem bi : this.getBagItems()) {
            sum = sum.add(bi.getBagItemWeight().multiply(bi.getBagItem().getQuantity()));
        }
		return sum;
	}
	
	public Money getGrandTotalAmount() {
		Money sum = this.getMoney();
        for (IRegularBagItem bi : this.getBagItems()) {
            sum = sum.add(bi.getBagItem().getBagItemTotal());
        }
        if(hasShippingItem()) {
        	System.out.println("the bag has a shipping item");
        	System.out.println("the amount is: " + shippingItem.getBagItem().getBagItemTotal());
        	sum = sum.add(shippingItem.getBagItem().getBagItemTotal());
        }
		return sum;
	}
	
	public Money getSubTotalAmount() {
		Money sum = this.getMoney();
        for (IRegularBagItem bi : this.getBagItems()) {
            sum = sum.add(bi.getBagItem().getBagItemTotal());
        }
		return sum;
	}
	
	@Override
	public void logItemError(String key, IBagItem bagItem) {
		bagIssues.logItemError(key, bagItem);
		bagItem.setBagItemStatus("PND01");
	}
	
	public BagIssues getBagIssues() {
		return bagIssues;
	}

	public void addCoupon(CouponCode couponCode) {
		if(!this.getCoupons().contains(couponCode)) {
			this.getCoupons().add(couponCode);
		}
	}

	public List<CouponCode> getCoupons() {
		return coupons;
	}

	public Boolean hasCoupon() {
		return this.coupons.size() > 0;
	}
	
	public Boolean hasIssues() {
		return bagIssues.hasIssues();
	}
	
	public void removeShippingItem() {
		this.shippingItem = null;
	}

	public List<IBagItem> getItems() {
		return null;
	}

	public Money getMoney() {
		return new Money(BigDecimal.ZERO, this.getLocale().getCurrency(), Constants.defaultMoneyRounding);
	}

	@Override
	public String getUserName() {
		return this.getCustomer().getUserName();
	}

	public Locale getLocale() {
		return this.locale;
	}
	
}
