package io.nzbee.entity.bag.view;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.view.bag.BagView;
import io.nzbee.view.bag.item.BagItemViewOut;
import io.nzbee.entity.bag.item.view.IBagItemViewDTOMapper;
import io.nzbee.entity.party.person.PersonDomainDTO;

@Component
public class BagViewDTOMapperImpl implements IBagViewDTOMapper {
	
	@Autowired
	private IBagItemViewDTOMapper bagItemMapper;
	
	@Override
	public BagView DTOToView(String locale, String currency, PersonDomainDTO pDto, BagViewDTO bDto) {
		
		//create a new bag domain object
		BagView b = new BagView();
		
		//map the entity bagItems to the domain bagItems
		Set<BagItemViewOut> sbi = 	 bDto.getBagItems().stream()
									.map(bi -> bagItemMapper.DTOToView(bDto,bi))
									.collect(Collectors.toSet());
		
		 
		b.getBagItems().addAll(sbi);
		
		//update the total on the bag
		//b.setTotalWeight(new BigDecimal(sbi.stream().mapToDouble(bi -> bi.getBagItemWeight().doubleValue() * bi.getItemQty()).sum()));
		
		return b;
	}

	@Override
	public BagView DTOToView(BagViewDTO dto) {
		BagView b = new BagView();
		b.setBagItems(dto.getBagItems().stream().map(bi -> bagItemMapper.DTOToView(dto, bi)).collect(Collectors.toSet()));
		return b;
	}	
	
}
