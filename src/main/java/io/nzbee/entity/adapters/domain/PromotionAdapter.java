package io.nzbee.entity.adapters.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.ports.IPromotionPortService;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.IPromotionMapper;
import io.nzbee.entity.promotion.IPromotionEntityService;
import io.nzbee.entity.promotion.PromotionDomainDTO;
import io.nzbee.entity.promotion.order.IPromotionOrderService;
import io.nzbee.entity.promotion.order.PromotionOrderDTO;
import io.nzbee.exceptions.EntityNotFoundException;

@Component
public class PromotionAdapter implements IPromotionPortService {

	@Autowired
	private IPromotionEntityService promotionService;
	
	@Autowired
	private IPromotionOrderService promotionOrderService;
	
	@Autowired
	private IPromotionMapper promotionMapper;
	
	@Override
	public void save(Promotion domainObject) {
		promotionService.save(promotionMapper.doToEntity(domainObject));
	}

	@Override
	public Promotion findByCode(String locale, String code) {
		PromotionDomainDTO dto = promotionService.findByCode(locale, code)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.promotionNotFound, locale, code));
		return promotionMapper.DTOToDo(dto);
	}
	
	@Override
	public Promotion findPromotionByCode(String locale, String code) {
		PromotionOrderDTO dto = promotionOrderService.findByCode(locale, code)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.promotionNotFound, locale, code));
		return (Promotion) promotionMapper.DTOToDo(dto);
	}

	@Override
	public Promotion findPromotionByCouponCode(String locale, String couponCode) {
		PromotionOrderDTO dto = promotionOrderService.findByCouponCode(locale, couponCode)
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.promotionCouponNotFound, locale, couponCode));
		return (Promotion) promotionMapper.DTOToDo(dto);
	}

	

}
