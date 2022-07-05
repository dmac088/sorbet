package io.nzbee.view.bag.item;

import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.item.regular.RegularBagItem;

@Component
public class BagItemViewMapperImpl implements IBagItemViewMapper {

	@Override
	public BagItemViewOut toView(RegularBagItem d) {
			BagItemViewOut bi = new BagItemViewOut();
			bi.setItemUPC(d.getBagItem().getProductUPC());
			bi.setItemQty(d.getBagItem().getQuantity());
			bi.setMarkdownPrice(d.getBagItem().getMarkdownPrice());
			bi.setBagItemTotal(d.getBagItem().getBagItemTotal());
			bi.setBagItemWeight(d.getBagItemWeight());
			return bi;
	}

}
