package io.nzbee.view.bag;

import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;

@Component
public class BagViewMapperImpl implements IBagViewMapper {


	@Override
	public BagView toView(Bag d) {
		BagView dto = new BagView();
		dto.setTotalItems(d.getTotalItems());
		dto.setTotalQuantity(d.getTotalQuantity());
		dto.setTotalAmount(d.getTotalAmount());
		dto.setTotalWeight(d.getTotalWeight());
		return dto;
	}

}
