package io.nzbee.entity.adapters.domain;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IPromotionPortService;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.IPromotionMapper;
import io.nzbee.entity.promotion.IPromotionDTOService;

@Component
public class PromotionAdapter implements IPromotionPortService {

	@Autowired
	private IPromotionDTOService promotionService;
	

	@Autowired
	private IPromotionMapper promotionMapper;

	@Override
	public Bag applyAll(String locale, Bag bag) {
		
		Set<String> codes = bag.getBagItems().stream()
		.map(i -> i.getBagItem().getProductUPC())
		.collect(Collectors.toSet());
		
		//get all the promotions for the list of products in the bag
		Set<Promotion> s = promotionService.findAll(locale, codes)
		.stream().map(p -> promotionMapper.DTOToDo(p))
		.collect(Collectors.toSet());
		
		//compute all the promotions
		//create discounts line items for each computation
		//add the discounts to the bag
		s.forEach(i -> {
			i.compute();
			System.out.println(i);
		});
		
		
		
		
		//return the bag
		return bag;
		
	}

	

}
