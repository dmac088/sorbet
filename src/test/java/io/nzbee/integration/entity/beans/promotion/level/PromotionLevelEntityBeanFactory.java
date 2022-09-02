package io.nzbee.integration.entity.beans.promotion.level;


import org.springframework.stereotype.Service;

import io.nzbee.entity.promotion.type.PromotionTypeEntity;

@Service

public class PromotionLevelEntityBeanFactory implements IPromotionLevelEntityBeanFactory {

	@Override
	public final PromotionTypeEntity getBean() {
		PromotionTypeEntity promotionLevel = new PromotionTypeEntity();
		promotionLevel.setPromotionLevelCode("TST01");
		promotionLevel.setPromotionLevelDesc("test promotion level");
		return promotionLevel;
	}
	
}
