package io.nzbee.domain.bag.item.regular;

import java.util.List;
import io.nzbee.domain.bag.item.IBagItem;
import io.nzbee.domain.valueObjects.BrandCode;
import io.nzbee.domain.valueObjects.CategoryCode;
import io.nzbee.domain.valueObjects.Weight;

public interface IRegularBagItem {

	IBagItem getBagItem();

	Weight getBagItemWeight();

	BrandCode getBrandCode();

	List<CategoryCode> getCategoryCodes();

	Boolean isInStock();

}
