package io.nzbee.entity.bag.item.domain;

import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.Constants;
import io.nzbee.domain.bag.IBag;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.valueObjects.CategoryCode;
import io.nzbee.domain.valueObjects.Money;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;
import io.nzbee.domain.valueObjects.Weight;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.entity.IBagEntityService;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.bag.item.type.BagItemTypeEntity;
import io.nzbee.entity.bag.item.type.IBagItemTypeService;
import io.nzbee.entity.bag.status.BagItemStatus;
import io.nzbee.entity.bag.status.IBagItemStatusService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;

public class RegularBagItemDomainDTOMapperImpl implements IRegularBagItemDomainDTOMapper {

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
	public RegularBagItem toDo(IBag bag, RegularBagItemDomainDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toDo parameters: {}, {}", bag.getCustomer().getUserName(), dto.getProductUPC());
		return new RegularBagItem(
						new BagItem(bag, 
						new ProductUPC(dto.getProductUPC()), 
						new Quantity(dto.getQuantity()), 
						new Money(dto.getMarkdownPrice(), Currency.getInstance(dto.getCurrency()), Constants.defaultMoneyRounding)),
						new Weight(dto.getWeight()),
						dto.isInStock()
					);
	}
	
	
	public List<CategoryCode> toCategoryCodes(List<String> categoryCodes) {
		return categoryCodes.stream().map(c -> new CategoryCode(c)).collect(Collectors.toList());
	}

	
	@Override
	public RegularBagItem toDo(IBag bag, RegularBagItemDomainDTO dto, Long quantity) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toDo parameters: {}, {}, {}", bag.getCustomer().getUserName(), dto.getProductUPC());
		return new RegularBagItem(
				new BagItem(bag, 
				new ProductUPC(dto.getProductUPC()), 
				new Quantity(quantity), 
				new Money(dto.getMarkdownPrice(), Currency.getInstance(dto.getCurrency()), Constants.defaultMoneyRounding)),
				new Weight(dto.getWeight()),
				dto.isInStock()
			);
	}


	@Override
	public BagItemEntity toEntity(IRegularBagItem d) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toDo doToEntity: {}", d.getBagItem().getProductUPC());
		Optional<ProductEntity> op = productService.findByCode(d.getBagItem().getProductUPC().toString());
		Optional<BagItemStatus> obis = bagItemStatusService.findByCode(Constants.bagItemStatusCodeNew);
		Optional<BagEntity> ob = bagEntityService.findByCode(d.getBagItem().getBag().getCustomer().getUserName().toString());
		Optional<BagItemTypeEntity> obit = bagItemTypeService.findByCode(Constants.regularBagItemType);
		BagItemEntity bi = new BagItemEntity(op.get());
		bi.setBag(ob.get());
		bi.setBagItemStatus(obis.get());
		bi.setBagItemType(obit.get());
		bi.setQuantity(d.getBagItem().getQuantity().amount());
		bi.setBagTotalWeight(d.getBagItemWeight().amount());
		return bi;
	}

}
