package io.nzbee.entity.bag.view;

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
import io.nzbee.entity.bag.item.view.IBagItemViewDTOMapper;

@Component
public class BagViewDTOMapperImpl implements IBagViewDTOMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagItemViewDTOMapper bagItemMapper;

	@Override
	public BagView DTOToView(BagViewDTO bDto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToView()");

		// create a new bag view object
		BagView b = new BagView();
 
		// map the entity bagItems to the view bagItems
		Set<BagItemViewOut> sbi =  bDto.getBagItems().stream()
				.filter(bi -> bi.getBagItemTypeCode().equals(Constants.regularBagItemType))
				.map(bi -> bagItemMapper.DTOToView(bi)).collect(Collectors.toSet());
		b.setBagItems(sbi);
		
		Optional<BagItemViewOut> ssbi = bDto.getBagItems().stream()
				.filter(bi -> bi.getBagItemTypeCode().equals(Constants.shippingBagItemType))
				.map(bi -> bagItemMapper.DTOToView(bi)).findAny();
		
		if(ssbi.isPresent() ) {
			b.setShippingItem(ssbi.get());
		}
	
		return b;
	}

}
