package io.nzbee.entity.bag.view;

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
import io.nzbee.entity.party.person.PersonDomainDTO;

@Component
public class BagViewDTOMapperImpl implements IBagViewDTOMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagItemViewDTOMapper bagItemMapper;

	@Override
	public BagView DTOToView(String locale, String currency, PersonDomainDTO pDto, BagViewDTO bDto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToView with parameters: {}, {}", locale, currency);

		// create a new bag domain object
		BagView b = new BagView();

		// map the entity bagItems to the view bagItems
		Set<BagItemViewOut> sbi = bDto.getBagItems().stream()
				.filter(bi -> bi.getBagItemTypeCode().equals(Constants.regularBagItemType))
				.map(bi -> bagItemMapper.DTOToView(bi)).collect(Collectors.toSet());

		b.getBagItems().addAll(sbi);

		b.setShippingItem(
				bDto.getBagItems().stream().filter(bi -> bi.getBagItemTypeCode().equals(Constants.shippingBagItemType))
						.map(bi -> bagItemMapper.DTOToView(bi)).findAny().get());

		return b;
	}

	@Override
	public BagView DTOToView(BagViewDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToView()");
		BagView b = new BagView();
		
		// the regular physical products
		Set<BagItemViewOut> sbi = 
			dto.getBagItems().stream()
				.filter(bi -> bi.getBagItemTypeCode().equals(Constants.regularBagItemType))
				.map(bi -> bagItemMapper.DTOToView(bi)).collect(Collectors.toSet());
		b.setBagItems(sbi);

		// the shipping item
		b.setShippingItem(
			dto.getBagItems().stream()
				.filter(bi -> bi.getBagItemTypeCode().equals(Constants.shippingBagItemType))
				.map(bi -> bagItemMapper.DTOToView(bi)).findAny().orElse(null));

		return b;
	}

}
