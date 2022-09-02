package io.nzbee.entity.promotion.disc;

import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.disc.PctgDiscount;

@Component(value = "promotionProductMapper")
public class PromotionDiscMapperImpl implements IPromotionDiscMapper {

	@Override
	public Promotion DTOToDo(PromotionDiscDTO dto) {		
		return new PctgDiscount(dto.getPromotion().getPromotionCode(), dto.getPromotion().getPromotionType().typeCode(), dto.getPromotion().getPromotionStartDate(),
				dto.getPromotion().getPromotionEndDate(), dto.getDiscountPercentage(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), dto.getPromotion().getCouponCode());
	}

	@Override
	public PromotionDiscEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
