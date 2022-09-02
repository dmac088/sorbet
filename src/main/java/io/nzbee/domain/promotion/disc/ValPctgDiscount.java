package io.nzbee.domain.promotion.disc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.nzbee.domain.bag.item.IDiscountableBagItem;
import io.nzbee.domain.promotion.DiscountItem;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.ports.IDiscountThresholdPromotionPort;

public class ValPctgDiscount extends Promotion implements IBagPromotion<IDiscountThresholdPromotionPort> {

	private final BigDecimal discountPctg;

	private final BigDecimal dollarThreshold;

	private final String currency;

	private final String direction;

	public ValPctgDiscount(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, BigDecimal discountPctg, Boolean active, Boolean couponRequired,
			String couponCode, BigDecimal dollarThreshold, String currency, String direction) {
		super(promotionTypeCode, promotionTypeCode, promotionEndDt, promotionEndDt, active, couponRequired, couponCode);

		this.discountPctg = discountPctg;
		this.dollarThreshold = dollarThreshold;
		this.currency = currency;
		this.direction = direction;
	}

	public BigDecimal getDiscountPctg() {
		return discountPctg;
	}

	public BigDecimal getDollarThreshold() {
		return dollarThreshold;
	}

	public String getCurrency() {
		return currency;
	}

	public String getDirection() {
		return direction;
	}

	@Override
	public List<DiscountItem> execute(IDiscountThresholdPromotionPort object) {
		List<DiscountItem> ldi = new ArrayList<DiscountItem>();
		object.getItems().stream().forEach(bi -> {
			if (bi.getBagTotalAmount().compareTo(this.getDollarThreshold()) >= 0) {
				ldi.add(applyDiscount(bi));
			}
		});
		return ldi;
	}

	private DiscountItem applyDiscount(IDiscountableBagItem bi) {
		BigDecimal amount = bi.getTotalAmount().multiply(discountPctg);
		return new DiscountItem(this.promotionCode, bi.getUPC(), amount);
	}

	@Override
	public Boolean qualifies() {
		// TODO Auto-generated method stub
		return null;
	}

}
