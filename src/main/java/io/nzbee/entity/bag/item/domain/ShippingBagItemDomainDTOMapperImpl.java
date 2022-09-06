package io.nzbee.entity.bag.item.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;
import io.nzbee.domain.promotion.value.BrandCode;
import io.nzbee.domain.promotion.value.CategoryCode;
import io.nzbee.domain.promotion.value.ProductUPC;
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
	public ShippingBagItem DTOToDo(Bag bag, ShippingBagItemDomainDTO dto, Long quantity) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo parameters: {}, {}, {}", bag.getCustomer().getUserName(), dto.getProductUPC(), quantity);
		return new ShippingBagItem(
				new BagItem(bag, 
				new ProductUPC(dto.getProductUPC()), 
				quantity, 
				dto.getMarkdownPrice(),
				new BrandCode(dto.getBrandCode()),
				this.toCategoryCodes(dto.getCategoryCodes()))
			);
	}
	
	
	@Override
	public ShippingBagItem DTOToDo(Bag bag, ShippingBagItemDomainDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo parameters: {}, {}, {}", bag.getCustomer().getUserName(), dto.getProductUPC());
		return new ShippingBagItem(
				new BagItem(bag, 
				new ProductUPC(dto.getProductUPC()),
				new Long(1), 
				dto.getMarkdownPrice(),
				new BrandCode(dto.getBrandCode()),
				this.toCategoryCodes(dto.getCategoryCodes()))
			);
	}
	
	public List<CategoryCode> toCategoryCodes(List<String> categoryCodes) {
		return categoryCodes.stream().map(c -> new CategoryCode(c)).collect(Collectors.toList());
	}

	@Override
	public BagItemEntity doToEntity(ShippingBagItem d) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo doToEntity: {}", d.getBagItem().getProductUPC());
		Optional<ProductEntity> op = productService.findByCode(d.getBagItem().getProductUPC().toString());
		Optional<BagItemStatus> obis = bagItemStatusService.findByCode(Constants.bagItemStatusCodeNew);
		Optional<BagEntity> ob = bagEntityService.findByCode(d.getBagItem().getBag().getCustomer().getUserName());
		Optional<BagItemTypeEntity> obit = bagItemTypeService.findByCode(Constants.shippingBagItemType);
		BagItemEntity bi = new BagItemEntity(op.get());
		bi.setBag(ob.get());
		bi.setBagItemStatus(obis.get());
		bi.setBagItemType(obit.get());
		bi.setQuantity(d.getBagItem().getQuantity());
		return bi;
	}


}
