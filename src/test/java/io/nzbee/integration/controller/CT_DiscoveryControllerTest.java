package io.nzbee.integration.controller;

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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Globals;
import io.nzbee.resources.controllers.DiscoveryController;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;

@RunWith(SpringRunner.class)
@EnableJpaRepositories(entityManagerFactoryRef = "mochiEntityManagerFactory", 
					   transactionManagerRef = "mochiTransactionManager", 
					   basePackages = {"io.nzbee.entity", "io.nzbee.security" })
@ContextConfiguration(classes = {DiscoveryController.class, 
								 Globals.class,
								 SecurityBeanConfiguration.class,
								 Globals.class, 
								 OAuth2ResourceServerConfig.class,  
								 ConfigControllerTest.class})
@WebMvcTest()
public class CT_DiscoveryControllerTest {

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
    public void testDiscoveryEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Discovery")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
        		.andDo(print()).andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$._links.getAllCategories").exists())
        		.andExpect(jsonPath("$._links.getCustomer").exists())
        		.andExpect(jsonPath("$._links.registerCustomer").exists())
        		.andExpect(jsonPath("$._links.getProduct").exists())
        		.andExpect(jsonPath("$._links.getShippingProviders").exists())
        		.andExpect(jsonPath("$._links.getShippingDestinations").exists())
        		.andExpect(jsonPath("$._links.searchProduct").exists())
        		.andExpect(jsonPath("$._links.searchSuggestion").exists())
        		.andExpect(jsonPath("$._links.getBag").exists())
        		.andExpect(jsonPath("$._links.accessTokens").exists());
    }
}
