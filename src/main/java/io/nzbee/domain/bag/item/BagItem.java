package io.nzbee.domain.bag.item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import io.nzbee.Constants;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.valueObjects.BagItemStatus;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;

public class BagItem implements IBagItem {

	private IBag bag;
	
	private final ProductUPC productUPC;
	
	private Quantity quantity;
	
	private BagItemStatus bagItemStatus;
	
	private final Money markdownPrice;
	
	private final List<IBagItemDiscount> discounts;

	
	public BagItem(	IBag bag, 
					ProductUPC productUPC,
			  	   	Quantity quantity,
			  	   	Money markdownPrice) {
		this.bag 				= bag;
		this.productUPC			= productUPC;
		this.quantity 			= quantity;
		this.bagItemStatus 		= new BagItemStatus(Constants.bagItemStatusCodeNew);
		this.markdownPrice 		= markdownPrice;
		this.discounts 			= new ArrayList<IBagItemDiscount>();
	}

	public IBag getBag() {
		return bag;
	}

	public Quantity getQuantity() {
		return quantity;
	}
	
	public void addToQuantity(Quantity quantity) {
		this.quantity = this.quantity.add(quantity);
	}

	public ProductUPC getProductUPC() {
		return this.productUPC;
	}
	
	public Money getMarkdownPrice() {
		return markdownPrice;
	}
	
	public Money getBagItemSubTotal() {
//		System.out.println("getBagItemSubTotal()");
//		System.out.println(this.markdownPrice.multiply(this.quantity).amount());
//		System.out.println("markdownPrice = " +  this.markdownPrice.amount());
//		System.out.println("quantity = " +  this.quantity);
		return this.markdownPrice.multiply(this.quantity.amount());
	}
	
	@Override
	public Money getBagItemTotal() {
//		System.out.println("getBagItemTotal()");
//		System.out.println(this.markdownPrice.multiply(this.quantity).subtract(this.getBagItemDiscountTotal()).amount());
//		System.out.println("markdownPrice = " +  this.markdownPrice.amount());
//		System.out.println("quantity = " +  this.quantity);
		return this.markdownPrice.multiply(this.quantity.amount()).subtract(this.getBagItemDiscountTotal());
	}
	
	public void setBagItemStatus(BagItemStatus bagItemStatus) {
		this.bagItemStatus = bagItemStatus;
	}

	public BagItemStatus getBagItemStatus() {
		return bagItemStatus;
	}
	
	@Override
	public Money getBagItemDiscountTotal() {
		BigDecimal b = this.getDiscounts().stream().map(d -> d.getDiscountAmount().amount()).reduce(BigDecimal.ZERO, BigDecimal::add);
		return bag.getMoney().add(new Money(b, bag.getLocale().getCurrency(), Constants.defaultMoneyRounding));
	}
	
	public void addDiscount(IBagItemDiscount discount) {
		this.discounts.add(discount);
	}

	public List<IBagItemDiscount> getDiscounts() {
		return discounts;
	}

}
