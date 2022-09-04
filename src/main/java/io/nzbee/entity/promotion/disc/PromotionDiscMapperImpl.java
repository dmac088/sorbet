package io.nzbee.entity.promotion.disc;

import org.springframework.stereotype.Component;

import io.nzbee.Constants;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.disc.PctgDiscount;
import io.nzbee.entity.promotion.type.IPromotionTypeProductDTO;

@Component(value = "promotionProductMapper")
public class PromotionDiscMapperImpl implements IPromotionDiscMapper {

	@Override
	public PctgDiscount DTOToDo(PromotionDiscDTO dto) {		
		return getDomainObject(dto);
	}

	
	private PctgDiscount getDomainObject(PromotionDiscDTO dto) {
		 switch(dto.getTypeCode()) {
			case Constants.promotionTypeProduct:
				return  new PctgDiscount(dto.getPromotion().getPromotionCode(), dto.getPromotion().getPromotionType().typeCode(), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getDiscountPercentage(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), dto.getPromotion().getCouponCode(), ((IPromotionTypeProductDTO) dto.getPromotion().getPromotionType()).getUpcCode());
						
			default:
				return new PctgDiscount(dto.getPromotion().getPromotionCode(), dto.getPromotion().getPromotionType().typeCode(), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getDiscountPercentage(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), dto.getPromotion().getCouponCode());
		}
		
	}
	
	@Override
	public PromotionDiscEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
