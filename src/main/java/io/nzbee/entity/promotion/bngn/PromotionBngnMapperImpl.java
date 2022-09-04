package io.nzbee.entity.promotion.bngn;

import java.util.Map;

import org.springframework.stereotype.Component;

import io.nzbee.Constants;
import io.nzbee.domain.promotion.bngn.BuyNGetNFree;
import io.nzbee.entity.promotion.PromotionDomainDTO;
import io.nzbee.entity.promotion.disc.PromotionDiscDTO;
import io.nzbee.entity.promotion.type.IPromotionTypeProductDTO;
import io.nzbee.entity.promotion.valdisc.IPromotionDTO;
import io.nzbee.entity.promotion.valdisc.PromotionValDiscDTO;

@Component(value = "promotionOrderMapper")
public class PromotionBngnMapperImpl implements IPromotionBngnMapper {

	@Override
	public BuyNGetNFree DTOToDo(PromotionBngnDTO dto) {

		return 

	}
	
	private BuyNGetNFree getSubType(PromotionBngnDTO dto) {
		 switch(dto.getTypeCode()) {
			case Constants.promotionTypeProduct:
				return new BuyNGetNFree(dto.getPromotion().getPromotionCode(), dto.getPromotion().getPromotionType().typeCode(), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), dto.getPromotion().getCouponCode(), 
						dto.getBuyQuantity(), dto.getDiscountPercentage(), ((IPromotionTypeProductDTO) dto.getPromotion().getPromotionType()).getUpcCode());
		
			default:
				return new BuyNGetNFree(dto.getPromotion().getPromotionCode(), dto.getPromotion().getPromotionType().typeCode(), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), dto.getPromotion().getCouponCode(), 
						dto.getBuyQuantity(), dto.getDiscountPercentage());
		}
		
	}

	@Override
	public PromotionBngnEntity doToEntity(BuyNGetNFree d) {
		// TODO Auto-generated method stub
		return null;
	}

}
