package io.nzbee.domain.promotion.bag.item;

import java.util.Currency;
import java.util.List;

import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.valueObjects.BagID;
import io.nzbee.domain.valueObjects.BagItemStatus;
import io.nzbee.domain.valueObjects.BrandCode;
import io.nzbee.domain.valueObjects.CategoryCode;
import io.nzbee.domain.valueObjects.CustomerID;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;

public class PromotionBagItem implements IPromotionBagItem {

	private final IPromotionBag bag;
	
	private final CustomerID customerID;
	
	private final BagID bagID;
	
	private final Long numberOfOrders;
	
	private final ProductUPC itemUPC;
	
	private final Quantity quantity;
	
	private final BrandCode brandCode;
	
	private final List<CategoryCode> categoryCodes;
	
	private final BagItemStatus bagItemStatus;

	private final Money itemPrice;
	
	private Money discountAmount;
	
	private List<IPromotionDiscountItem> discountItems;
	
	
	public PromotionBagItem(IPromotionBag bag, CustomerID customerID, BagID bagID, Long numberOfOrders, ProductUPC itemUPC, Quantity quantity,
			BrandCode brandCode, List<CategoryCode> categoryCodes, Currency currency, Money itemPrice, BagItemStatus bagItemStatus) {
		super();
		this.bag = bag;
		this.customerID = customerID;
		this.bagID = bagID;
		this.numberOfOrders = numberOfOrders;
		this.itemUPC = itemUPC;
		this.quantity = quantity;
		this.brandCode = brandCode;
		this.categoryCodes = categoryCodes;
		this.itemPrice = itemPrice;
		this.discountAmount = new Money();
		this.bagItemStatus = bagItemStatus;
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

	public Quantity getQuantity() {
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
	public Money getTotalAmount() {
		return this.itemPrice.multiply(this.quantity.amount());
	}

	@Override
	public Money getPrice() {
		return itemPrice;
	}

	@Override
	public Money getDiscountAmount() {
		return this.discountAmount;
	}

	@Override
	public IPromotionBag getBag() {
		return this.bag;
	}

	@Override
	public BagItemStatus getBagItemStatus() {
		return bagItemStatus;
	}
	
	@Override
	public void addDiscountItem(IPromotionDiscountItem discountItem) {
		this.discountItems.add(discountItem);
	}

}
