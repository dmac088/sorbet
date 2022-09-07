package io.nzbee.view.bag;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;

@Component
public class BagViewMapperImpl implements IBagViewMapper {


	@Override
	public BagView toView(Bag d) {
		BagView dto = new BagView();
		dto.setTotalItems(d.getTotalItems());
		dto.setTotalQuantity(d.getTotalQuantity());
		dto.setGrandTotalAmount(d.getGrandTotalAmount().amount());
		dto.setSubTotalAmount(d.getSubTotalAmount().amount());
		dto.setTotalWeight(d.getTotalWeight());
		dto.setCoupons(d.getCoupons().stream().map(c -> c.toString()).collect(Collectors.toList()));
		return dto;
	}

}
