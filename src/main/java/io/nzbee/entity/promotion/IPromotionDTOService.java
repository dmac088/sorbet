package io.nzbee.entity.promotion;

import java.util.Set;

public interface IPromotionDTOService  {

	Set<PromotionDomainDTO> findAll(String locale, Set<String> codes);
	
}
