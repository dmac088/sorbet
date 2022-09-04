package io.nzbee.entity.promotion.valdisc;

import org.springframework.stereotype.Component;

import io.nzbee.Constants;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.disc.ValPctgDiscount;
import io.nzbee.entity.promotion.type.IPromotionTypeProductDTO;

@Component
public class PromotionValDiscMapperImpl implements IPromotionValDiscMapper {

	@Override
	public ValPctgDiscount DTOToDo(PromotionValDiscDTO dto) {		
		return getDomainObject(dto);
	}

	private ValPctgDiscount getDomainObject(PromotionValDiscDTO dto) {
		 switch(dto.getTypeCode()) {
			case Constants.promotionTypeProduct:
				return new ValPctgDiscount(
						dto.getPromotion().getPromotionCode(), 
						dto.getPromotion()
									.getPromotionType()
									.typeCode(),
						dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(),
						dto.getDiscountPercentage(), 
						dto.getPromotion().getPromotionIsActive(), 
						dto.getPromotion().getCouponRequired(), 
						dto.getPromotion().getCouponCode(),
						dto.getValueThreshold(), 
						dto.getCurrency(), 
						dto.getDirection(),
						((IPromotionTypeProductDTO) dto.getPromotion().getPromotionType()).getUpcCode());
			default:
				return  new ValPctgDiscount(
						dto.getPromotion().getPromotionCode(), 
						dto.getPromotion()
						.getPromotionType()
						.typeCode(),
						dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(),
						dto.getDiscountPercentage(), 
						dto.getPromotion().getPromotionIsActive(), 
						dto.getPromotion().getCouponRequired(), 
						dto.getPromotion().getCouponCode(),
						dto.getValueThreshold(), 
						dto.getCurrency(), 
						dto.getDirection());
		
			}
			
		}

	@Override
	public PromotionValDiscEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
