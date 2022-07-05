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
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.WebMvcConfig;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.view.TagFacetAdapterImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOMapperImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOPostgresDaoImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOServiceImpl;
import io.nzbee.resources.tag.facet.TagFacetMapperImpl;
import io.nzbee.resources.tag.facet.TagFacetModelAssembler;
import io.nzbee.resources.controllers.TagController;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.IUserRepository;
import io.nzbee.security.user.UserService;
import io.nzbee.view.product.tag.facet.TagFacetViewServiceImpl;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc()
@ContextConfiguration(classes = {TagController.class,
								 TagFacetViewServiceImpl.class,
								 TagFacetAdapterImpl.class,
								 TagFacetDTOServiceImpl.class,
								 TagFacetDTOPostgresDaoImpl.class,
								 TagFacetDTOMapperImpl.class,
								 TagFacetMapperImpl.class,
								 DataSourceBeanMochi.class,
							     DataSourceBeanSecurity.class,
							     WebMvcConfig.class,
							     UserService.class,
							     Globals.class,
							     TagFacetModelAssembler.class,
							     SecurityBeanConfiguration.class,
							     DataSourceBeanMochi.class,
							     DataSourceBeanSecurity.class,
							     WebMvcConfig.class,
							     UserService.class,
							     IUserRepository.class,
							     OAuth2ResourceServerConfig.class})
@WebMvcTest(TagController.class)
@Import(WebSecurityConfig.class)

public class CT_TagControllerIntegrationTest {

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
    public void testFindAllTagFacets() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.post("/api/Tag/Facet/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/FRT01")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]")
                .accept(MediaType.ALL))
        		.andDo(print()).andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$._embedded.objects.length()", is(2)))
    	
				.andExpect(jsonPath("$._embedded.objects[0].data.id").value("GFR01"))
				.andExpect(jsonPath("$._embedded.objects[0].data.desc").value("Gluten Free Test"))
				.andExpect(jsonPath("$._embedded.objects[0].data.count").value("1"))
		    	
				.andExpect(jsonPath("$._embedded.objects[1].data.id").value("ORG01"))
				.andExpect(jsonPath("$._embedded.objects[1].data.desc").value("ORGANIC"))
				.andExpect(jsonPath("$._embedded.objects[1].data.count").value("1"));
    }
    
	
}
