package io.nzbee.entity.adapters.domain;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IPromotionPortService;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.IRegularBagItemPromotion;
import io.nzbee.domain.promotion.IShippingBagItemPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.IPromotionMapper;
import io.nzbee.entity.promotion.PromotionDomainDTO;
import io.nzbee.entity.promotion.type.IPromotionTypeService;
import io.nzbee.entity.promotion.type.PromotionTypeEntity;
import io.nzbee.entity.promotion.IPromotionDTOService;

@Component
public class PromotionAdapter implements IPromotionPortService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPromotionTypeService promotionTypeService;

	@Autowired
	private IPromotionDTOService promotionService;

	@Autowired
	private IPromotionMapper promotionMapper;

	@Override
	public Promotion findByCode(String coupon) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters {}", coupon);

		promotionTypeService.findByTriggerCode(coupon);
		return null;
	}

	@Override
	public Bag applyAll(Bag bag) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".applyAll with parameter {}",
				bag.getCustomer().getUserName());

		bag.getCoupons().stream().forEach(c -> {
			LOGGER.debug("counpon is present: " + c);
			// we want to fist find the promotion type by coupon code
			Optional<PromotionTypeEntity> opt = promotionTypeService.findByTriggerCode(c);

			if (opt.isPresent()) {
				LOGGER.debug("promotion type found: " + opt.get().getPromotionTypeDesc());

				//bag level logic
				//bag level discounts require a coupon at this stage
				//these are for arbitrary discounts like 20% of for a 2 week period
				if (opt.get().getPromotionTypeCode().equals(Constants.promotionTypeBag)) {
					Promotion p = promotionMapper.DTOToDo(promotionService.find(c).get());
					IBagPromotion blp = (IBagPromotion) p;
					blp.execute(bag);
					return;
				}

				//item level logic
				//promotions include category and brand level promotions
				//item level promotions require a coupon at this stage 
				if (opt.get().getPromotionTypeCode().equals(Constants.promotionTypeProduct)) {
					bag.getBagItems().stream().forEach(i -> {
						Optional<PromotionDomainDTO> opdto = promotionService.find(i.getBagItem().getProductUPC(), c);
						if (opdto.isPresent()) {
							Promotion p = promotionMapper.DTOToDo(opdto.get());
							IBagPromotion rbip = (IBagPromotion) p;
							rbip.execute(i);
							return;
						}
					});
				}
			}
		});
		
		//shipping logic
		//we don't need a coupon to get the discounted shipping, it's automatic
		
		Optional<PromotionDomainDTO> opdto = promotionService.findAllByType(Constants.promotionTypeShipping);
		if (opdto.isPresent()) {
			Promotion p = promotionMapper.DTOToDo(opdto.get());
			IBagPromotion sbip = (IBagPromotion) p;
			sbip.execute(bag.getShippingItem());
		}
			
		
		
		return bag;
	}

}
