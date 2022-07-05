package io.nzbee.integration.entity.beans.promotion.type;


import org.springframework.stereotype.Service;
import io.nzbee.entity.promotion.type.PromotionTypeEntity;

@Service

public class PromotionTypeEntityBeanFactory implements IPromotionTypeEntityBeanFactory {

	@Override
	public final PromotionTypeEntity getBean() {
		PromotionTypeEntity promotionType = new PromotionTypeEntity();
		promotionType.setPromotionTypeCode("TST01");
		promotionType.setPromotionTypeDesc("test promotion type");
		promotionType.setPromotionClass("TestPromotionType");
		return promotionType;
	}
	
}
