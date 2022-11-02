package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.Constants;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.shipping.IShippingBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;
import io.nzbee.domain.valueObjects.CategoryCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.entity.IBagEntityService;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.bag.item.type.BagItemTypeEntity;
import io.nzbee.entity.bag.item.type.IBagItemTypeService;
import io.nzbee.entity.bag.status.BagItemStatus;
import io.nzbee.entity.bag.status.IBagItemStatusService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;

public class ShippingBagItemDomainDTOMapperImpl implements IShippingBagItemDomainDTOMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IProductService productService;

	@Autowired
	private IBagEntityService bagEntityService;

	@Autowired
	private IBagItemStatusService bagItemStatusService;

	@Autowired
	private IBagItemTypeService bagItemTypeService;

	@Override
	public ShippingBagItem toDo(IBag bag, ShippingBagItemDomainDTO dto, Long quantity, BigDecimal price,
			Locale locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toDo parameters: {}, {}, {}",
				bag.getCustomer().getUserName(), dto.getProductUPC(), quantity);
		return new ShippingBagItem(new BagItem(bag, new ProductUPC(dto.getProductUPC()), new Quantity(quantity),
				new Money(price, locale.getCurrency(), Constants.defaultMoneyRounding)));
	}

	@Override
	public ShippingBagItem toDo(IBag bag, ShippingBagItemDomainDTO dto, BigDecimal price, Locale locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo parameters: {}, {}, {}",
				bag.getCustomer().getUserName(), dto.getProductUPC());
		return new ShippingBagItem(new BagItem(bag, new ProductUPC(dto.getProductUPC()), new Quantity(new Long(1)),
				new Money(price, locale.getCurrency(), Constants.defaultMoneyRounding)));
	}

	public List<CategoryCode> toCategoryCodes(List<String> categoryCodes) {
		return categoryCodes.stream().map(c -> new CategoryCode(c)).collect(Collectors.toList());
	}

	@Override
	public BagItemEntity toEntity(IShippingBagItem d) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toEntity doToEntity: {}", d.getUserName());
		Optional<ProductEntity> op = productService.findByCode(d.getBagItem().getProductUPC().toString());
		Optional<BagItemStatus> obis = bagItemStatusService.findByCode(Constants.bagItemStatusCodeNew);
		Optional<BagEntity> ob = bagEntityService.findByCode(d.getUserName().toString());
		Optional<BagItemTypeEntity> obit = bagItemTypeService.findByCode(Constants.shippingBagItemType);
		BagItemEntity bi = new BagItemEntity(op.get());
		bi.setBag(ob.get());
		bi.setBagItemStatus(obis.get());
		bi.setBagItemType(obit.get());
		bi.setQuantity(d.getBagItem().getQuantity().amount());
		bi.setBagTotalWeight(new BigDecimal(0));
		return bi;
	}


}
