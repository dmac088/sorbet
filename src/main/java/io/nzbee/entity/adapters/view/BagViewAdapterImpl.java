package io.nzbee.entity.adapters.view;

import java.math.BigDecimal;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.Constants;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.entity.bag.view.BagViewDTO;
import io.nzbee.entity.bag.view.IBagViewDTOMapper;
import io.nzbee.entity.bag.view.IBagViewDTOService;
import io.nzbee.entity.product.shipping.entity.IShippingProductService;
import io.nzbee.entity.product.shipping.entity.ShippingProductEntity;
import io.nzbee.hkpost.IHKPostService;
import io.nzbee.hkpost.PostageProductViewDTO;
import io.nzbee.view.bag.BagView;
import io.nzbee.view.ports.IBagPortService;

public class BagViewAdapterImpl implements IBagPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagViewDTOService bagService;
	
	@Autowired
	private IBagViewDTOMapper bagMapper;
	
	@Autowired
	private IHKPostService hkPostService;
	
	@Autowired
	private IShippingProductService shippingProductService;
	
	@Override
	@Transactional
	public BagView findByCode(Locale locale, String userName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByCode with parameter {}, {}, {}", locale, locale.getCurrency().getCurrencyCode(), userName);
		
		//this will also retrieve the items of the bag
		Optional<BagViewDTO> ob = bagService.findByCode(locale.getLanguageCode(), locale.getCurrency().getCurrencyCode(),userName);
		
		
		
		if(ob.get().getBagItems().stream().filter(i -> i.getBagItemTypeCode().equals(Constants.shippingBagItemType)).findAny().isPresent()) {
	
			String shippingUpc = ob.get().getBagItems().stream().filter(i -> i.getBagItemTypeCode().equals(Constants.shippingBagItemType)).findAny().get().getBagItemUPC();
			
			//get the postage product details from the database
			ShippingProductEntity s = shippingProductService.findByUpc(shippingUpc);
			
			//get the postage free from hk post
			PostageProductViewDTO postageFee = hkPostService.getHKPostageFee(s.getShippingCountryCode(), s.getShippingCode(), ob.get().getTotalWeight());
			
			BigDecimal fee = (postageFee != null && postageFee.getTotalPostage() != null)
								? postageFee.getTotalPostage()
								: BigDecimal.ZERO;
			
			return bagMapper.toView(ob.get(), fee, locale);
			
		}
		return bagMapper.toView(ob.get());
	}
	
	
	

}
