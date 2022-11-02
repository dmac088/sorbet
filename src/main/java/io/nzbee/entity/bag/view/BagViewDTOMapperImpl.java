package io.nzbee.entity.bag.view;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.view.bag.BagView;
import io.nzbee.view.bag.item.BagItemViewOut;
import io.nzbee.Constants;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.entity.bag.item.view.IBagItemViewDTOMapper;

@Component
public class BagViewDTOMapperImpl implements IBagViewDTOMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagItemViewDTOMapper bagItemMapper;

	@Override
	public BagView toView(BagViewDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toView()");

		// create a new bag view object
		BagView b = new BagView();
 
		// map the entity bagItems to the view bagItems
		Set<BagItemViewOut> sbi =  dto.getBagItems().stream()
				.filter(bi -> bi.getBagItemTypeCode().equals(Constants.regularBagItemType))
				.map(bi -> bagItemMapper.toView(bi)).collect(Collectors.toSet());
		b.setBagItems(sbi);
		
		Optional<BagItemViewOut> ssbi = dto.getBagItems().stream()
				.filter(bi -> bi.getBagItemTypeCode().equals(Constants.shippingBagItemType))
				.map(bi -> bagItemMapper.toView(bi)).findAny();
		
		if(ssbi.isPresent() ) {
			b.setShippingItem(ssbi.get());
		}
	
		return b;
	}

	@Override
	public BagView toView(BagViewDTO dto, BigDecimal totalPostage, Locale locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toView()");

		// create a new bag view object
		BagView b = new BagView();
 
		// map the entity bagItems to the view bagItems
		Set<BagItemViewOut> sbi =  dto.getBagItems().stream()
				.filter(bi -> bi.getBagItemTypeCode().equals(Constants.regularBagItemType))
				.map(bi -> bagItemMapper.toView(bi)).collect(Collectors.toSet());
		b.setBagItems(sbi);
		
		Optional<BagItemViewOut> ssbi = dto.getBagItems().stream()
				.filter(bi -> bi.getBagItemTypeCode().equals(Constants.shippingBagItemType))
				.map(bi -> bagItemMapper.toView(bi, totalPostage, locale)).findAny();
		
		if(ssbi.isPresent() ) {
			b.setShippingItem(ssbi.get());
		}
	
		return b;
	}

}
