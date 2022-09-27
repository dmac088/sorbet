package io.nzbee.domain.bag.item.shipping;

import io.nzbee.domain.bag.item.IBagItem;
import io.nzbee.domain.valueObjects.UserName;

public interface IShippingBagItem {

	IBagItem getBagItem();
	
	UserName getUserName();

}
