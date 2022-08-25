package io.nzbee.entity.promotion.order;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionOrderServiceImpl implements IPromotionOrderService {

	@Autowired
	private IPromotionOrderRepository promotionOrderRepository;
	
	@Override
	public Optional<PromotionOrderEntity> findById(Long id) {
		return promotionOrderRepository.findById(id);
	}

	@Override
	public Optional<PromotionOrderEntity> findByCode(String code) {
		//to-do
		return null;
	}
	
	@Override
	public Optional<PromotionOrderDTO> findByCode(String locale, String code) {
		//to-do
		return null;
	}

	@Override
	public Optional<PromotionOrderDTO> findByCouponCode(String locale, String couponCode) {
		//to-do
		return null;
		//return promotionOrderRepository.findByPromotionCouponCode(locale, couponCode);
	}

	@Override
	public void save(PromotionOrderEntity t) {
		promotionOrderRepository.save(t);
	}

	@Override
	public void update(PromotionOrderEntity t) {
		promotionOrderRepository.save(t);
	}

	@Override
	public void delete(PromotionOrderEntity t) {
		promotionOrderRepository.delete(t);
		
	}

}
