package io.nzbee.domain.promotion.bag;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import io.nzbee.domain.promotion.bag.item.IPromotionBagItem;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.Quantity;

public class DroolsPromotionBagItemWrapper {
	
	private IPromotionBag bag;
	
	private IPromotionBagItem bagItem;
	
	public DroolsPromotionBagItemWrapper(IPromotionBagItem bagItem) {
		this.bag = bagItem.getBag();
		this.bagItem = bagItem;
	}
	
	public int getTotalBagItemCount() {
		return bag.getItemCount();
	}
	
	public Quantity getTotalBagQuantity() {
		return bag.getQuantity();
	}
	
	public Money getTotalBagAmount() {
		return bag.getTotalAmount();
	}

	public int getBagItemQuantity() {
		return bagItem.getQuantity().amount().intValue();
	}
	
	public BigDecimal getBagItemPrice() {
		return this.bagItem.getPrice().amount();
	}
	
	public BigDecimal getBagItemAmount() {
		return this.bagItem.getTotalAmount().amount();
	}
	
	public String getBrandCode() {
		return this.bagItem.getBrandCode().toString();
	}
	
	public List<String> getCategoryCodes() {
		return this.bagItem.getCategoryCodes().stream().map(c -> c.toString()).collect(Collectors.toList());
	}
	
	public List<String> getCouponCodes() {
		return this.bag.getCoupons().stream().map(c -> c.toString()).collect(Collectors.toList());
	}
	
}
