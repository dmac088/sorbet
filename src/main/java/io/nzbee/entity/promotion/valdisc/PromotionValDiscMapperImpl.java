package io.nzbee.entity.promotion.valdisc;

import org.springframework.stereotype.Component;

import io.nzbee.Constants;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.disc.ValPctgDiscount;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.CouponCode;
import io.nzbee.domain.promotion.value.ProductUPC;
import io.nzbee.domain.promotion.value.PromotionCode;
import io.nzbee.domain.promotion.value.PromotionTypeCode;
import io.nzbee.entity.promotion.type.IPromotionTypeBrandDTO;
import io.nzbee.entity.promotion.type.IPromotionTypeCategoryDTO;
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
						new PromotionCode(dto.getPromotion().getPromotionCode()), 
						new PromotionTypeCode(dto.getPromotion()
									.getPromotionType()
									.typeCode()),
						dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(),
						dto.getDiscountPercentage(), 
						dto.getPromotion().getPromotionIsActive(), 
						dto.getPromotion().getCouponRequired(), 
						new CouponCode(dto.getPromotion().getCouponCode()),
						dto.getValueThreshold(), 
						dto.getCurrency(), 
						dto.getDirection(),
						new ProductUPC(((IPromotionTypeProductDTO) dto.getPromotion().getPromotionType()).getUpcCode()));
			case Constants.promotionTypeBrand:
				return new ValPctgDiscount(
						new PromotionCode(dto.getPromotion().getPromotionCode()), 
						new PromotionTypeCode(dto.getPromotion()
								.getPromotionType()
								.typeCode()),
						dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(),
						dto.getDiscountPercentage(), 
						dto.getPromotion().getPromotionIsActive(), 
						dto.getPromotion().getCouponRequired(), 
						new CouponCode(dto.getPromotion().getCouponCode()),
						dto.getValueThreshold(), 
						dto.getCurrency(), 
						dto.getDirection(),
						new BrandCode(((IPromotionTypeBrandDTO) dto.getPromotion().getPromotionType()).getBrandCode()));
			case Constants.promotionTypeCategory:
				return new ValPctgDiscount(
						new PromotionCode(dto.getPromotion().getPromotionCode()), 
						new PromotionTypeCode(dto.getPromotion()
								.getPromotionType()
								.typeCode()),
						dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(),
						dto.getDiscountPercentage(), 
						dto.getPromotion().getPromotionIsActive(), 
						dto.getPromotion().getCouponRequired(), 
						new CouponCode(dto.getPromotion().getCouponCode()),
						dto.getValueThreshold(), 
						dto.getCurrency(), 
						dto.getDirection(),
						new CategoryCode(((IPromotionTypeCategoryDTO) dto.getPromotion().getPromotionType()).getCategoryCode()));
			default:
				return  new ValPctgDiscount(
						new PromotionCode(dto.getPromotion().getPromotionCode()), 
						new PromotionTypeCode(dto.getPromotion()
								.getPromotionType()
								.typeCode()),
						dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(),
						dto.getDiscountPercentage(), 
						dto.getPromotion().getPromotionIsActive(), 
						dto.getPromotion().getCouponRequired(), 
						new CouponCode(dto.getPromotion().getCouponCode()),
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
