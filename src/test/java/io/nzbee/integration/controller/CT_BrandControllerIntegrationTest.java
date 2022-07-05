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
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.WebMvcConfig;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.view.BrandFacetAdapterImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTODaoImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOMapperImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOServiceImpl;
import io.nzbee.resources.brand.facet.BrandFacetMapper;
import io.nzbee.resources.brand.facet.BrandFacetModelAssembler;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.IUserRepository;
import io.nzbee.security.user.UserService;
import io.nzbee.view.product.brand.facet.BrandFacetViewServiceImpl;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc()
@ContextConfiguration(classes = {BrandController.class,
								 BrandFacetViewServiceImpl.class,
								 BrandFacetAdapterImpl.class,
								 BrandFacetDTOServiceImpl.class,
								 BrandFacetDTODaoImpl.class,
								 BrandFacetDTOMapperImpl.class,
								 BrandFacetMapper.class,
								 DataSourceBeanMochi.class,
							     DataSourceBeanSecurity.class,
							     WebMvcConfig.class,
							     UserService.class,
							     Globals.class,
							     BrandFacetModelAssembler.class,
							     BrandFacetModelAssembler.class,
							     SecurityBeanConfiguration.class,
							     DataSourceBeanMochi.class,
							     DataSourceBeanSecurity.class,
							     WebMvcConfig.class,
							     UserService.class,
							     IUserRepository.class,
							     OAuth2ResourceServerConfig.class})
@WebMvcTest(BrandController.class)
@Import(WebSecurityConfig.class)
public class CT_BrandControllerIntegrationTest {

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
    @Autowired
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
    @Transactional
    public void testFindAllBrandViews() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.post("/api/Brand/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/FRT01")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]")
                .accept(MediaType.ALL))
        		.andDo(print()).andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$._embedded.objects.length()", is(7)))
    			
        		
        		.andExpect(jsonPath("$._embedded.objects[0].data.type").value("EntityFacet"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.desc").value("Adora"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.facetingName").value("brand"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.objectType").value("BrandFacetView"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.value").value("ADO01"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.count").value("2"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.id").value("ADO01"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.hierarchical").value(false))
        		
				.andExpect(jsonPath("$._embedded.objects[6].data.type").value("EntityFacet"))
				.andExpect(jsonPath("$._embedded.objects[6].data.desc").value("Shine"))
				.andExpect(jsonPath("$._embedded.objects[6].data.facetingName").value("brand"))
				.andExpect(jsonPath("$._embedded.objects[6].data.objectType").value("BrandFacetView"))
				.andExpect(jsonPath("$._embedded.objects[6].data.value").value("SHI01"))
				.andExpect(jsonPath("$._embedded.objects[6].data.count").value("2"))
				.andExpect(jsonPath("$._embedded.objects[6].data.id").value("SHI01"))
				.andExpect(jsonPath("$._embedded.objects[6].data.hierarchical").value(false));
    }
    
    @Test
    @Transactional
    public void testFindAllBrandFacets() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.post("/api/Brand/Facet/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/FRT01")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]")
                .accept(MediaType.ALL))
        		.andDo(print()).andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$._embedded.objects.length()", is(7)))
    	
    			.andExpect(jsonPath("$._embedded.objects[0].data.type").value("EntityFacet"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.desc").value("Adora"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.facetingName").value("brand"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.objectType").value("BrandFacetView"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.value").value("ADO01"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.count").value("2"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.id").value("ADO01"))
    			.andExpect(jsonPath("$._embedded.objects[0].data.hierarchical").value(false))
    	
				.andExpect(jsonPath("$._embedded.objects[6].data.type").value("EntityFacet"))
				.andExpect(jsonPath("$._embedded.objects[6].data.desc").value("Shine"))
				.andExpect(jsonPath("$._embedded.objects[6].data.facetingName").value("brand"))
				.andExpect(jsonPath("$._embedded.objects[6].data.objectType").value("BrandFacetView"))
				.andExpect(jsonPath("$._embedded.objects[6].data.value").value("SHI01"))
				.andExpect(jsonPath("$._embedded.objects[6].data.count").value("2"))
				.andExpect(jsonPath("$._embedded.objects[6].data.id").value("SHI01"))
				.andExpect(jsonPath("$._embedded.objects[6].data.hierarchical").value(false));
    }
    
	
}
