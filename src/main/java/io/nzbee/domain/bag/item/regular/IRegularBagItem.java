package io.nzbee.domain.bag.item.regular;

import io.nzbee.domain.bag.item.IBagItem;
import io.nzbee.domain.valueObjects.Weight;

public interface IRegularBagItem {

	IBagItem getBagItem();

	Weight getBagItemWeight();

	Boolean isInStock();

}
