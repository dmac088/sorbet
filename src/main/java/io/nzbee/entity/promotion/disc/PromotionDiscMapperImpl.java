package io.nzbee.entity.promotion.disc;

import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.disc.PctgDiscount;

@Component(value = "promotionProductMapper")
public class PromotionDiscMapperImpl implements IPromotionDiscMapper {

	@Override
	public Promotion DTOToDo(PromotionDiscDTO dto) {		
		return new PctgDiscount(dto.getPromotionCode(), dto.getPromotionTypeCode(), dto.getPromotionStartDate(),
				dto.getPromotionEndDate(), dto.getDiscountPercentage(), dto.getPromotionIsActive());
	}

	@Override
	public PromotionDiscEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
