package io.nzbee.integration.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.domain.bag.BagConfiguration;
import io.nzbee.domain.bag.BagDomainServiceImpl;
import io.nzbee.domain.bag.item.BagItemConfiguration;
import io.nzbee.domain.bag.item.regular.RegularBagItemServiceImpl;
import io.nzbee.domain.promotion.PromotionServiceImpl;
import io.nzbee.entity.adapters.domain.BagDomainAdapter;
import io.nzbee.entity.adapters.domain.BagItemDomainAdapter;
import io.nzbee.entity.adapters.domain.PromotionAdapter;
import io.nzbee.entity.adapters.view.BagViewAdapterImpl;
import io.nzbee.entity.bag.domain.BagDomainDTODaoImpl;
import io.nzbee.entity.bag.domain.BagDomainDTOMapperImpl;
import io.nzbee.entity.bag.domain.BagDomainDTOServiceImpl;
import io.nzbee.entity.bag.entity.BagEntityServiceImpl;
import io.nzbee.entity.bag.item.entity.BagItemEntityServiceImpl;
import io.nzbee.entity.bag.item.view.BagItemViewDTOMapperImpl;
import io.nzbee.entity.bag.item.domain.regular.RegularBagItemDomainDTOMapperImpl;
import io.nzbee.entity.bag.item.domain.regular.RegularBagItemDomainDTOServiceImpl;
import io.nzbee.entity.bag.status.BagItemStatusServiceImpl;
import io.nzbee.entity.bag.view.BagViewDTODaoPostgresImpl;
import io.nzbee.entity.bag.view.BagViewDTOMapperImpl;
import io.nzbee.entity.bag.view.BagViewDTOServiceImpl;
import io.nzbee.entity.party.person.CustomerMapperImpl;
import io.nzbee.entity.party.person.PersonServiceImpl;
import io.nzbee.entity.product.attribute.ProductAttributeServiceImpl;
import io.nzbee.entity.product.currency.CurrencyServiceImpl;
import io.nzbee.entity.product.physical.view.PhysicalProductDTODaoPostgresImpl;
import io.nzbee.entity.product.physical.view.PhysicalProductDTOMapperImpl;
import io.nzbee.entity.product.physical.view.PhysicalProductDTOServiceImpl;
import io.nzbee.entity.product.ProductEntityServiceImpl;
import io.nzbee.entity.product.price.ProductPriceServiceImpl;
import io.nzbee.entity.product.price.ProductPriceTypeService;
import io.nzbee.entity.promotion.PromotionDaoPostgresImpl;
import io.nzbee.entity.promotion.PromotionEntityServiceImpl;
import io.nzbee.entity.promotion.PromotionMapperImpl;
import io.nzbee.entity.promotion.order.PromotionOrderMapperImpl;
import io.nzbee.entity.promotion.order.PromotionOrderServiceImpl;
import io.nzbee.entity.promotion.product.PromotionProductMapperImpl;
import io.nzbee.payment.PaymentService;
import io.nzbee.resources.bag.BagResourceAssembler;
import io.nzbee.resources.bag.item.BagItemResourceAssembler;
import io.nzbee.resources.controllers.BagController;
import io.nzbee.security.OAuth2AuthorizationServerConfig;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.UserService;
import io.nzbee.view.bag.BagViewMapperImpl;
import io.nzbee.view.bag.BagViewServiceImpl;
import io.nzbee.view.bag.item.BagItemViewMapperImpl;

@RunWith(SpringRunner.class)
@EnableJpaRepositories(entityManagerFactoryRef = "mochiEntityManagerFactory", transactionManagerRef = "mochiTransactionManager", basePackages = {
		"io.nzbee.entity", "io.nzbee.security" })
@ContextConfiguration(classes = { PaymentService.class, ConfigControllerTest.class, SecurityBeanConfiguration.class,
		OAuth2AuthorizationServerConfig.class, OAuth2ResourceServerConfig.class, UserService.class,
		WebSecurityConfig.class, Globals.class, BagController.class, BagDomainAdapter.class, CustomerMapperImpl.class,
		PromotionMapperImpl.class, PromotionOrderMapperImpl.class, PromotionProductMapperImpl.class,
		ProductEntityServiceImpl.class, BagItemStatusServiceImpl.class, PersonServiceImpl.class,
		PromotionServiceImpl.class, PromotionEntityServiceImpl.class, PromotionDaoPostgresImpl.class,
		BagDomainServiceImpl.class, BagConfiguration.class, BagItemDomainAdapter.class, BagItemEntityServiceImpl.class,
		BagItemConfiguration.class, ProductAttributeServiceImpl.class, ProductPriceServiceImpl.class,
		CurrencyServiceImpl.class, ProductPriceTypeService.class, PromotionAdapter.class,
		PromotionOrderServiceImpl.class, BagViewMapperImpl.class, BagItemViewMapperImpl.class,
		BagResourceAssembler.class, BagItemResourceAssembler.class, BagViewServiceImpl.class, BagViewAdapterImpl.class,
		PhysicalProductDTOServiceImpl.class, PhysicalProductDTODaoPostgresImpl.class, BagViewDTOServiceImpl.class,
		BagViewDTODaoPostgresImpl.class, BagViewDTOMapperImpl.class, BagItemViewMapperImpl.class,
		BagItemViewDTOMapperImpl.class, PhysicalProductDTOMapperImpl.class, RegularBagItemDomainDTOServiceImpl.class,
		RegularBagItemDomainDTOMapperImpl.class, BagDomainDTOServiceImpl.class, BagDomainDTOMapperImpl.class,
		BagEntityServiceImpl.class, RegularBagItemServiceImpl.class, BagDomainDTODaoImpl.class})
@WebMvcTest
public class CT_BagControllerTest {

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		IntegrationTestSetupHelper.dbInit(database);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}
	
	@Test
	public void testWhenBagIsEmpty_ThenReturnAnEmptyBag() throws Exception {
		// to-do
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
																	ConfigControllerTest.TEST_PASSWORD, webApplicationContext);
		
		
		// remove the items to empty the bag
		String url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Items/Remove/17235347";
		mockMvc.perform(get(url).header("Authorization", "Bearer " + accessToken).with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk());
		
		url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Items/Remove/12345678";
		mockMvc.perform(get(url).header("Authorization", "Bearer " + accessToken).with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk());
		
		//check the bag is still viewable through the api
		url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD;
		mockMvc.perform(get(url).header("Authorization", "Bearer " + accessToken).with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$.data.totalItems", is(0))).andExpect(jsonPath("$.data.totalQuantity", is(0)));
		
		//add items back to restore integrity
		url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Items/Physical/Add";
		mockMvc.perform(post(url).header("Authorization", "Bearer " + accessToken)
				.content("{\n" + "  \"itemUPC\": \"17235347\",\n" + "  \"itemQty\": 1\n" + "}").with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"));
		
		url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Items/Physical/Add";
		mockMvc.perform(post(url).header("Authorization", "Bearer " + accessToken)
				.content("{\n" + "  \"itemUPC\": \"12345678\",\n" + "  \"itemQty\": 1\n" + "}").with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"));
	}

	@Test
	public void testWhenCustomerLoginSuccessfully_ThenCustomerCanViewBag() throws Exception {
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);
		String 
		url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD;
		mockMvc.perform(get(url).header("Authorization", "Bearer " + accessToken).with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$.data.totalItems", is(2))).andExpect(jsonPath("$.data.totalQuantity", is(2)));
	}

	@Test
	public void testWhenCustomerLoginSuccessfully_ThenCustomerCanViewBagItems() throws Exception {
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);
		String url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Items";
		mockMvc.perform(get(url).header("Authorization", "Bearer " + accessToken).with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$._embedded.bagItemResources.length()", is(2)));
	}

	@Test
	public void testWhenCustomerLoginSuccessfullyAndAddCashewsItemToBag_ThenCustomerBagIsUpdatedWithCashewsProduct()
			throws Exception {
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);
		String url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Items/Physical/Add";
		mockMvc.perform(post(url).header("Authorization", "Bearer " + accessToken)
				.content("{\n" + "  \"itemUPC\": \"17152401\",\n" + "  \"itemQty\": 1\n" + "}").with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"));

		// check if the item was added to the bag
		url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Items";
		mockMvc.perform(get(url).header("Authorization", "Bearer " + accessToken).with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$._embedded.bagItemResources.length()", is(3)));

		// remove the item to preserve integrity
		url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Items/Remove/17152401";
		mockMvc.perform(get(url).header("Authorization", "Bearer " + accessToken).with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testWhenCustomerLoginSuccessfullyAndRemoveCashewsItemFromBag_ThenCustomerBagIsUpdatedWithCashewsProductRemoved()
			throws Exception {
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);
		String url = "/api/Bag/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Items/Remove/17152401";
		mockMvc.perform(get(url).header("Authorization", "Bearer " + accessToken).with(csrf())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk());
	}

}
