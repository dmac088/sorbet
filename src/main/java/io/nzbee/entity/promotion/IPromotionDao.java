package io.nzbee.entity.promotion;

import java.util.Optional;
import java.util.Set;


public interface IPromotionDao {
	
	Optional<PromotionDomainDTO> findAll(String locale, Set<String> codes);
}
