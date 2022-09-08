package io.nzbee.domain.bag.item.regular;

import java.math.BigDecimal;
import java.util.List;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;

public interface IRegularBagItem {

	BagItem getBagItem();

	BigDecimal getBagItemWeight();

	BrandCode getBrandCode();

	List<CategoryCode> getCategoryCodes();

	Boolean isInStock();

}
