package io.nzbee.entity.promotion.valdisc;

import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.disc.ValPctgDiscount;

@Component
public class PromotionValDiscMapperImpl implements IPromotionValDiscMapper {

	@Override
	public Promotion DTOToDo(PromotionValDiscDTO dto) {		
		return new ValPctgDiscount(dto.getPromotionCode(), dto.getPromotionTypeCode(), dto.getPromotionStartDate(),
				dto.getPromotionEndDate(), dto.getDiscountPercentage(), dto.getPromotionIsActive(), dto.getCouponRequired(), dto.getCouponCode(),
				dto.getValueThreshold(), dto.getCurrency(), dto.getDirection());
	}

	@Override
	public PromotionValDiscEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
