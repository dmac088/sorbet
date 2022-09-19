package io.nzbee.entity.bag.item.domain.promotion;

import java.util.Currency;
import java.util.stream.Collectors;
import io.nzbee.Constants;
import io.nzbee.domain.promotion.item.PromotionItem;
import io.nzbee.domain.valueObjects.BagID;
import io.nzbee.domain.valueObjects.BrandCode;
import io.nzbee.domain.valueObjects.CategoryCode;
import io.nzbee.domain.valueObjects.CustomerID;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;

public class PromotionBagItemDomainDTOMapperImpl implements IPromotionBagItemDomainDTOMapper {

	@Override
	public PromotionItem toDo(PromotionBagItemDomainDTO dto) {
		Currency curr = Currency.getInstance(dto.getCurrency());
		
		return new PromotionItem(
				new CustomerID(),
				new BagID(),
				new Long(0),
				new ProductUPC(dto.getProductUPC()),
				dto.getQuantity(),
				new BrandCode(dto.getBrandCode()),
				dto.getCategoryCodes().stream().map(c -> new CategoryCode(c)).collect(Collectors.toList()),
				curr,
				new Money(dto.getMarkdownPrice(), curr, Constants.defaultMoneyRounding),
				new Money(dto.getBaseTotal(), curr, Constants.defaultMoneyRounding));
	}

}
