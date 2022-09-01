package io.nzbee.entity.promotion;

import java.util.List;

public interface IPromotionDao {
	
	List<PromotionDomainDTO> findAll(List<String> coupons, List<String> items);
	
}

