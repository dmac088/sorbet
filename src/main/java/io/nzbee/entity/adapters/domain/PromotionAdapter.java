package io.nzbee.entity.adapters.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.IBag;
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
	public IBag applyAll(IBag bag) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".applyAll with parameter {}",
				bag.getUserName());

		bag.getCoupons().forEach(c -> {
			System.out.println("coupon found: " + c);
		});
	
		//bag promotions
		promotionService.findAll()
		.forEach(dto -> {
			
			IBagPromotion p = (IBagPromotion) promotionMapper.DTOToDo(dto);

			p.execute(bag);
			
			bag.getBagItems().forEach(i -> {
				i.getBagItem().getDiscounts().forEach(d -> {
					System.out.println("promotion item = " + d.getBagItem().getUPC().toString()  +"\n"
									 + "promotion discount amount = " + d.getDiscountAmount().amount());
				});
			});
		});

		return bag;
	}

}
