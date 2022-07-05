package io.nzbee.integration.entity.beans.promotion.level;


import org.springframework.stereotype.Service;
import io.nzbee.entity.promotion.level.PromotionLevelEntity;

@Service

public class PromotionLevelEntityBeanFactory implements IPromotionLevelEntityBeanFactory {

	@Override
	public final PromotionLevelEntity getBean() {
		PromotionLevelEntity promotionLevel = new PromotionLevelEntity();
		promotionLevel.setPromotionLevelCode("TST01");
		promotionLevel.setPromotionLevelDesc("test promotion level");
		return promotionLevel;
	}
	
}
