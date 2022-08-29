package io.nzbee.entity.bag.domain;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.bag.item.shipping.ShippingBagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.entity.IBagEntityService;
import io.nzbee.entity.bag.item.domain.IRegularBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.IShippingBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.ShippingBagItemDomainDTO;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.bag.item.type.IBagItemTypeService;
import io.nzbee.entity.bag.status.IBagItemStatusService;
import io.nzbee.entity.party.person.ICustomerDomainMapper;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.product.IProductService;
//import io.nzbee.entity.promotion.IPromotionEntityService;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.entity.promotion.order.PromotionOrderEntity;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.entity.party.person.PersonDomainDTO;

@Component
public class BagDomainDTOMapperImpl implements IBagDomainDTOMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagEntityService bagService;

	@Autowired
	private ICustomerDomainMapper personMapper;

	@Autowired
	private IRegularBagItemDomainDTOMapper regularBagItemMapper;
	
	@Autowired
	private IShippingBagItemDomainDTOMapper shippingItemMapper;
	
	@Autowired
	private IBagItemStatusService bagItemStatusService;
	
	@Autowired
	private IBagItemTypeService bagItemTypeService;

	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IProductService productService;


	@Override
	public Bag DTOToDo(BagDomainDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo()");
		Bag b = new Bag(personMapper.DTOToDo(dto.getCustomer()));

		// map the entity bagItems to the domain bagItems
		Set<RegularBagItem> sbi = dto.getRegularBagItems().stream()
				.map(bi -> regularBagItemMapper.DTOToDo(b, bi))
				.collect(Collectors.toSet());
		
		Optional<ShippingBagItemDomainDTO> ossbi = Optional.ofNullable(dto.getShippingBagItem());
		
		// use the add item method on the domain object to
		// ensure business rules are fired against each added bagItem
		sbi.forEach(bi -> {
			b.addItem(bi, bi.getBagItem().getQuantity());
		});
		
		if(ossbi.isPresent()) {
			ShippingBagItem ssbi = shippingItemMapper.DTOToDo(b, ossbi.get());
			b.addShippingItem(ssbi);
		}
		
		return b;
	}

	@Override
	public Bag DTOToDo(String locale, String currency, PersonDomainDTO pDto, BagDomainDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo() with parameters: {}, {}", locale, currency);
		
		// we need a customer to instantiate a new bag
		Customer c = personMapper.DTOToDo(pDto);

		// create a new bag domain object
		Bag b = new Bag(c);

		// map the entity bagItems to the domain bagItems
		Set<RegularBagItem> sbi = dto.getRegularBagItems().stream()
				.map(bi -> regularBagItemMapper.DTOToDo(b, bi))
				.collect(Collectors.toSet());

		Optional<ShippingBagItemDomainDTO> ossbi = Optional.ofNullable(dto.getShippingBagItem());
		
		// use the add item method on the domain object to
		// ensure business rules are fired against each added bagItem
		sbi.forEach(bi -> {
			b.addItem(bi, bi.getBagItem().getQuantity());
		});
		
		if(ossbi.isPresent()) {
			ShippingBagItem ssbi = shippingItemMapper.DTOToDo(b, ossbi.get());
			b.addShippingItem(ssbi);
		}

		return b;
	}

	@Override
	public BagEntity doToEntity(Bag d) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".doToEntity()");
		
		// get the bag, status, and customer from the database
		Optional<BagEntity> obe = bagService.findByCode(d.getCustomer().getUserName());
		Optional<PersonEntity> op = personService.findByUsernameAndRole(d.getCustomer().getUserName(),
				Constants.partyRoleCustomer);
		Optional<PromotionEntity> opr = Optional.ofNullable(null);

		BagEntity nbe = new BagEntity();
		nbe.setBagCreatedDateTime(LocalDateTime.now());
		nbe.setBagUpdatedDateTime(LocalDateTime.now());

		// use the existing bag if it exists otherwise use newly created
		BagEntity b = obe.orElse(nbe);

		b.setBagUpdatedDateTime(LocalDateTime.now());

		// map the domain bagItems to entity bagItems
		d.getBagItems().stream().forEach(bi -> {
			Optional<BagItemEntity> obi = b.getBagItems().stream()
					.filter(i -> i.getProduct().getProductUPC().equals(bi.getBagItem().getProductUPC())).findAny();

			if (obi.isPresent()) {
				BagItemEntity bie = obi.get();
				bie.setQuantity(bi.getBagItem().getQuantity());
			} else {
				BagItemEntity i = new BagItemEntity();
				i.setBag(b);
				i.setBagItemStatus(bagItemStatusService.findByCode(Constants.bagItemStatusCodeNew)
						.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.bagItemStatusNotFound,Constants.localeENGB,Constants.bagItemStatusCodeNew)));
				i.setBagItemType(bagItemTypeService.findByCode(Constants.regularBagItemType)
						.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.bagItemTypesNotFound,Constants.localeENGB,Constants.regularBagItemType)));
				i.setProduct(productService.findByCode(bi.getBagItem().getProductUPC())
						.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound,Constants.localeENGB,bi.getBagItem().getProductUPC())));
				i.setQuantity(bi.getBagItem().getQuantity());
				b.addItem(i);
			}
		});
		
		//remove any shipping item if it exists 
		if(d.hasShippingItem() ) {
		
			//find the shipping item among the existing bag item entities 
			Optional<BagItemEntity> oe = b.getBagItems().stream().filter(i -> i.getBagItemType().getBagItemTypeCode().equals(Constants.shippingBagItemType)).findAny();

			if(oe.isPresent()) {
				LOGGER.debug("Shipping item with id: {} was found!", d.getShippingItem().getBagItem().getProductUPC());
				b.getBagItems().remove(oe.get());
			}
		}
				
		//add the new shipping item to the bag
		if(d.hasShippingItem()) {
			BagItemEntity sie = shippingItemMapper.doToEntity(d.getShippingItem());
			LOGGER.debug("Adding shipping item with id {}", sie.getProduct().getUPC());
			b.getBagItems().add(sie);
		}
		
		// add promotion to the bag if the promotion exists
		if (opr.isPresent()) {
			b.setPromotion((PromotionOrderEntity) opr.get().getPromotionOrder());
		}

		// set the customer of the bag
		b.setParty(op.get().getPersonParty());

		// persist the bags
		return b;
	}

}
