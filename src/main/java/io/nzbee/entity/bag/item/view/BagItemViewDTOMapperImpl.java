package io.nzbee.entity.bag.item.view;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.view.bag.item.BagItemViewOut;

@Component
public class BagItemViewDTOMapperImpl implements IBagItemViewDTOMapper {

	@Override
	public BagItemViewOut DTOToView(BagItemViewDTO dto, IRegularBagItem rbi) {
		BagItemViewOut biv = new BagItemViewOut();
		biv.setBagItemTotal(rbi.getBagItem().getBagItemTotal().amount());
		biv.setItemDesc(dto.getBagItemDesc());
		biv.setBagItemWeight(rbi.getBagItemWeight());
		biv.setItemId(dto.getBagItemId());
		biv.setItemQty(dto.getQuantity());
		biv.setItemUPC(rbi.getBagItem().getProductUPC().toString());
		biv.setMarkdownPrice(rbi.getBagItem().getMarkdownPrice().amount());
		return biv;
	}

	@Override
	public BagItemViewOut DTOToView(BagItemViewDTO dto, IShippingBagItem sbi) {
		BagItemViewOut biv = new BagItemViewOut();
		biv.setBagItemTotal(sbi.getBagItem().getBagItemTotal().amount());
		biv.setBagItemWeight(new BigDecimal(0));
		biv.setItemId(dto.getBagItemId());
		biv.setItemQty(dto.getQuantity());
		biv.setItemUPC(sbi.getBagItem().getProductUPC().toString());
		biv.setMarkdownPrice(sbi.getBagItem().getMarkdownPrice().amount());
		return biv;
	} 

}
