package io.nzbee.entity.bag.view;

import java.util.Optional;

public interface IBagViewDTODao {

	Optional<BagViewDTO> findByCode(String locale, String currency, String rootCategory, String userName);
	
}
