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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.WebMvcConfig;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.view.ProductCategoryAdapterImpl;
import io.nzbee.entity.category.CategoryServiceImpl;
import io.nzbee.entity.category.brand.CategoryBrandDaoImpl;
import io.nzbee.entity.category.brand.CategoryBrandServiceImpl;
import io.nzbee.entity.category.product.CategoryProductDaoImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTODaoImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTOMapperImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTOServiceImpl;
import io.nzbee.resources.category.facet.CategoryFacetMapper;
import io.nzbee.resources.category.facet.CategoryFacetModelAssembler;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.product.price.facet.PriceFacetMapper;
import io.nzbee.resources.product.price.facet.PriceFacetResourceAssembler;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.IUserRepository;
import io.nzbee.security.user.UserService;
import io.nzbee.util.FileStorageProperties;

import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.view.category.product.ProductCategoryViewServiceImpl;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc()
@ContextConfiguration(classes = {CategoryController.class, 
							     ProductCategoryAdapterImpl.class,
							     ProductCategoryViewServiceImpl.class,
							     CategoryProductDaoImpl.class,
							     CategoryBrandServiceImpl.class,
							     CategoryBrandDaoImpl.class,
							     CategoryFacetModelAssembler.class,  
							     CategoryFacetMapper.class,
							     CategoryFacetModelAssembler.class,
							     ProductCategoryFacetDTODaoImpl.class,
							     ProductCategoryFacetDTOMapperImpl.class,
							     PriceFacetMapper.class,
							     PriceFacetResourceAssembler.class,
							     SecurityBeanConfiguration.class,
							     Globals.class,
							     CategoryServiceImpl.class,
							     ProductCategoryFacetDTOServiceImpl.class,
							     DataSourceBeanMochi.class,
							     DataSourceBeanSecurity.class,
							     WebMvcConfig.class,
							     UserService.class,
							     IUserRepository.class,
							     OAuth2ResourceServerConfig.class,
							     FileStorageProperties.class,
							     WebSecurityConfig.class
							     })
@WebMvcTest(CategoryController.class)
public class CT_CategoryControllerIntegrationTest {
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void setUp() throws Exception {
    	IntegrationTestSetupHelper.dbInit(database);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    public void testFindAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Category/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
        		.andDo(print()).andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$._embedded.objects.length()", is(27)));
        
    }
    
    
    @Test
    public void testFindAllChildCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/Category/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Code/FRT01")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]")
                .accept(MediaType.ALL))
        		.andDo(print())
        		.andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$._embedded.objects.length()", is(7)))
        		
        		//check the first child category
        		.andExpect(jsonPath("$._embedded.objects[0].data.id").value("BER01"))
        		.andExpect(jsonPath("$._embedded.objects[0].data.parentId").value("FRT01"))
        		.andExpect(jsonPath("$._embedded.objects[0].data.desc").value("Berries"))
        		.andExpect(jsonPath("$._embedded.objects[0].data.count").value("2"))
        		.andExpect(jsonPath("$._embedded.objects[0].data.childCount").value("0"))
        		
        		.andExpect(jsonPath("$._embedded.objects[0]._links.products").exists())
        		.andExpect(jsonPath("$._embedded.objects[0]._links.tags").exists())
        		.andExpect(jsonPath("$._embedded.objects[0]._links.brands").exists())
        		.andExpect(jsonPath("$._embedded.objects[0]._links.children").exists())
        		
        		//check the last child category
        		.andExpect(jsonPath("$._embedded.objects[6].data.id").value("TRO01"))
        		.andExpect(jsonPath("$._embedded.objects[6].data.parentId").value("FRT01"))
        		.andExpect(jsonPath("$._embedded.objects[6].data.desc").value("Tropical"))
        		.andExpect(jsonPath("$._embedded.objects[6].data.count").value("2"))
        		.andExpect(jsonPath("$._embedded.objects[6].data.childCount").value("0"))
        		
        		.andExpect(jsonPath("$._embedded.objects[6]._links.products").exists())
        		.andExpect(jsonPath("$._embedded.objects[6]._links.tags").exists())
        		.andExpect(jsonPath("$._embedded.objects[6]._links.brands").exists())
        		.andExpect(jsonPath("$._embedded.objects[6]._links.children").exists());
    }
    

}
