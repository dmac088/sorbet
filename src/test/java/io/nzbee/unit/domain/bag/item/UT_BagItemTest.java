package io.nzbee.unit.domain.bag.item;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import io.nzbee.Constants;
import io.nzbee.UT_Config;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.BagItemConfiguration;
import io.nzbee.domain.bag.item.regular.IRegularBagItemDomainService;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.IBagItemPortService;
import io.nzbee.domain.promotion.IPromotionService;
import io.nzbee.domain.promotion.bag.IPromotionBag;
import io.nzbee.domain.promotion.bag.item.IPromotionBagItem;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.UserName;
import io.nzbee.unit.domain.beans.customer.CustomerDoBeanFactory;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {UT_Config.class, BagItemConfiguration.class})
public class UT_BagItemTest {

	@Autowired
    private IPromotionService promotionService;
	
    private IPromotionBag pb;
    
    private final UserName TEST_USERNAME = new UserName("nob@nob");
    
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.openMocks(this);
		
		pb = promotionService.find(Locale.localize(Constants.localeENGB, Constants.currencyHKD), TEST_USERNAME);
				
	}

	
	@Test
	public void when3EligableItemsAdded_thenB3G33PromotionDiscountIsApplied() {
		
		promotionService.applyAll(pb);
		
//    	assertThat(bagItem.getDiscountPercentage())
//    	.isEqualTo(Double.valueOf(10.0));
	}
	
	
	@Test
	public void when6EligableItemsAdded_thenB3G33PromotionDiscountIsApplied() {
		
		promotionService.applyAll(pb);
		
//    	assertThat(bagItem.getDiscountPercentage())
//    	.isEqualTo(Double.valueOf(10.0));
	}
	
}
