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
import io.nzbee.domain.bag.Bag;
import io.nzbee.entity.bag.item.view.BagItemViewDTO;
import io.nzbee.entity.bag.item.view.IBagItemViewDTOMapper;

@Component
public class BagViewDTOMapperImpl implements IBagViewDTOMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagItemViewDTOMapper bagItemMapper;

	@Override
	public BagView DTOToView(BagViewDTO bDto, Bag bag) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToView()");
 
		// create a new bag domain object
		BagView b = new BagView();

		// map the entity bagItems to the view bagItems
		Set<BagItemViewOut> sbi = bag.getBagItems().stream().map(dbi -> {
			Optional<BagItemViewDTO> oe = bDto.getBagItems().stream().filter(
					vbi -> vbi.getProduct().getProductDto().getProductUPC().equals(dbi.getBagItem().getProductUPC()))
					.findAny();

			if (oe.isPresent()) {
				return bagItemMapper.DTOToView(oe.get(), dbi);
			}
			return null;
		}).collect(Collectors.toSet());

		b.getBagItems().addAll(sbi);

		Optional<BagItemViewDTO> oe = bDto.getBagItems().stream()
				.filter(vbi -> vbi.getProduct().getProductDto().getProductUPC().equals(bag.getShippingItem()))
				.findAny();

		if (oe.isPresent()) {
			b.setShippingItem(bagItemMapper.DTOToView(oe.orElse(null), bag.getShippingItem()));
		}

		return b;
	}

}
