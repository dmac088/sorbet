package io.nzbee.entity.adapters.view;

import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.Globals;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.entity.bag.view.BagViewDTO;
import io.nzbee.entity.bag.view.IBagViewDTOMapper;
import io.nzbee.entity.bag.view.IBagViewDTOService;
import io.nzbee.view.bag.BagView;
import io.nzbee.view.ports.IBagPortService;

public class BagViewAdapterImpl implements IBagPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private Globals globals;
	
	@Autowired
	private IBagViewDTOService bagService;
	
	@Autowired
	private IBagViewDTOMapper bagMapper;
	
	@Override
	@Transactional
	public BagView findByCode(Locale locale, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameter {}, {}, {}", locale, locale.getCurrency().getCurrencyCode(), userName);
		
		//this will also retrieve the items of the bag
		Optional<BagViewDTO> b = bagService.findByCode(locale.getLanguageCode(), locale.getCurrency().getCurrencyCode(), globals.getDefaultProductRootCategoryCode(),userName);
		
		return bagMapper.DTOToView(b.get());
	}

	@Override
	@Transactional
	public BagView toView(Locale locale, String userName) {
		//this function will convert the bag domain object to a view representation
		LOGGER.debug("call " + getClass().getSimpleName() + ".toView with parameter {}, {}, {}", locale, locale.getCurrency().getCurrencyCode(), userName );
		Optional<BagViewDTO> bvDto = bagService.findByCode(locale.getLanguageCode(), locale.getCurrency().getCurrencyCode(), globals.getDefaultProductRootCategoryCode(), userName);
		BagView bv = bagMapper.DTOToView(bvDto.get());
    	return bv;	
	}

}
