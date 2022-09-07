package io.nzbee.entity.adapters.view;

import java.math.BigDecimal;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.Globals;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.promotion.value.ProductUPC;
import io.nzbee.entity.bag.view.BagViewDTO;
import io.nzbee.entity.bag.view.IBagViewDTOMapper;
import io.nzbee.entity.bag.view.IBagViewDTOService;
import io.nzbee.view.bag.BagView;
import io.nzbee.view.ports.IBagPortService;

public class BagViewAdapterImpl implements IBagPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private Globals globals;
	
	@Autowired
	private IBagViewDTOService bagService;
	
	@Autowired
	private IBagViewDTOMapper bagMapper;
	
	@Override
	@Transactional
	public BagView findByCode(String locale, String currency, String userName, Bag bag) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameter {}, {}, {}", locale, currency, userName);
		
		//this will also retrieve the items of the bag
		Optional<BagViewDTO> b = bagService.findByCode(locale, currency, globals.getDefaultProductRootCategoryCode(),userName);
		
		return bagMapper.DTOToView(b.get(), bag);
	}

	@Override
	@Transactional
	public BagView toView(String locale, String currency, Bag b) {
		//this function will convert the bag domain object to a view representation
		LOGGER.debug("call " + getClass().getSimpleName() + ".toView with parameter {}, {}, {}", locale, currency, b.getCustomer().getUserName());
		Optional<BagViewDTO> bvDto = bagService.findByCode(locale, currency, globals.getDefaultProductRootCategoryCode(), b.getCustomer().getUserName());
		BagView bv = bagMapper.DTOToView(bvDto.get(), b);
    	bv.setGrandTotalAmount(b.getGrandTotalAmount().amount());
    	bv.setSubTotalAmount(b.getSubTotalAmount().amount());
    	bv.setTotalQuantity(b.getTotalQuantity());
    	bv.setTotalItems(b.getTotalItems());
    	bv.setTotalWeight(b.getTotalWeight());
    	bv.getBagItems().forEach(v -> {
    		RegularBagItem bi = b.getBagItem(new ProductUPC(v.getItemUPC()));
    		v.setBagItemTotal(bi.getBagItem().getBagItemTotal().amount());
    		v.setBagItemDiscount(bi.getBagItem().getBagItemDiscount().amount());
    	});
    	return bv;	
	}

}
