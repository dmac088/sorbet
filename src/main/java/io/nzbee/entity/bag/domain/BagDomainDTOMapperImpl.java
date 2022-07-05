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
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.entity.IBagEntityService;
import io.nzbee.entity.bag.item.domain.regular.IRegularBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.party.person.ICustomerDomainMapper;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.promotion.IPromotionEntityService;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.entity.promotion.order.PromotionOrderEntity;
import io.nzbee.entity.party.person.PersonDomainDTO;

@Component
public class BagDomainDTOMapperImpl implements IBagDomainDTOMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagEntityService bagService;

	@Autowired
	private ICustomerDomainMapper personMapper;

	@Autowired
	private IRegularBagItemDomainDTOMapper bagItemMapper;

	@Autowired
	private IPersonService personService;

	@Autowired
	private IPromotionEntityService promotionService;

	@Override
	public Bag DTOToDo(BagDomainDTO dto) {
		Bag b = new Bag(personMapper.DTOToDo(dto.getCustomer()));

		// map the entity bagItems to the domain bagItems
		Set<RegularBagItem> sbi = dto.getBagItems().stream().map(bi -> bagItemMapper.DTOToDo(b, bi))
				.collect(Collectors.toSet());

		// use the add item method on the domain object to
		// ensure business rules are fired against each added bagItem
		sbi.forEach(bi -> {
			b.addItem(bi, bi.getBagItem().getQuantity());
		});

		return b;
	}

	@Override
	public Bag DTOToDo(String locale, String currency, PersonDomainDTO pDto, BagDomainDTO bDto) {

		// we need a customer to instantiate a new bag
		Customer c = personMapper.DTOToDo(pDto);

		// create a new bag domain object
		Bag b = new Bag(c);

		// map the entity bagItems to the domain bagItems
		Set<RegularBagItem> sbi = bDto.getBagItems().stream().map(bi -> bagItemMapper.DTOToDo(b, bi))
				.collect(Collectors.toSet());

		// use the add item method on the domain object to
		// ensure business rules are fired against each added bagItem
		sbi.forEach(bi -> {
			b.addItem(bi, bi.getBagItem().getQuantity());
		});

		return b;
	}

	@Override
	public BagEntity doToEntity(Bag d) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".doToEntity");
		// get the bag, status, and customer from the database
		Optional<BagEntity> obe = bagService.findByCode(d.getCustomer().getUserName());
		Optional<PersonEntity> op = personService.findByUsernameAndRole(d.getCustomer().getUserName(),
				Constants.partyRoleCustomer);
		Optional<PromotionEntity> opr = Optional.ofNullable(null);
		if (d.getPromotion().isPresent()) {
			opr = promotionService.findByCode(d.getPromotion().get().getPromotionCode());
		}

		System.out.println("username = " + d.getCustomer().getUserName());
		System.out.println("bag is present = " + obe.isPresent());
		System.out.println("person is present = " + op.isPresent());
		System.out.println("bag promotion is present = " + opr.isPresent());

		BagEntity nbe = new BagEntity();
		nbe.setBagCreatedDateTime(LocalDateTime.now());
		nbe.setBagUpdatedDateTime(LocalDateTime.now());

		// use the existing bag if it exists otherwise use newly created
		BagEntity b = (obe.isPresent()) ? obe.get() : nbe;

		b.setBagUpdatedDateTime(LocalDateTime.now());

		// map the domain bagItems to entity bagItems
		Set<BagItemEntity> sbi = d.getBagItems().stream().map(bi -> {
			Optional<BagItemEntity> obi = b.getBagItems().stream()
					.filter(i -> i.getProduct().getProductUPC().equals(bi.getBagItem().getProductUPC())).findAny();

			if (obi.isPresent()) {
				BagItemEntity bie = obi.get();
				bie.setQuantity(bi.getBagItem().getQuantity());
				return bie;
			}

			return bagItemMapper.doToEntity(bi);
		}).collect(Collectors.toSet());

		// add the bag items to the bag
		sbi.stream().forEach(bi -> {
			b.addItem(bi);
		});

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
