package io.nzbee.entity.promotion;

import java.util.List;
import java.util.Set;

public interface IPromotionDTOService  {

	List<PromotionDomainDTO> findAll(String locale, Set<String> codes);
	
}
