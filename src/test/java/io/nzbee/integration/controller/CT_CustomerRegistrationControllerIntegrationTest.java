package io.nzbee.integration.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
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
import io.nzbee.cache.CacheConfiguration;
import io.nzbee.controllers.CustomerController;
import io.nzbee.domain.customer.CustomerDomainServiceImpl;
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
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonServiceImpl;
import io.nzbee.entity.party.type.PartyTypeServiceImpl;
import io.nzbee.entity.product.ProductEntityServiceImpl;
import io.nzbee.exceptions.handle.CustomControllerAdvice;
import io.nzbee.integration.entity.beans.party.IPartyEntityBeanFactory;
import io.nzbee.integration.entity.beans.party.PartyEntityBeanFactory;
import io.nzbee.resources.customer.CustomerResourceAssembler;
import io.nzbee.resources.customer.address.CustomerAddressResourceAssembler;
import io.nzbee.security.Encoders;
import io.nzbee.security.OAuth2AuthorizationServerConfig;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.User;
import io.nzbee.security.user.UserService;
import io.nzbee.security.user.role.IUserRoleRepository;
import io.nzbee.security.user.role.UserRoleServiceImpl;
import io.nzbee.security.user.verificationtoken.VerificationToken;
import io.nzbee.util.FileStorageProperties;
import io.nzbee.view.customer.CustomerDTOMapperImpl;
import io.nzbee.view.customer.CustomerViewServiceImpl;
import io.nzbee.view.customer.address.CustomerAddressDTOMapperImpl;

@RunWith(SpringRunner.class)
@EnableJpaRepositories(entityManagerFactoryRef = "mochiEntityManagerFactory", transactionManagerRef = "mochiTransactionManager", basePackages = {
		"io.nzbee.entity", "io.nzbee.security" })
@ContextConfiguration(classes = { PartyTypeServiceImpl.class, CustomerDomainServiceImpl.class, CustomerAdapter.class,
		PersonServiceImpl.class, CustomerMapperImpl.class, UserRoleServiceImpl.class, IUserRoleRepository.class,
		UserService.class, Encoders.class, SecurityBeanConfiguration.class, ProductEntityServiceImpl.class,
		WebSecurityConfig.class, Globals.class, SecurityBeanConfiguration.class, OAuth2AuthorizationServerConfig.class,
		OAuth2ResourceServerConfig.class, ConfigControllerTest.class, FileStorageProperties.class,
		CacheConfiguration.class, CustomControllerAdvice.class, AddressAdapter.class, PartyAddressViewServiceImpl.class,
		PartyServiceImpl.class, PartyDaoImpl.class, AddressTypeServiceImpl.class, CustomerResourceAssembler.class,
		CustomerAddressResourceAssembler.class, CustomerDTOMapperImpl.class, CustomerAddressDTOMapperImpl.class,
		CustomerController.class, CustomerViewServiceImpl.class, CustomerViewAdapter.class, CustomerDTOMapperImpl.class,
		CustomerViewMapperImpl.class, BagItemEntityServiceImpl.class, BagEntityServiceImpl.class,
		PartyAddressEntityServiceImpl.class, PartyEntityBeanFactory.class })
@WebMvcTest(CustomerController.class)
@Transactional
public class CT_CustomerRegistrationControllerIntegrationTest {

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	@Autowired
	private EntityManager em;

	@Autowired
	private IPersonService personService;

	@Autowired
	private IPartyEntityBeanFactory partyBeanFactory;

	private MockMvc mockMvc;
	private String token;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		// initialise the database
		IntegrationTestSetupHelper.dbInit(database);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@BeforeAll
	public void createNewUser() {
		User u = partyBeanFactory.getUserBean();
		
		personService.save(u.getParty().getPartyPerson());

		this.token = UUID.randomUUID().toString();

		VerificationToken verificationToken = new VerificationToken(token, u);
		verificationToken.setExpiryDate(Date.from(Instant.now().plus(2, ChronoUnit.DAYS)));

		verificationToken.setUser(u);

		em.persist(verificationToken);
		em.flush();
		em.clear();
	}

	@Test
	public void testRegistrationConfirm() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/registrationConfirmation?token=" + token).with(csrf())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.ALL)).andDo(print())
				.andExpect(status().isOk());

//        ResultActions resultActions = this.mockMvc.perform(get("/registrationConfirm?token=" + token));
//        resultActions.andExpect(status().is3xxRedirection());
//        resultActions.andExpect(model().attribute("messageKey", "message.accountVerified"));
//        resultActions.andExpect(view().name("redirect:/console"));
	}

}
