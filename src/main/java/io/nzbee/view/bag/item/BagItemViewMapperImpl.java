package io.nzbee.view.bag.item;

import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;

@Component
public class BagItemViewMapperImpl implements IBagItemViewMapper {

	@Override
	public BagItemViewOut toView(RegularBagItem d) {
			BagItemViewOut bi = new BagItemViewOut();
			//bi.setItemDesc(d);
			bi.setItemUPC(d.getBagItem().getProductUPC().toString());
			bi.setItemQty(d.getBagItem().getQuantity());
			bi.setMarkdownPrice(d.getBagItem().getMarkdownPrice().amount());
			bi.setBagItemTotal(d.getBagItem().getBagItemTotal().amount());
			bi.setBagItemWeight(d.getBagItemWeight());
			return bi;
	}
	
	@Override
	public BagItemViewOut toView(ShippingBagItem d) {
			BagItemViewOut bi = new BagItemViewOut();
			bi.setItemUPC(d.getBagItem().getProductUPC().toString());
			bi.setItemQty(d.getBagItem().getQuantity());
			bi.setMarkdownPrice(d.getBagItem().getMarkdownPrice().amount());
			bi.setBagItemTotal(d.getBagItem().getBagItemTotal().amount());
			return bi;
	}

}
