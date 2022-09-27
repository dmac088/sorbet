package io.nzbee.domain.promotion.bag.item;

import java.math.BigDecimal;
import io.nzbee.domain.promotion.bag.IPromotionBag;

public class DroolsPromotionBagItemWrapper {
	
	private IPromotionBagItem bagItem;
	
	public DroolsPromotionBagItemWrapper(IPromotionBagItem object) {
		this.bagItem = object;
	}
	
	public Long getBagItemQuantity() {
		return this.bagItem.getQuantity().amount();
	}
	
	public int getBagQuantity() {
		return this.bagItem.getBag().getQuantity().amount().intValue();
	}
	
	public BigDecimal getMarkdownPrice() {
		return this.bagItem.getPrice().amount();
	}
	
	public String getBagItemStatus() {
		return this.bagItem.getBagItemStatus().toString();
	}
	
	public String getProductUPC() {
		return this.bagItem.getItemUPC().toString();
	}
	
	public Boolean isErrors() {
		return this.bagItem.getBag().isErrors();
	}
	
	public void setErrors(Boolean errors) {
		this.bagItem.getBag().setErrors(errors);
	}

	public String getError() {
		return this.bagItem.getBag().getError();
	}

	public void setError(String error) {
		this.bagItem.getBag().setError(error);
	}
	
	public IPromotionBag getBag() {
		return bagItem.getBag();
	}
	
}