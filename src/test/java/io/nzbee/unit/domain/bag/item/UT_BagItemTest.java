package io.nzbee.unit.domain.bag.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
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
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.BagItemConfiguration;
import io.nzbee.domain.bag.item.regular.IRegularBagItemDomainService;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.IRegularBagItemPortService;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.unit.domain.beans.customer.CustomerDoBeanFactory;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {UT_Config.class, BagItemConfiguration.class})
public class UT_BagItemTest {

	@Autowired
	private CustomerDoBeanFactory customerDoBeanFactory;
	
	@Autowired
    private IRegularBagItemDomainService bagItemService;
    
	@MockBean
	private IRegularBagItemPortService bagItemPortService;
	
    private RegularBagItem bagItem = null;
    
    private Bag bag;
    
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.openMocks(this);
		
		Customer c = customerDoBeanFactory.getBean();
		
		bag = new Bag(c);
		
	}

	
	@Test
	public void when3EligableItemsAdded_thenB3G33PromotionDiscountIsApplied() {
		
		bagItem = new RegularBagItem(new BagItem(bag, "abcd123", 3, new BigDecimal(10)), new BigDecimal(1), true);

		Promotion b3g33 = new Promotion("B3G33", 
				 						"Buy 3 Get 33% off",
										LocalDateTime.of(2020, Month.JANUARY, 8, 0,0,0),
										LocalDateTime.of(2021, Month.JANUARY, 8, 0,0,0),
										"BNGNPCT",
										"Buy N Get X Percent Off",
										"PRD01",
										"Product");
		
		bagItem.getBagItem().addPromotion(b3g33);
		
		bagItemService.checkAllBagItemRules(bagItem);
		
		assertThat(bagItem.getBagItem().getDiscounts().size())
        .isEqualTo(1);
    	
    	assertThat(bagItem.getBagItem().getBagItemDiscount())
    	.isEqualTo(Double.valueOf(10.0));
	}
	
	
	@Test
	public void when6EligableItemsAdded_thenB3G33PromotionDiscountIsApplied() {
		
		bagItem = new RegularBagItem(new BagItem(bag, "abcd123", 6, new BigDecimal(10)), new BigDecimal(1), true);

		Promotion b3g33 = new 	Promotion(	"B3G33", 
					 						"Buy 3 Get 33% off",
											LocalDateTime.of(2020, Month.JANUARY, 8, 0,0,0),
											LocalDateTime.of(2021, Month.JANUARY, 8, 0,0,0),
											"BNGNPCT", 
											"Buy N Get X Percent Off",
											"PRD01",
											"Product");
		
		bagItem.getBagItem().addPromotion(b3g33);
		
		bagItemService.checkAllBagItemRules(bagItem);
	
		assertThat(bagItem.getBagItem().getDiscounts().size())
        .isEqualTo(1);
    	
    	assertThat(bagItem.getBagItem().getBagItemDiscount())
    	.isEqualTo(Double.valueOf(20.0));
	}
	
	@Test
	public void whenItemQuantityExceedsMaximum_thenBagItemIsNotAdded() {
		
		bagItem = new RegularBagItem(new BagItem(bag, "abcd123", 7, new BigDecimal(10)), new BigDecimal(1), true);
		
		bagItemService.checkAllBagItemRules(bagItem);
    	
    	assertThat(bag.bagItemExists(bagItem.getBagItem().getProductUPC())).isFalse();
	}
	
	@Test
	public void whenItemIsOutOfStock_thenBagItemIsNotAdded() {
		
		bagItem = new RegularBagItem(new BagItem(bag, "abcd123", 1, new BigDecimal(10)), new BigDecimal(1), true);
		
		bagItemService.checkAllBagItemRules(bagItem);
    	
    	assertThat(bag.bagItemExists("abcd123")).isFalse();
	}

}
