package io.nzbee.entity.promotion.bngn;

import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.bngn.BuyNGetNFree;

@Component(value = "promotionOrderMapper")
public class PromotionBngnMapperImpl implements IPromotionBngnMapper {

	@Override
	public BuyNGetNFree DTOToDo(PromotionBngnDTO dto) {

		return new BuyNGetNFree(dto.getPromotion().getPromotionCode(), dto.getPromotion().getPromotionType().typeCode(), dto.getPromotion().getPromotionStartDate(),
				dto.getPromotion().getPromotionEndDate(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), dto.getPromotion().getCouponCode(), 
				dto.getBuyQuantity(), dto.getDiscountPercentage());

	}

	@Override
	public PromotionBngnEntity doToEntity(BuyNGetNFree d) {
		// TODO Auto-generated method stub
		return null;
	}

}
