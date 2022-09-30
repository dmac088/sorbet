package io.nzbee.entity.bag.item.view;

import org.springframework.stereotype.Component;
import io.nzbee.view.bag.item.BagItemViewOut;

@Component
public class BagItemViewDTOMapperImpl implements IBagItemViewDTOMapper {

	@Override
	public BagItemViewOut toView(BagItemViewDTO dto) {
		BagItemViewOut biv = new BagItemViewOut();
		biv.setItemDesc(dto.getBagItemDesc());
		biv.setBagItemWeight(dto.getBagTotalWeight());
		biv.setItemId(dto.getBagItemId());
		biv.setItemQty(dto.getQuantity());
		biv.setItemUPC(dto.getBagItemUPC());
		biv.setMarkdownPrice(dto.getMarkdownPrice());
		biv.setBagItemDiscountPercentage(dto.getBagItemDiscountPercentage());
		return biv;
	}


}
