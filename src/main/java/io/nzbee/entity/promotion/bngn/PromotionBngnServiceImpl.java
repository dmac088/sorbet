package io.nzbee.entity.promotion.bngn;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionBngnServiceImpl implements IPromotionBngnService {

	@Autowired
	private IPromotionBngnRepository promotionOrderRepository;
	
	@Override
	public Optional<PromotionBngnEntity> findById(Long id) {
		return promotionOrderRepository.findById(id);
	}

	@Override
	public Optional<PromotionBngnEntity> findByCode(String code) {
		//to-do
		return null;
	}
	
	@Override
	public Optional<PromotionBngnDTO> findByCode(String locale, String code) {
		//to-do
		return null;
	}

	@Override
	public Optional<PromotionBngnDTO> findByCouponCode(String locale, String couponCode) {
		//to-do
		return null;
		//return promotionOrderRepository.findByPromotionCouponCode(locale, couponCode);
	}

	@Override
	public void save(PromotionBngnEntity t) {
		promotionOrderRepository.save(t);
	}

	@Override
	public void update(PromotionBngnEntity t) {
		promotionOrderRepository.save(t);
	}

	@Override
	public void delete(PromotionBngnEntity t) {
		promotionOrderRepository.delete(t);
		
	}

}
