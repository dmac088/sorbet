package io.nzbee.domain.promotion.bag;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import io.nzbee.Constants;
import io.nzbee.domain.promotion.bag.item.IPromotionBagItem;
import io.nzbee.domain.valueObjects.Money;

public class DroolsPromotionBagItemWrapper implements IDroolsPromotionBagItemWrapper {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private final IPromotionBag bag;
	
	private final IPromotionBagItem bagItem;
	
	public DroolsPromotionBagItemWrapper(IPromotionBagItem bagItem) {
		this.bag = bagItem.getBag();
		this.bagItem = bagItem;
	}
	
	public int getTotalBagItemCount() {
		return bag.getItemCount();
	}
	
	public int getTotalBagQuantity() {
		return bag.getQuantity().amount().intValue();
	}
	
	public BigDecimal getTotalBagAmount() {
		return bag.getTotalAmount().amount();
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

	public IPromotionBagItem getPromotionBagItem() {
		return bagItem;
	}
	
	public Currency getCurrency() {
		return this.bag.getLocale().getCurrency();
	}
	
	public void addDiscount(BigDecimal discount) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addDiscount() with parameter: {}", discount);
		Money md = new Money(discount, this.getCurrency(), Constants.defaultMoneyRounding);
		LOGGER.debug("created new Money() with amount: {}", md.amount());
		this.bagItem.addDiscount(md);
	}
	
	
}
