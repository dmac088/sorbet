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
import io.nzbee.UT_Config;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.BagItemConfiguration;
import io.nzbee.domain.bag.item.regular.IRegularBagItemDomainService;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.IBagItemPortService;
import io.nzbee.domain.promotion.bag.IPromotionBagItem;
import io.nzbee.unit.domain.beans.customer.CustomerDoBeanFactory;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {UT_Config.class, BagItemConfiguration.class})
public class UT_BagItemTest {

	@Autowired
	private CustomerDoBeanFactory customerDoBeanFactory;
	
	@Autowired
    private IRegularBagItemDomainService bagItemService;
    
	@MockBean
	private IBagItemPortService bagItemPortService;
	
    private IPromotionBagItem bagItem = null;
    
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.openMocks(this);
		
		Customer c = customerDoBeanFactory.getBean();
		
	}

	
	@Test
	public void when3EligableItemsAdded_thenB3G33PromotionDiscountIsApplied() {
		
		bagItemService.checkAllBagItemRules(bagItem);
		
    	assertThat(bagItem.getDiscountAmount())
    	.isEqualTo(Double.valueOf(10.0));
	}
	
	
	@Test
	public void when6EligableItemsAdded_thenB3G33PromotionDiscountIsApplied() {
		
		bagItemService.checkAllBagItemRules(bagItem);
		
    	assertThat(bagItem.getDiscountAmount())
    	.isEqualTo(Double.valueOf(10.0));
	}
	
}
