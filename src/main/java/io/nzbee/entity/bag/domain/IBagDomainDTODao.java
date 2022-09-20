package io.nzbee.entity.bag.domain;

import java.util.Optional;

public interface IBagDomainDTODao {

	Optional<BagDomainDTO> findByCode(String locale, String currency, String username);
	
}
