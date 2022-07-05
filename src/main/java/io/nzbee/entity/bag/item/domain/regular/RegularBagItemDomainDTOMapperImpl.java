package io.nzbee.entity.bag.item.domain.regular;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.entity.IBagEntityService;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.bag.status.BagItemStatus;
import io.nzbee.entity.bag.status.IBagItemStatusService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;

public class RegularBagItemDomainDTOMapperImpl implements IRegularBagItemDomainDTOMapper {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private IBagEntityService bagEntityService;
	
	@Autowired
	private IBagItemStatusService bagItemStatusService;

	@Override
	public RegularBagItem DTOToDo(Bag bag, RegularBagItemWithQuantityDomainDTO dto) {
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
	public RegularBagItem DTOToDo(Bag bag, RegularBagItemDomainDTO dto, int quantity) {
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
	public BagItemEntity doToEntity(RegularBagItem d) {
		Optional<ProductEntity> op = productService.findByCode(d.getBagItem().getProductUPC());
		Optional<BagItemStatus> obis = bagItemStatusService.findByCode(Constants.bagItemStatusCodeNew);
		Optional<BagEntity> ob = bagEntityService.findByCode(d.getBagItem().getBag().getCustomer().getUserName());
		BagItemEntity bi = new BagItemEntity(op.get());
		bi.setBag(ob.get());
		bi.setBagItemStatus(obis.get());
		bi.setQuantity(d.getBagItem().getQuantity());
		return bi;
	}




}
