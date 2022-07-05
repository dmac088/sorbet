package io.nzbee.integration.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.cache.CacheConfiguration;
import io.nzbee.domain.customer.CustomerServiceImpl;
import io.nzbee.entity.adapters.domain.CustomerAdapter;
import io.nzbee.entity.adapters.view.AddressAdapter;
import io.nzbee.entity.adapters.view.CustomerViewAdapter;
import io.nzbee.entity.bag.entity.BagEntityServiceImpl;
import io.nzbee.entity.bag.item.entity.BagItemEntityServiceImpl;
import io.nzbee.entity.party.PartyDaoImpl;
import io.nzbee.entity.party.PartyServiceImpl;
import io.nzbee.entity.party.address.PartyAddressViewServiceImpl;
import io.nzbee.entity.party.address.entity.PartyAddressEntityServiceImpl;
import io.nzbee.entity.party.address.type.AddressTypeServiceImpl;
import io.nzbee.entity.party.person.CustomerMapperImpl;
import io.nzbee.entity.party.person.CustomerViewMapperImpl;
import io.nzbee.entity.party.person.PersonServiceImpl;
import io.nzbee.entity.party.type.PartyTypeServiceImpl;
import io.nzbee.entity.product.ProductEntityServiceImpl;
import io.nzbee.exceptions.handle.CustomControllerAdvice;
import io.nzbee.resources.controllers.CustomerController;
import io.nzbee.resources.customer.CustomerResourceAssembler;
import io.nzbee.resources.customer.address.CustomerAddressResourceAssembler;
import io.nzbee.security.Encoders;
import io.nzbee.security.OAuth2AuthorizationServerConfig;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.UserService;
import io.nzbee.security.user.role.IUserRoleRepository;
import io.nzbee.security.user.role.UserRoleServiceImpl;
import io.nzbee.util.FileStorageProperties;
import io.nzbee.view.customer.CustomerDTOMapperImpl;
import io.nzbee.view.customer.CustomerViewServiceImpl;
import io.nzbee.view.customer.address.CustomerAddressDTOMapperImpl;

@RunWith(SpringRunner.class)
@EnableJpaRepositories(entityManagerFactoryRef = "mochiEntityManagerFactory", transactionManagerRef = "mochiTransactionManager", basePackages = {
		"io.nzbee.entity", "io.nzbee.security" })
@ContextConfiguration(classes = { PartyTypeServiceImpl.class, CustomerServiceImpl.class, CustomerAdapter.class,
		PersonServiceImpl.class, CustomerMapperImpl.class, UserRoleServiceImpl.class, IUserRoleRepository.class,
		UserService.class, Encoders.class, SecurityBeanConfiguration.class, ProductEntityServiceImpl.class, 
		WebSecurityConfig.class, Globals.class, SecurityBeanConfiguration.class, OAuth2AuthorizationServerConfig.class,
		OAuth2ResourceServerConfig.class, ConfigControllerTest.class, FileStorageProperties.class,
		CacheConfiguration.class, CustomControllerAdvice.class, AddressAdapter.class, PartyAddressViewServiceImpl.class,
		PartyServiceImpl.class, PartyDaoImpl.class, AddressTypeServiceImpl.class, CustomerResourceAssembler.class,
		CustomerAddressResourceAssembler.class, CustomerDTOMapperImpl.class, CustomerAddressDTOMapperImpl.class,
		CustomerController.class, CustomerViewServiceImpl.class, CustomerViewAdapter.class, CustomerDTOMapperImpl.class,
		CustomerViewMapperImpl.class, BagItemEntityServiceImpl.class, BagEntityServiceImpl.class, PartyAddressEntityServiceImpl.class})
@WebMvcTest(CustomerController.class)
public class CT_CustomerControllerIntegrationTest {

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
	public void testWhenGetUsernameThenTheCorrectUsernameIsReturned() throws Exception {
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/username").header("Authorization", "Bearer " + accessToken)
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.ALL)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(ConfigControllerTest.TEST_USERNAME));
		;
	}

	@Test
	public void testWhenRegistrationIsSuccessfulThenRegistrationConfirmationIsSuccess() throws Exception {
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);

		System.out.println("token = " + accessToken);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/registrationConfirmation")
				.header("Authorization", "Bearer " + accessToken).with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testWhenRequestCustomerThenCustomerIsReturned() throws Exception {
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/Customer").header("Authorization", "Bearer " + accessToken)
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.ALL)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testWhenGetCustomerBillingAddressThenCorrectCustomerBillingAddressIsReturned() throws Exception {
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/Customer/Address/" + Constants.billingAddressCode)
				.header("Authorization", "Bearer " + accessToken).with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.addressLine1").value("Test Line 1"))
				.andExpect(jsonPath("$.data.addressLine2").value("Test Line 2"))
				.andExpect(jsonPath("$.data.addressLine3").value("Test Line 3"))
				.andExpect(jsonPath("$.data.country").value("Test Ctry"))
				.andExpect(jsonPath("$.data.postCode").value("Test PC"))
				.andExpect(jsonPath("$.data.addressTypeCode").value("BIL01"))
				.andExpect(jsonPath("$.data.addressTypeDesc").value("Billing Address"));
	}

	@Test
	public void testWhenGetCustomerShippingAddressThenCorrectCustomerShippingAddressIsReturned() throws Exception {
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/Customer/Address/" + Constants.shippingAddressCode)
				.header("Authorization", "Bearer " + accessToken).with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.addressLine1").value("Test Line 1"))
				.andExpect(jsonPath("$.data.addressLine2").value("Test Line 2"))
				.andExpect(jsonPath("$.data.addressLine3").value("Test Line 3"))
				.andExpect(jsonPath("$.data.country").value("Test Ctry"))
				.andExpect(jsonPath("$.data.postCode").value("Test PC"))
				.andExpect(jsonPath("$.data.addressTypeCode").value("MAI01"))
				.andExpect(jsonPath("$.data.addressTypeDesc").value("Mailing Address"));
	}

	@Test
	public void testWhenUpdateCustomerAddressThenCustomerAddressIsSuccessfullyUpdated() throws Exception {
		String accessToken = ConfigControllerTest.obtainAccessToken(ConfigControllerTest.TEST_USERNAME,
				ConfigControllerTest.TEST_PASSWORD, webApplicationContext);

		mockMvc.perform(post("/api/Customer/Address/Update").header("Authorization", "Bearer " + accessToken)
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content("{\n" + "  \"addressLine1\": \"x\",\n" + "  \"addressLine2\": \"x\",\n"
						+ "  \"addressLine3\": \"x\",\n" + "  \"country\": \"x\",\n" + "  \"postCode\": \"x\",\n"
						+ "  \"addressTypeCode\": \"MAI01\"\n" + "}")
				.accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/Customer/Address/" + Constants.shippingAddressCode)
				.header("Authorization", "Bearer " + accessToken).with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.addressLine1").value("x"))
				.andExpect(jsonPath("$.data.addressLine2").value("x"))
				.andExpect(jsonPath("$.data.addressLine3").value("x")).andExpect(jsonPath("$.data.country").value("x"))
				.andExpect(jsonPath("$.data.postCode").value("x"))
				.andExpect(jsonPath("$.data.addressTypeCode").value("MAI01"))
				.andExpect(jsonPath("$.data.addressTypeDesc").value("Mailing Address"));
		
		//reset the mailing address 
		mockMvc.perform(post("/api/Customer/Address/Update").header("Authorization", "Bearer " + accessToken)
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content("{\n"
						+ "\"addressLine1\": \"Test Line 1\", \n"
						+ "\"addressLine2\": \"Test Line 2\",\n"
						+ "\"addressLine3\": \"Test Line 3\", \n"
						+ "\"country\": \"Test Ctry\",\n"
						+ "\"postCode\": \"Test PC\",\n"
						+ "\"addressTypeCode\": \"MAI01\"\n"
						+ "}")
				.accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testWhenNewCustomerIsSubmittedAndCustomerAlreadyExistsThenExceptionResponse409IsReturned()
			throws Exception {
		mockMvc.perform(post("/api/Customer/Signup").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content("{"+ "\"givenName\": \"Daniel\","
						+ "\"familyName\": \"Mackie\"," + "\"userName\": \"dmac1119\"," + "\"password\": \"1234567\","
						+ "\"confirmPassword\": \"1234567\"," + "\"partyType\": \"Person\"," + "\"enabled\": \"true\""
						+ "}")
				.accept(MediaType.ALL)).andDo(print()).andExpect(status().isConflict())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testWhenNewCustomerIsSubmittedAThenCustomerIsSuccessfullyCreated() throws Exception {
		mockMvc.perform(post("/api/Customer/Signup").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content("{"+ "\"givenName\": \"Daniel\","
						+ "\"familyName\": \"Mackie\"," + "\"userName\": \"dmac1124\"," + "\"password\": \"1234567\","
						+ "\"confirmPassword\": \"1234567\"	"
						+ "}")
				.accept(MediaType.ALL)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

}
