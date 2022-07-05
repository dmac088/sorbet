package io.nzbee.integration.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.WebMvcConfig;
import io.nzbee.domain.bag.BagConfiguration;
import io.nzbee.domain.bag.BagDomainServiceImpl;
import io.nzbee.domain.bag.item.BagItemConfiguration;
import io.nzbee.domain.bag.item.regular.RegularBagItemServiceImpl;
import io.nzbee.entity.promotion.PromotionEntityServiceImpl;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.domain.BagDomainAdapter;
import io.nzbee.entity.adapters.domain.BagItemDomainAdapter;
import io.nzbee.entity.adapters.view.BrandAdapterImpl;
import io.nzbee.entity.adapters.view.PhysicalProductFullAdapterImpl;
import io.nzbee.entity.adapters.view.PhysicalProductLightAdapterImpl;
import io.nzbee.entity.adapters.view.ShippingDestinationAdapterImpl;
import io.nzbee.entity.adapters.view.ShippingProductAdapterImpl;
import io.nzbee.entity.adapters.view.ShippingTypeAdapterImpl;
import io.nzbee.entity.bag.domain.BagDomainDTODaoImpl;
import io.nzbee.entity.bag.domain.BagDomainDTOMapperImpl;
import io.nzbee.entity.bag.domain.BagDomainDTOServiceImpl;
import io.nzbee.entity.bag.entity.BagEntityServiceImpl;
import io.nzbee.entity.bag.item.domain.regular.RegularBagItemDomainDTOMapperImpl;
import io.nzbee.entity.bag.item.domain.regular.RegularBagItemDomainDTOServiceImpl;
import io.nzbee.entity.bag.item.entity.BagItemEntityServiceImpl;
import io.nzbee.entity.bag.status.BagItemStatusServiceImpl;
import io.nzbee.entity.bag.view.BagViewDTODaoPostgresImpl;
import io.nzbee.entity.bag.view.BagViewDTOServiceImpl;
import io.nzbee.entity.brand.view.BrandDTOMapperImpl;
import io.nzbee.entity.brand.view.BrandDTOServiceImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTODaoImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOServiceImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTODaoImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTOServiceImpl;
import io.nzbee.entity.party.person.CustomerMapperImpl;
import io.nzbee.entity.party.person.PersonServiceImpl;
import io.nzbee.entity.product.ProductEntityServiceImpl;
import io.nzbee.entity.product.attribute.ProductAttributeServiceImpl;
import io.nzbee.entity.product.currency.CurrencyServiceImpl;
import io.nzbee.entity.product.physical.full.PhysicalProductFullDTOServiceImpl;
import io.nzbee.entity.product.physical.full.PhysicalProductFullDaoImpl;
import io.nzbee.entity.product.physical.full.PhysicalProductFullViewMapperImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDaoImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightMapperImpl;
import io.nzbee.entity.product.physical.view.PhysicalProductDTODaoPostgresImpl;
import io.nzbee.entity.product.physical.view.PhysicalProductDTOServiceImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDTOServiceImpl;
import io.nzbee.entity.product.price.ProductPriceServiceImpl;
import io.nzbee.entity.product.price.ProductPriceTypeService;
import io.nzbee.entity.product.shipping.attribute.destination.ShippingDestinationViewMapperImpl;
import io.nzbee.entity.product.shipping.attribute.type.ShippingTypeViewMapperImpl;
import io.nzbee.entity.product.shipping.attribute.view.ShippingProductAttributeViewServiceImpl;
import io.nzbee.entity.product.shipping.view.ShippingProductViewDTOMapperImpl;
import io.nzbee.entity.product.shipping.view.ShippingProductViewDTOServiceImpl;
import io.nzbee.entity.promotion.PromotionDaoPostgresImpl;
import io.nzbee.entity.promotion.PromotionMapperImpl;
import io.nzbee.entity.promotion.order.PromotionOrderMapperImpl;
import io.nzbee.entity.promotion.product.PromotionProductMapperImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOPostgresDaoImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOServiceImpl;
import io.nzbee.resources.brand.BrandViewModelAssembler;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.resources.product.physical.full.PhysicalProductFullModelAssembler;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModelAssembler;
import io.nzbee.resources.product.shipping.ShippingProductResourceAssembler;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResourceAssembler;
import io.nzbee.resources.product.shipping.type.ShippingTypeResourceAssembler;
import io.nzbee.search.FacetServicesImpl;
import io.nzbee.search.SearchServiceImpl;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.security.OAuth2AuthorizationServerConfig;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.IUserRepository;
import io.nzbee.security.user.UserService;
import io.nzbee.view.product.brand.BrandViewServiceImpl;
import io.nzbee.view.product.physical.full.PhysicalProductFullServiceImpl;
import io.nzbee.view.product.physical.light.PhysicalProductLightViewServiceImpl;
import io.nzbee.view.product.shipping.ShippingProductViewServiceImpl;
import io.nzbee.view.product.shipping.destination.ShippingDestinationViewServiceImpl;
import io.nzbee.view.product.shipping.type.ShippingTypeViewServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ProductController.class, PhysicalProductLightViewServiceImpl.class,
		PhysicalProductLightAdapterImpl.class, PhysicalProductLightDTOServiceImpl.class,
		PhysicalProductFullServiceImpl.class, PhysicalProductLightDaoImpl.class, PhysicalProductLightMapperImpl.class,
		SearchServiceImpl.class, DataSourceBeanMochi.class, DataSourceBeanSecurity.class, WebMvcConfig.class,
		UserService.class, Globals.class, PhysicalProductLightModelAssembler.class, SecurityBeanConfiguration.class,
		DataSourceBeanMochi.class, DataSourceBeanSecurity.class, WebMvcConfig.class, UserService.class,
		IUserRepository.class, OAuth2ResourceServerConfig.class, ProductCategoryFacetDTOServiceImpl.class,
		BrandFacetDTOServiceImpl.class, TagFacetDTOServiceImpl.class, FacetServicesImpl.class,
		ProductCategoryFacetDTODaoImpl.class, BrandFacetDTODaoImpl.class, TagFacetDTOPostgresDaoImpl.class,
		BagDomainAdapter.class, BagDomainServiceImpl.class, CustomerMapperImpl.class, 
		PromotionMapperImpl.class, PromotionOrderMapperImpl.class,
		PromotionProductMapperImpl.class, ProductEntityServiceImpl.class, BagItemStatusServiceImpl.class,
		PersonServiceImpl.class, PromotionEntityServiceImpl.class, PromotionDaoPostgresImpl.class,
		BagConfiguration.class, BrandViewServiceImpl.class, BrandAdapterImpl.class, BrandViewServiceImpl.class,
		BrandDTOServiceImpl.class, BrandDTOMapperImpl.class, ShippingProductAdapterImpl.class,
		ShippingProductViewDTOMapperImpl.class, ShippingDestinationResourceAssembler.class,
		ShippingTypeResourceAssembler.class, ShippingProductResourceAssembler.class, BrandViewModelAssembler.class,
		PhysicalProductFullModelAssembler.class, ProductAttributeServiceImpl.class, ProductPriceServiceImpl.class,
		CurrencyServiceImpl.class, ProductPriceTypeService.class, PhysicalProductFullAdapterImpl.class,
		PhysicalProductFullDTOServiceImpl.class, PhysicalProductFullDaoImpl.class,
		PhysicalProductFullViewMapperImpl.class, BagItemDomainAdapter.class,
		BagItemEntityServiceImpl.class, BagItemConfiguration.class, WebSecurityConfig.class,
		ShippingDestinationViewServiceImpl.class, ShippingDestinationAdapterImpl.class,
		ShippingDestinationViewMapperImpl.class, ShippingTypeViewServiceImpl.class, ShippingTypeAdapterImpl.class,
		ShippingTypeViewMapperImpl.class, BagViewDTOServiceImpl.class, BagViewDTODaoPostgresImpl.class,
		PhysicalProductDTOServiceImpl.class, PhysicalProductDTODaoPostgresImpl.class, RegularBagItemDomainDTOServiceImpl.class,
		RegularBagItemDomainDTOMapperImpl.class, ShippingProductViewServiceImpl.class, ShippingProductViewDTOServiceImpl.class,
		OAuth2AuthorizationServerConfig.class, OAuth2ResourceServerConfig.class, BagDomainDTOServiceImpl.class,
		ShippingProductAttributeViewServiceImpl.class, BagDomainDTOMapperImpl.class, BagEntityServiceImpl.class,
		RegularBagItemDomainDTOServiceImpl.class, RegularBagItemServiceImpl.class, BagItemDomainAdapter.class,
		BagDomainDTODaoImpl.class})
@WebMvcTest(ProductController.class)
@Import(ProductController.class)
public class CT_ProductControllerIntegrationTest {

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
	public void testBrowseAllProductsForFruitCategoryWithPaginationAndOrderOfNameAscending() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD
						+ "/Category/Code/FRT01?page=0&size=10&sort=nameAsc")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))

				.andExpect(jsonPath("$.searchResults._embedded.products").exists())
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(10)))

				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productUPC").value("18911676"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productDesc").value("Apple"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productRetail").value("72"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productMarkdown").value("64.8"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.brandDesc").value("Planters"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productImage").value("apple.jpg"))

				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productUPC").value("19037164"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productDesc").value("Strawberry"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productRetail").value("180"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productMarkdown").value("162.0"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.brandDesc").value("Shine"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productImage").value("strawberry.jpg"))

				.andExpect(jsonPath("$.searchResults.page").exists())
				.andExpect(jsonPath("$.searchResults.page.size").value("10"))
				.andExpect(jsonPath("$.searchResults.page.totalElements").value("12"))
				.andExpect(jsonPath("$.searchResults.page.totalPages").value("2"))
				.andExpect(jsonPath("$.searchResults.page.number").value("0"));
	}

	@Test
	public void testBrowseAllProductsForFruitCategoryWithPaginationAndOrderOfNameDescending() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD
						+ "/Category/Code/FRT01?page=0&size=10&sort=nameDesc")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))

				.andExpect(jsonPath("$.searchResults._embedded.products").exists())
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(10)))

				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productUPC").value("15483827"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productDesc").value("Water Melon"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productRetail").value("28"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productMarkdown").value("25.2"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.brandDesc").value("Driscolls"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productImage").value("water-melon.jpg"))

				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productUPC").value("10760430"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productDesc").value("Grapes"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productRetail").value("60"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productMarkdown").value("54.0"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.brandDesc").value("Enza"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productImage").value("grapes.jpg"))

				.andExpect(jsonPath("$.searchResults.page").exists())
				.andExpect(jsonPath("$.searchResults.page.size").value("10"))
				.andExpect(jsonPath("$.searchResults.page.totalElements").value("12"))
				.andExpect(jsonPath("$.searchResults.page.totalPages").value("2"))
				.andExpect(jsonPath("$.searchResults.page.number").value("0"));
	}

	@Test
	public void testBrowseAllProductsForFruitCategoryWithPaginationAndOrderOfPriceAscending() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD
						+ "/Category/Code/FRT01?page=0&size=10&sort=priceAsc")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))

				.andExpect(jsonPath("$.searchResults._embedded.products").exists())
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(10)))

				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productUPC").value("25556789"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productDesc").value("tomato"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productRetail").value("16"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productMarkdown").value("14.4"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.brandDesc").value("Shine"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productImage").value("tomato.jpg"))

				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productUPC").value("17235347"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productDesc").value("Pomegranate"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productRetail").value("95"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productMarkdown").value("85.5"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.brandDesc").value("Adora"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productImage").value("pomegranate.jpg"))

				.andExpect(jsonPath("$.searchResults.page").exists())
				.andExpect(jsonPath("$.searchResults.page.size").value("10"))
				.andExpect(jsonPath("$.searchResults.page.totalElements").value("12"))
				.andExpect(jsonPath("$.searchResults.page.totalPages").value("2"))
				.andExpect(jsonPath("$.searchResults.page.number").value("0"));
	}

	@Test
	public void testBrowseAllProductsForFruitCategoryWithPaginationAndOrderOfPriceDescending() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD
						+ "/Category/Code/FRT01?page=0&size=10&sort=priceDesc")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))

				.andExpect(jsonPath("$.searchResults._embedded.products").exists())
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(10)))

				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productUPC").value("19037164"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productDesc").value("Strawberry"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productRetail").value("180"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productMarkdown").value("162.0"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.brandDesc").value("Shine"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productImage").value("strawberry.jpg"))

				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productUPC").value("10688155"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productDesc").value("Musk Melon"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productRetail").value("36"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productMarkdown").value("32.4"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.brandDesc").value("Planters"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productImage").value("musk-melon.jpg"))

				.andExpect(jsonPath("$.searchResults.page").exists())
				.andExpect(jsonPath("$.searchResults.page.size").value("10"))
				.andExpect(jsonPath("$.searchResults.page.totalElements").value("12"))
				.andExpect(jsonPath("$.searchResults.page.totalPages").value("2"))
				.andExpect(jsonPath("$.searchResults.page.number").value("0"));
	}

	@Test
	public void testGetAllShippingProviders() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/Product/Shipping/Provider/" + Constants.localeENGB + "/" + Constants.currencyHKD)
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$._embedded").exists()).andExpect(jsonPath("$._embedded.brands").exists())
				.andExpect(jsonPath("$._embedded.brands[0].data").exists())
				.andExpect(jsonPath("$._embedded.brands[0].data.brandCode").exists())
				.andExpect(jsonPath("$._embedded.brands[0].data.brandCode").value("HKP01"));
	}

	@Test
	public void testGetSingleProductGivenProductCodeInEnglishAndUSD() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyUSD + "/Code/17366878")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$.data").exists()).andExpect(jsonPath("$.data.productUPC").exists())
				.andExpect(jsonPath("$.data.productDesc").exists()).andExpect(jsonPath("$.data.productRetail").exists())
				.andExpect(jsonPath("$.data.productMarkdown").exists()).andExpect(jsonPath("$.data.brandDesc").exists())
				.andExpect(jsonPath("$.data.inStock").exists())
				.andExpect(jsonPath("$.data.productUPC").value("17366878"))
				.andExpect(jsonPath("$.data.productDesc").value("Banana"))
				.andExpect(jsonPath("$.data.productRetail").value("5.8"))
				.andExpect(jsonPath("$.data.productMarkdown").value("5.2"))
				.andExpect(jsonPath("$.data.inStock").value("true"))
				.andExpect(jsonPath("$.data.productImage").value("banana.jpg"))
				.andExpect(jsonPath("$.data.productLongDesc").value(
						"Lorem ipsum, dolor sit amet consectetur adipisicing elit. Itaque obcaecati tempore reiciendis neque facere! Eos, necessitatibus? Fugit iure veritatis quidem velit quaerat quos qui pariatur dolore facilis, aliquid quae voluptatibus dicta. Quae harum velit hic molestias, eius ab cum quidem voluptates modi maiores laboriosam iusto excepturi ex, recusandae aut, facere earum ad vero aperiam. Minima dolores dignissimos ab ipsam atque placeat sapiente officia debitis nobis porro at quia veritatis, quidem id repudiandae ex tempore non. Enim soluta explicabo atque adipisci itaque."));
	}

	@Test
	public void testGetSingleProductGivenProductCodeInTraditionalChineseAndHKD() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/Product/" + Constants.localeZHHK + "/" + Constants.currencyHKD + "/Code/17366878")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$.data").exists()).andExpect(jsonPath("$.data.productUPC").exists())
				.andExpect(jsonPath("$.data.productDesc").exists()).andExpect(jsonPath("$.data.productRetail").exists())
				.andExpect(jsonPath("$.data.productMarkdown").exists()).andExpect(jsonPath("$.data.brandDesc").exists())
				.andExpect(jsonPath("$.data.inStock").exists())
				.andExpect(jsonPath("$.data.productUPC").value("17366878"))
				.andExpect(jsonPath("$.data.productDesc").value("香蕉"))
				.andExpect(jsonPath("$.data.productRetail").value("45"))
				.andExpect(jsonPath("$.data.productMarkdown").value("40.5"))
				.andExpect(jsonPath("$.data.inStock").value("true"))
				.andExpect(jsonPath("$.data.productImage").value("banana.jpg"))
				.andExpect(jsonPath("$.data.productLongDesc").value(
						"疼痛本身，疼痛是在主要的脂肪過程中實施的。被時間拒絕或做事蒙蔽了雙眼！那些需求？他迴避真理的權利，並渴望尋找那些他容易忍受痛苦的人，這種痛苦被稱為快樂。他選擇了哪一種煩惱，當他確實會歡迎、接受或打開正義者以這種辛苦的方式帶來的樂趣，並讓它們向真理敞開心扉時。最小的痛苦對她來說是最值得的，智者用我們應盡的義務取悅她；我將解釋鬆散的目的，並相應地獲得它們"));
	}

	@Test
	public void whenRequestingShippingProduct_ThenShippingProductIsReturned() throws Exception {

		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/Product/en-GB/HKD/Destination/HKG/Type/LEG")
				.header("Authorization", "Bearer " + accessToken).with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk());
	}
}