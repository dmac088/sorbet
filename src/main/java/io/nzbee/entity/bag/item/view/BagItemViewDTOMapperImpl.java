package io.nzbee.entity.bag.item.view;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.view.bag.item.BagItemViewOut;

@Component
public class BagItemViewDTOMapperImpl implements IBagItemViewDTOMapper {

	@Override
	public BagItemViewOut toView(BagItemViewDTO dto) {
		BagItemViewOut biv = new BagItemViewOut();
		biv.setItemDesc(dto.getBagItemDesc());
		biv.setBagItemWeight(dto.getBagItemTotalWeight());
		biv.setItemId(dto.getBagItemId());
		biv.setItemQty(dto.getQuantity());
		biv.setItemUPC(dto.getBagItemUPC());
		biv.setMarkdownPrice(dto.getMarkdownPrice());
		biv.setBagItemDiscountPercentage(dto.getBagItemDiscountPercentage());
		return biv;
	}

	@Override
	public BagItemViewOut toView(BagItemViewDTO dto, BigDecimal totalPostage, Locale locale) {
		BagItemViewOut biv = new BagItemViewOut();
		biv.setItemDesc(dto.getBagItemDesc());
		biv.setBagItemWeight(dto.getBagItemTotalWeight());
		biv.setItemId(dto.getBagItemId());
		biv.setItemQty(dto.getQuantity());
		biv.setItemUPC(dto.getBagItemUPC());
		biv.setMarkdownPrice(totalPostage);
		biv.setBagItemDiscountPercentage(dto.getBagItemDiscountPercentage());
		return biv;
	}


}
