package io.nzbee.entity.promotion.bngn;

import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.domain.promotion.bngn.BuyNGetNFree;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.ProductUPC;
import io.nzbee.entity.promotion.type.IPromotionTypeBrandDTO;
import io.nzbee.entity.promotion.type.IPromotionTypeCategoryDTO;
import io.nzbee.entity.promotion.type.IPromotionTypeProductDTO;

@Component(value = "promotionOrderMapper")
public class PromotionBngnMapperImpl implements IPromotionBngnMapper {

	@Override
	public BuyNGetNFree DTOToDo(PromotionBngnDTO dto) {
		return getDomainObject(dto);

	}
	
	private BuyNGetNFree getDomainObject(PromotionBngnDTO dto) {
		 switch(dto.getTypeCode()) {
			case Constants.promotionTypeProduct:
				return new BuyNGetNFree(dto.getPromotion().getPromotionCode(), dto.getPromotion().getPromotionType().typeCode(), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), dto.getPromotion().getCouponCode(), 
						dto.getBuyQuantity(), dto.getDiscountPercentage(), new ProductUPC(((IPromotionTypeProductDTO) dto.getPromotion().getPromotionType()).getUpcCode()));
		
			case Constants.promotionTypeBrand:
				return new BuyNGetNFree(dto.getPromotion().getPromotionCode(), dto.getPromotion().getPromotionType().typeCode(), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), dto.getPromotion().getCouponCode(), 
						dto.getBuyQuantity(), dto.getDiscountPercentage(), new BrandCode(((IPromotionTypeBrandDTO) dto.getPromotion().getPromotionType()).getBrandCode()));
						
			case Constants.promotionTypeCategory:
				return new BuyNGetNFree(dto.getPromotion().getPromotionCode(), dto.getPromotion().getPromotionType().typeCode(), dto.getPromotion().getPromotionStartDate(),
						dto.getPromotion().getPromotionEndDate(), dto.getPromotion().getPromotionIsActive(), dto.getPromotion().getCouponRequired(), dto.getPromotion().getCouponCode(), 
						dto.getBuyQuantity(), dto.getDiscountPercentage(), new CategoryCode(((IPromotionTypeCategoryDTO) dto.getPromotion().getPromotionType()).getCategoryCode()));
					
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
