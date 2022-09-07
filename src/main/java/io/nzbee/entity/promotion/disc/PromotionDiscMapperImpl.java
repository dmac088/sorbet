package io.nzbee.entity.promotion.disc;

import org.springframework.stereotype.Component;

import io.nzbee.Constants;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.disc.PctgDiscount;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.CouponCode;
import io.nzbee.domain.promotion.value.ProductUPC;
import io.nzbee.domain.promotion.value.PromotionCode;
import io.nzbee.domain.promotion.value.PromotionTypeCode;
import io.nzbee.entity.promotion.type.IPromotionTypeBrandDTO;
import io.nzbee.entity.promotion.type.IPromotionTypeCategoryDTO;
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
				return  new PctgDiscount(new PromotionCode(dto.getPromotion().getPromotionCode()), new PromotionTypeCode(dto.getPromotion().getPromotionType().typeCode()), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getDiscountPercentage(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), new CouponCode(dto.getPromotion().getCouponCode()), 
						new ProductUPC(((IPromotionTypeProductDTO) dto.getPromotion().getPromotionType()).getUpcCode()));
			case Constants.promotionTypeBrand:
				return  new PctgDiscount(new PromotionCode(dto.getPromotion().getPromotionCode()), new PromotionTypeCode(dto.getPromotion().getPromotionType().typeCode()), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getDiscountPercentage(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), new CouponCode(dto.getPromotion().getCouponCode()), 
						new BrandCode(((IPromotionTypeBrandDTO) dto.getPromotion().getPromotionType()).getBrandCode()));
			case Constants.promotionTypeCategory:
				return  new PctgDiscount(new PromotionCode(dto.getPromotion().getPromotionCode()), new PromotionTypeCode(dto.getPromotion().getPromotionType().typeCode()), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getDiscountPercentage(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), new CouponCode(dto.getPromotion().getCouponCode()), 
						new CategoryCode(((IPromotionTypeCategoryDTO) dto.getPromotion().getPromotionType()).getCategoryCode()));
									
			default:
				return new PctgDiscount(new PromotionCode(dto.getPromotion().getPromotionCode()), new PromotionTypeCode(dto.getPromotion().getPromotionType().typeCode()), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getDiscountPercentage(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), new CouponCode(dto.getPromotion().getCouponCode()));
		}
		
	}
	
	@Override
	public PromotionDiscEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}

}
