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
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.WebMvcConfig;
import io.nzbee.domain.bag.BagConfiguration;
import io.nzbee.domain.bag.BagDomainServiceImpl;
import io.nzbee.domain.bag.item.BagItemConfiguration;
import io.nzbee.domain.bag.item.regular.RegularBagItemServiceImpl;
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
import io.nzbee.entity.product.physical.light.PhysicalProductLightDTOServiceImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDaoImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightMapperImpl;
import io.nzbee.entity.product.physical.view.PhysicalProductDTODaoPostgresImpl;
import io.nzbee.entity.product.physical.view.PhysicalProductDTOServiceImpl;
import io.nzbee.entity.product.price.ProductPriceServiceImpl;
import io.nzbee.entity.product.price.ProductPriceTypeService;
import io.nzbee.entity.product.shipping.attribute.destination.ShippingDestinationViewMapperImpl;
import io.nzbee.entity.product.shipping.attribute.type.ShippingTypeViewMapperImpl;
import io.nzbee.entity.product.shipping.attribute.view.ShippingProductAttributeViewServiceImpl;
import io.nzbee.entity.product.shipping.view.ShippingProductViewDTOMapperImpl;
import io.nzbee.entity.product.shipping.view.ShippingProductViewDTOServiceImpl;
import io.nzbee.entity.promotion.PromotionDaoPostgresImpl;
import io.nzbee.entity.promotion.PromotionEntityServiceImpl;
import io.nzbee.entity.promotion.PromotionMapperImpl;
import io.nzbee.entity.promotion.order.PromotionOrderMapperImpl;
import io.nzbee.entity.promotion.product.PromotionProductMapperImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOPostgresDaoImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOServiceImpl;
import io.nzbee.resources.brand.BrandViewModelAssembler;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.resources.controllers.SearchController;
import io.nzbee.resources.product.physical.full.PhysicalProductFullModelAssembler;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModelAssembler;
import io.nzbee.resources.product.shipping.ShippingProductResourceAssembler;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResourceAssembler;
import io.nzbee.resources.product.shipping.type.ShippingTypeResourceAssembler;
import io.nzbee.resources.search.SearchFacetModelAssembler;
import io.nzbee.search.FacetServicesImpl;
import io.nzbee.search.SearchServiceImpl;
import io.nzbee.security.DataSourceBeanSecurity;
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
		BagDomainAdapter.class, BagDomainServiceImpl.class, CustomerMapperImpl.class, PromotionMapperImpl.class,
		PromotionOrderMapperImpl.class, PromotionProductMapperImpl.class, ProductEntityServiceImpl.class,
		BagItemStatusServiceImpl.class, PersonServiceImpl.class, PromotionEntityServiceImpl.class,
		PromotionDaoPostgresImpl.class, BagConfiguration.class, BrandViewServiceImpl.class, BrandAdapterImpl.class,
		BrandViewServiceImpl.class, BrandDTOServiceImpl.class, BrandDTOMapperImpl.class,
		ShippingProductAdapterImpl.class, ShippingProductViewDTOMapperImpl.class,
		ShippingDestinationResourceAssembler.class, ShippingTypeResourceAssembler.class,
		ShippingProductResourceAssembler.class, BrandViewModelAssembler.class, PhysicalProductFullModelAssembler.class,
		ProductAttributeServiceImpl.class, ProductPriceServiceImpl.class, CurrencyServiceImpl.class,
		ProductPriceTypeService.class, PhysicalProductFullAdapterImpl.class, PhysicalProductFullDTOServiceImpl.class,
		PhysicalProductFullDaoImpl.class, PhysicalProductFullViewMapperImpl.class, SearchController.class,
		SearchFacetModelAssembler.class, WebSecurityConfig.class, BagItemDomainAdapter.class,
		BagItemEntityServiceImpl.class, BagItemConfiguration.class, ShippingDestinationViewServiceImpl.class,
		ShippingDestinationAdapterImpl.class, ShippingDestinationViewMapperImpl.class,
		ShippingTypeViewServiceImpl.class, ShippingTypeAdapterImpl.class, ShippingTypeViewMapperImpl.class,
		BagViewDTOServiceImpl.class, BagViewDTODaoPostgresImpl.class, PhysicalProductDTOServiceImpl.class,
		PhysicalProductDTODaoPostgresImpl.class, RegularBagItemDomainDTOServiceImpl.class,
		RegularBagItemDomainDTOMapperImpl.class, ShippingProductViewServiceImpl.class,
		ShippingProductViewDTOServiceImpl.class, ShippingProductAttributeViewServiceImpl.class,
		BagDomainDTOServiceImpl.class, BagDomainDTOMapperImpl.class, BagEntityServiceImpl.class,
		RegularBagItemServiceImpl.class, BagDomainDTODaoImpl.class})
@WebMvcTest(SearchController.class)
@Import(SearchController.class)
public class CT_SearchControllerIntegrationTest {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		IntegrationTestSetupHelper.indexInit(em);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	@Test
	@Transactional
	public void testWhenSearchWithFacetParameterThenCorrectResultIsReturned() throws Exception {

		mockMvc.perform(post("/api/Search/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/"
				+ Constants.defaultProductRootCategoryCode)

				.param("q", "fruit").param("page", "0").param("size", "10").param("sort", "nameAsc")
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\n" + "    \"type\": \"SearchFacet\",\n" + "    \"value\": \"/PRM01/FRT01/POM01\",\n"
						+ "    \"facetingName\": \"category\",\n" + "    \"desc\": \"Pomes\",\n"
						+ "    \"hierarchical\": false,\n"
						+ "    \"fieldName\": \"product.categories.categoryToken\",\n" + "    \"count\": 3,\n"
						+ "    \"id\": \"POM01\"\n" + "  }]"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(3)));

	}

	@Test
	@Transactional
	public void testWhenUserEntersSearchTextThenCorrectSuggestionsAreReturned() throws Exception {
		mockMvc.perform(get("/api/Search/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Suggest")
				.param("q", "app").with(csrf()).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("[\"Apple\"]")).andExpect(content().contentType("application/hal+json"));
	}

}
