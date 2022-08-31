package io.nzbee.entity.promotion.bngn;

import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.bngn.BuyNGetNFree;

@Component(value = "promotionOrderMapper")
public class PromotionBngnMapperImpl implements IPromotionBngnMapper {

	@Override
	public Promotion DTOToDo(PromotionBngnDTO dto) {

		return new BuyNGetNFree(dto.getPromotionCode(), dto.getPromotionTypeCode(), dto.getPromotionStartDate(),
				dto.getPromotionEndDate(), dto.getBuyQuantity(), dto.getDiscountPercentage());

	}

	@Override
	public PromotionBngnEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
