package io.nzbee.entity.adapters.domain;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IPromotionPortService;
import io.nzbee.domain.promotion.DiscountItem;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.IPromotionMapper;
import io.nzbee.entity.promotion.type.IPromotionTypeService;
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

		
		
		bag.getCoupons().forEach(c -> {
			System.out.println("coupon found: " + c);
		});
	
		//bag promotions
		promotionService.findBagPromotions()
		.forEach(dto -> {
			//we need to check if the bag is eligible for the promotion before executing
			//or this should be done within the execute method of the promotion object itself
			
			IBagPromotion p = (IBagPromotion) promotionMapper.DTOToDo(dto);
			p.execute(bag);
			
			bag.getShippingItem().getBagItem().getDiscounts().forEach(d -> {
				System.out.println("promotion item = " + d.getBagItem().getUPC()  +"\n"
								 + "promotion discount amount = " + d.getDiscountAmount());
			});
		});

		//shipping promotions
		promotionService.findShippingPromotions()
		.forEach(dto -> {
			//we need to check if the bag is eligible for the promotion before executing
			//or this should be done within the execute method of the promotion object itself
					
			IBagPromotion p = (IBagPromotion) promotionMapper.DTOToDo(dto);
			if(bag.hasShippingItem()) {
				System.out.println(bag.getShippingItem().getUPC());
				p.execute(bag.getShippingItem());
				
				bag.getShippingItem().getBagItem().getDiscounts().forEach(d -> {
					System.out.println("shipping discount is: " + d.getDiscountAmount());
				});
			}
		});
		
		 
//		//item level promotions
//		bag.getBagItems().forEach(i -> {
//			List<Promotion> lp = 
//					promotionService.findItemPromotion(i.getBagItem().getProductUPC())
//					.stream().map(p -> promotionMapper.DTOToDo(p))
//					.collect(Collectors.toList());
//			
//			lp.forEach(k -> {
//				IBagPromotion rbip = (IBagPromotion) k;
//				rbip.execute(i);
//			});
//		});
//				
//		
//		if(bag.hasShippingItem()) {
//			promotionService.findShippingPromotion()
//			.forEach(dto -> {
//				//we need to check if the bag is eligible for the promotion before executing
//				//or this should be done within the execute method of the promotion object itself
//				IBagPromotion p = (IBagPromotion)promotionMapper.DTOToDo(dto);
//				p.execute(bag.getShippingItem());
//				
//			});
//		}
		
		//shipping promotions
		
//		
//		// for each promotion apply it
//		sp.forEach(p -> {
//
//			if (p.isBagType()) {
//				Promotion pdo = promotionMapper.DTOToDo(p);
//				IBagPromotion blp = (IBagPromotion) pdo;
//				blp.execute(bag);
//				return;
//			}
//
//			if (p.isProductType()) {
//				bag.getBagItems().stream().forEach(i -> {
//					if (i.getBagItem().getProductUPC().equals(p.getPromotionUPC())) {
//						Promotion pdo = promotionMapper.DTOToDo(p);
//						IBagPromotion rbip = (IBagPromotion) pdo;
//						rbip.execute(i);
//						return;
//					}
//				});
//			}
//
//			if (p.isShippingType()) {
//				Promotion pdo = promotionMapper.DTOToDo(p);
//				IBagPromotion sbip = (IBagPromotion) pdo;
//				if (bag.hasShippingItem()) {
//					sbip.execute(bag.getShippingItem());
//				}
//			}
//
//		});

		return bag;
	}

}
