package io.nzbee.entity.bag.item.domain;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.entity.IBagEntityService;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.bag.item.type.BagItemTypeEntity;
import io.nzbee.entity.bag.item.type.IBagItemTypeService;
import io.nzbee.entity.bag.status.BagItemStatus;
import io.nzbee.entity.bag.status.IBagItemStatusService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;

public class BagItemDomainDTOMapperImpl implements IBagItemDomainDTOMapper {

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
	public RegularBagItem DTOToDo(Bag bag, BagItemWithQuantityDomainDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo parameters: {}, {}", bag.getCustomer().getUserName(), dto.getProductUPC());
		return new RegularBagItem(
						new BagItem(bag, 
						dto.getProductUPC(), 
						dto.getQuantity(), 
						dto.getMarkdownPrice()),
						dto.getWeight(),
						dto.isInStock()
					);
	}
	
	@Override
	public ShippingBagItem ShippingDTOToShippingDo(Bag bag, BagItemWithQuantityDomainDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo parameters: {}, {}", bag.getCustomer().getUserName(), dto.getProductUPC());
		return new ShippingBagItem(
						new BagItem(bag, 
						dto.getProductUPC(), 
						1, 
						dto.getMarkdownPrice())
					);
	}
	
	
	@Override
	public RegularBagItem DTOToDo(Bag bag, BagItemDomainDTO dto, int quantity) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo parameters: {}, {}, {}", bag.getCustomer().getUserName(), dto.getProductUPC());
		return new RegularBagItem(
				new BagItem(bag, 
				dto.getProductUPC(), 
				quantity, 
				dto.getMarkdownPrice()),
				dto.getWeight(),
				dto.isInStock()
			);
	}
	
	@Override
	public ShippingBagItem DTOToDo(Bag bag, BagItemDomainDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo parameters: {}, {}, {}", bag.getCustomer().getUserName(), dto.getProductUPC());
		return new ShippingBagItem(
				new BagItem(bag, 
				dto.getProductUPC(), 
				1, 
				dto.getMarkdownPrice())
			);
	}


	@Override
	public BagItemEntity doToEntity(RegularBagItem d) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo doToEntity: {}", d.getBagItem().getProductUPC());
		Optional<ProductEntity> op = productService.findByCode(d.getBagItem().getProductUPC());
		Optional<BagItemStatus> obis = bagItemStatusService.findByCode(Constants.bagItemStatusCodeNew);
		Optional<BagEntity> ob = bagEntityService.findByCode(d.getBagItem().getBag().getCustomer().getUserName());
		Optional<BagItemTypeEntity> obit = bagItemTypeService.findByCode(Constants.regularBagItemType);
		BagItemEntity bi = new BagItemEntity(op.get());
		bi.setBag(ob.get());
		bi.setBagItemStatus(obis.get());
		bi.setBagItemType(obit.get());
		bi.setQuantity(d.getBagItem().getQuantity());
		return bi;
	}


	@Override
	public BagItemEntity doToEntity(ShippingBagItem d) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo doToEntity: {}", d.getBagItem().getProductUPC());
		Optional<ProductEntity> op = productService.findByCode(d.getBagItem().getProductUPC());
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