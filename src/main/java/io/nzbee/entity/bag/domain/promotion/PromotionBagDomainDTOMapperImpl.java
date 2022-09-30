package io.nzbee.entity.bag.domain.promotion;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.promotion.bag.PromotionBag;
import io.nzbee.domain.promotion.bag.item.IPromotionBagItem;
import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.domain.valueObjects.Quantity;
import io.nzbee.domain.valueObjects.UserName;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.entity.IBagEntityService;
import io.nzbee.entity.bag.item.domain.promotion.IPromotionBagItemDomainDTOMapper;
import io.nzbee.exceptions.EntityNotFoundException;

@Component
public class PromotionBagDomainDTOMapperImpl implements IPromotionBagDomainDTOMapper {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagEntityService bagService;

	@Autowired 
	private IPromotionBagItemDomainDTOMapper bagItemMapper;
	

	@Override
	public PromotionBag toDo(PromotionBagDomainDTO dto) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".DTOToDo()");

		PromotionBag pb = new PromotionBag(new UserName(dto.getCustomer().getUserName()), Locale.localize(dto.getLocale(), dto.getCurrency()), new Quantity(new Long(10)), 1);

		//add the items to PromotionBag one by one
		dto.getPromotionBagItems().stream().forEach(i -> {
			pb.addPromotionItem(bagItemMapper.toDo(pb, i));
		});
		
		//add the coupons to the PromotionBag one by one
		dto.getCoupons().stream().forEach(cpn -> {
			pb.addCoupon(new CouponCode(cpn));
		});

		return pb;
	}


	@Override
	public BagEntity toEntity(IPromotionBag d) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".toEntity()");
		BagEntity be = bagService.findByCode(d.getUserName().toString()).orElseThrow(
				() -> new EntityNotFoundException(ErrorKeys.bagNotFound, d.getLocale(), d.getUserName().toString()));
		
		be.getBagItems().stream().forEach(i -> {
			Optional<IPromotionBagItem> opi = d.getBagItems().stream().filter(pi ->  pi.getItemUPC().sameAs(new ProductUPC(i.getProduct().getUPC()))).findAny();
			if(opi.isPresent()) {
				//we only update the line item discount amount
				LOGGER.debug("setBagItemDiscountPercentage(" + opi.get().getDiscountPercentage().amount() + ")");
				i.setBagItemDiscountPercentage(opi.get().getDiscountPercentage().amount());
				
			}
		});
		
		return be;
	}

}
