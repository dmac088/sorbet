package io.nzbee.entity.promotion.valdisc;

import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.disc.ValPctgDiscount;

@Component
public class PromotionValDiscMapperImpl implements IPromotionValDiscMapper {

	@Override
	public Promotion DTOToDo(PromotionValDiscDTO dto) {		
		return new ValPctgDiscount(dto.getPromotion().getPromotionCode(), dto.getPromotion().getPromotionType().typeCode(), dto.getPromotion().getPromotionStartDate(),
				dto.getPromotion().getPromotionEndDate(), dto.getDiscountPercentage(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), dto.getPromotion().getCouponCode(),
				dto.getValueThreshold(), dto.getCurrency(), dto.getDirection());
	}

	@Override
	public PromotionValDiscEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
