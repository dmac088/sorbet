package io.nzbee.integration.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
import io.nzbee.Globals;
import io.nzbee.payment.PaymentService;
import io.nzbee.security.OAuth2AuthorizationServerConfig;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.UserService;

@RunWith(SpringRunner.class)
@EnableJpaRepositories(entityManagerFactoryRef = "mochiEntityManagerFactory", transactionManagerRef = "mochiTransactionManager", basePackages = {
		"io.nzbee.entity", "io.nzbee.security" })
@ContextConfiguration(classes = {PaymentService.class,  ConfigControllerTest.class, SecurityBeanConfiguration.class, 
		OAuth2AuthorizationServerConfig.class,OAuth2ResourceServerConfig.class, UserService.class, WebSecurityConfig.class, Globals.class})
@WebMvcTest
public class CT_OauthTokenRenewal {

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		IntegrationTestSetupHelper.dbInit(database);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
								 .apply(springSecurity())
								 .build();
	}
	
	@Test
	public void OAuthTest()
			throws Exception {
		mockMvc.perform(post("/oauth/token")
				
				.header("Authorization", "Basic c3ByaW5nLXNlY3VyaXR5LW9hdXRoMi1yZWFkLXdyaXRlLWNsaWVudDpzcHJpbmctc2VjdXJpdHktb2F1dGgyLXJlYWQtd3JpdGUtY2xpZW50LXBhc3N3b3JkMTIzNA==")
				.content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                    new BasicNameValuePair("username", ConfigControllerTest.TEST_USERNAME),
                    new BasicNameValuePair("password", ConfigControllerTest.TEST_PASSWORD),
                    new BasicNameValuePair("client_id", "spring-security-oauth2-read-write-client"),
                    new BasicNameValuePair("grant_type", "password")
				))))
				.with(csrf())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.characterEncoding("utf-8")
				.accept(MediaType.ALL)).andDo(print())
				.andExpect(jsonPath("$.token_type", is("bearer")))
				.andExpect(jsonPath("$.authenticated", is(true)))
				.andExpect(status().isOk());
	}
	
}
