package io.nzbee.entity.adapters.domain;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IPromotionPortService;
import io.nzbee.domain.promotion.IBagPromotion;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.IPromotionMapper;
import io.nzbee.entity.promotion.PromotionDomainDTO;
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

		List<String> ss = bag.getBagItems().stream().map(i -> i.getBagItem().getProductUPC())
				.collect(Collectors.toList());
		List<PromotionDomainDTO> sp = promotionService.findAll(bag.getCoupons(), ss);

		// for each promotion apply it
		sp.forEach(p -> {

			if (p.isBagType()) {
				Promotion pdo = promotionMapper.DTOToDo(p);
				IBagPromotion blp = (IBagPromotion) pdo;
				blp.execute(bag);
				return;
			}

			if (p.isProductType()) {
				bag.getBagItems().stream().forEach(i -> {
					if (i.getBagItem().getProductUPC().equals(p.getPromotionUPC())) {
						Promotion pdo = promotionMapper.DTOToDo(p);
						IBagPromotion rbip = (IBagPromotion) pdo;
						rbip.execute(i);
						return;
					}
				});
			}

			if (p.isShippingType()) {
				Promotion pdo = promotionMapper.DTOToDo(p);
				IBagPromotion sbip = (IBagPromotion) pdo;
				if (bag.hasShippingItem()) {
					sbip.execute(bag.getShippingItem());
				}
			}

		});

		return bag;
	}

}
