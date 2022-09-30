package io.nzbee.domain.promotion.bag.item;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.valueObjects.BagID;
import io.nzbee.domain.valueObjects.BagItemStatus;
import io.nzbee.domain.valueObjects.BrandCode;
import io.nzbee.domain.valueObjects.CategoryCode;
import io.nzbee.domain.valueObjects.CustomerID;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.Percentage;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;

public class PromotionBagItem implements IPromotionBagItem {

	private final IPromotionBag bag;
	
	private final CustomerID customerID;
	
	private final BagID bagID;
	
	private final ProductUPC itemUPC;
	
	private final Quantity quantity;
	
	private final BrandCode brandCode;
	
	private final List<CategoryCode> categoryCodes;
	
	private final BagItemStatus bagItemStatus;

	private final Money itemPrice;
	
	private Percentage discountPercentage;
	
	
	public PromotionBagItem(IPromotionBag bag, CustomerID customerID, BagID bagID, ProductUPC itemUPC, Quantity quantity,
			BrandCode brandCode, List<CategoryCode> categoryCodes, Currency currency, Money itemPrice, BagItemStatus bagItemStatus) {
		super();
		this.bag = bag;
		this.customerID = customerID;
		this.bagID = bagID;
		this.itemUPC = itemUPC;
		this.quantity = quantity;
		this.brandCode = brandCode;
		this.categoryCodes = categoryCodes;
		this.itemPrice = itemPrice;
		this.discountPercentage = new Percentage(new BigDecimal(0));
		this.bagItemStatus = bagItemStatus;
	}


	public CustomerID getCustomerID() {
		return customerID;
	}

	public BagID getBagID() {
		return bagID;
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
	public void addDiscount(Percentage discount) {
		System.out.println("adding discount amount: " + discount.amount());
		this.discountPercentage = new Percentage(discount.amount());
	}
	
	@Override
	public Money getTotalAmount() {
		return this.itemPrice.multiply(this.quantity.amount()).multiply(BigDecimal.ONE.subtract(this.discountPercentage.amount()));
	}

	@Override
	public Money getPrice() {
		return itemPrice;
	}

	@Override
	public Percentage getDiscountPercentage() {
		return this.discountPercentage;
	}

	@Override
	public IPromotionBag getBag() {
		return this.bag;
	}

	@Override
	public BagItemStatus getBagItemStatus() {
		return this.bagItemStatus;
	}
	

}
