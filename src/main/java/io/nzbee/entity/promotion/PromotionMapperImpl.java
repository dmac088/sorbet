package io.nzbee.entity.promotion;

import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.value.CouponCode;
import io.nzbee.domain.promotion.value.PromotionCode;

@Component(value = "promotionMapper")
public class PromotionMapperImpl implements IPromotionMapper {
	
	@Override
	public Promotion DTOToDo(PromotionDomainDTO dto) {
		return new Promotion(new PromotionCode(dto.getPromotionCode()),
							 dto.getPromotionStartDate(), 
							 dto.getPromotionEndDate(),
							 dto.getPromotionIsActive(), 
							 dto.getCouponRequired(), 
							 new CouponCode(dto.getCouponCode()));

	}

}
