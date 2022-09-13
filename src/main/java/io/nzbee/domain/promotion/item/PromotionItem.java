package io.nzbee.domain.promotion.item;

import java.util.Currency;
import java.util.List;

import io.nzbee.domain.valueObjects.BagID;
import io.nzbee.domain.valueObjects.BrandCode;
import io.nzbee.domain.valueObjects.CategoryCode;
import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.CustomerID;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;

public class PromotionItem implements IPromotionItem {

	private final CustomerID customerID;
	
	private final BagID bagID;
	
	private final Long numberOfOrders;
	
	private final ProductUPC itemUPC;
	
	private final Long quantity;
	
	private final BrandCode brandCode;
	
	private final List<CategoryCode> categoryCodes;

	private final Money itemPrice;
	
	private final List<CouponCode> couponCodes;
	
	private Money discountAmount;

	private final Money totalAmount;
	
	
	
	public PromotionItem(CustomerID customerID, BagID bagID, Long numberOfOrders, ProductUPC itemUPC, Long quantity,
			BrandCode brandCode, List<CategoryCode> categoryCodes, Currency currency, Money itemPrice,
			List<CouponCode> couponCodes, Money totalAmount) {
		super();
		this.customerID = customerID;
		this.bagID = bagID;
		this.numberOfOrders = numberOfOrders;
		this.itemUPC = itemUPC;
		this.quantity = quantity;
		this.brandCode = brandCode;
		this.categoryCodes = categoryCodes;
		this.itemPrice = itemPrice;
		this.couponCodes = couponCodes;
		this.totalAmount = totalAmount;
	}

	public CustomerID getCustomerID() {
		return customerID;
	}

	public BagID getBagID() {
		return bagID;
	}

	public Long getNumberOfOrders() {
		return numberOfOrders;
	}

	public ProductUPC getItemUPC() {
		return itemUPC;
	}

	public Long getQuantity() {
		return quantity;
	}

	public BrandCode getBrandCode() {
		return brandCode;
	}

	public List<CategoryCode> getCategoryCodes() {
		return categoryCodes;
	}
	
	@Override
	public void addDiscount(Money discount) {
		this.discountAmount.add(discount);
	}
	
	@Override
	public Money getDiscountAmount() {
		return this.discountAmount;
	}
	
	@Override
	public Money getTotalAmount() {
		return this.totalAmount;
	}

	@Override
	public Money getPrice() {
		return itemPrice;
	}

	@Override
	public List<CouponCode> getCoupons() {
		return couponCodes;
	}

}
