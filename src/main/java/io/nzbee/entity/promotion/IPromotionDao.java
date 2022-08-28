package io.nzbee.entity.promotion;

import java.util.List;
import java.util.Set;

public interface IPromotionDao {
	
	List<PromotionDomainDTO> findAll(String locale, Set<String> codes);
}
