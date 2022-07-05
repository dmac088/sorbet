package io.nzbee.entity.bag.view;

import io.nzbee.entity.party.person.PersonDomainDTO;
import io.nzbee.view.bag.BagView;

public interface IBagViewDTOMapper {

	BagView DTOToView(String locale, String currency, PersonDomainDTO pDto, BagViewDTO bDto);

	BagView DTOToView(BagViewDTO dto);
	
}
