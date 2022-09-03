package io.nzbee.entity.adapters.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IPromotionPortService;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.IPromotionMapper;
import io.nzbee.entity.promotion.IPromotionDTOService;

@Component
public class PromotionAdapter implements IPromotionPortService {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPromotionDTOService promotionService;

	@Autowired
	private IPromotionMapper promotionMapper;

	@Override
	public Promotion findByCode(String coupon) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameters {}", coupon);

		return null;
	}

	@Override
	public Bag applyAll(Bag bag) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".applyAll with parameter {}",
				bag.getCustomer().getUserName());

		bag.getCoupons().forEach(c -> {
			System.out.println("coupon found: " + c);
		});
	
		//bag promotions
		promotionService.findAll()
		.stream().filter(p -> p.getTypeCode().equals(Constants.promotionTypeBag))
		.forEach(dto -> {
			//we need to check if the bag is eligible for the promotion before executing
			//or this should be done within the execute method of the promotion object itself
			
			System.out.println(dto.getTypeCode());
			System.out.println(dto.getMechanicCode());
			
			IBagPromotion p = (IBagPromotion) promotionMapper.DTOToDo(dto);
			p.execute(bag);
			
			bag.getBagItems().forEach(i -> {
				i.getBagItem().getDiscounts().forEach(d -> {
					System.out.println("promotion item = " + d.getBagItem().getUPC()  +"\n"
									 + "promotion discount amount = " + d.getDiscountAmount());
				});
			});
		});

		//shipping promotions
//		promotionService.findShippingPromotions()
//		.forEach(dto -> {
//			//we need to check if the bag is eligible for the promotion before executing
//			//or this should be done within the execute method of the promotion object itself
//					
//			IBagPromotion p = (IBagPromotion) promotionMapper.DTOToDo(dto);
//			if(bag.hasShippingItem()) {
//				System.out.println(bag.getShippingItem().getUPC());
//				System.out.println(dto.getType());
//				p.execute(bag.getShippingItem());
//				
//				bag.getShippingItem().getBagItem().getDiscounts().forEach(d -> {
//					System.out.println("shipping discount is: " + d.getDiscountAmount());
//				});
//			}
//		});

		return bag;
	}

}
