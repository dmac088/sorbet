package io.nzbee.entity.bag.item.domain.promotion;

import java.util.Currency;
import java.util.stream.Collectors;
import io.nzbee.Constants;
import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.promotion.bag.item.PromotionBagItem;
import io.nzbee.domain.valueObjects.BagID;
import io.nzbee.domain.valueObjects.BagItemStatus;
import io.nzbee.domain.valueObjects.BrandCode;
import io.nzbee.domain.valueObjects.CategoryCode;
import io.nzbee.domain.valueObjects.CustomerID;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;
import io.nzbee.entity.bag.item.entity.BagItemEntity;

public class PromotionBagItemDomainDTOMapperImpl implements IPromotionBagItemDomainDTOMapper {

	@Override
	public PromotionBagItem toDo(IPromotionBag b, PromotionBagItemDomainDTO dto) {
		Currency curr = Currency.getInstance(dto.getCurrency());
		return new PromotionBagItem(
				b,
				new CustomerID(),
				new BagID(),
				new ProductUPC(dto.getProductUPC()),
				new Quantity(dto.getQuantity()),
				new BrandCode(dto.getBrandCode()),
				dto.getCategoryCodes().stream().map(c -> new CategoryCode(c)).collect(Collectors.toList()),
				curr,
				new Money(dto.getMarkdownPrice(), curr, Constants.defaultMoneyRounding),
				new BagItemStatus(dto.getBagItemStatus()));
	}

	@Override
	public BagItemEntity toEntity(PromotionBagItem d) {
		// TODO Auto-generated method stub
		return null;
	}

}
