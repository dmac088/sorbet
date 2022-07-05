package io.nzbee.view.bag.item;

import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.view.IViewObjectMapper;

public interface IBagItemViewMapper extends IViewObjectMapper<RegularBagItem, BagItemViewOut> {

	BagItemViewOut toView(RegularBagItem d);


}
