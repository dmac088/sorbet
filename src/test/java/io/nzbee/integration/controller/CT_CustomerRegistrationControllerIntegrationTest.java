package io.nzbee.integration.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;
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
import io.nzbee.ErrorKeys;
import io.nzbee.Globals;
import io.nzbee.cache.CacheConfiguration;
import io.nzbee.domain.customer.CustomerServiceImpl;
import io.nzbee.entity.adapters.domain.CustomerAdapter;
import io.nzbee.entity.adapters.view.AddressAdapter;
import io.nzbee.entity.adapters.view.CustomerViewAdapter;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.entity.BagEntityServiceImpl;
import io.nzbee.entity.bag.item.entity.BagItemEntityServiceImpl;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.PartyDaoImpl;
import io.nzbee.entity.party.PartyServiceImpl;
import io.nzbee.entity.party.address.PartyAddressViewServiceImpl;
import io.nzbee.entity.party.address.entity.PartyAddressEntity;
import io.nzbee.entity.party.address.entity.PartyAddressEntityServiceImpl;
import io.nzbee.entity.party.address.type.AddressTypeEntity;
import io.nzbee.entity.party.address.type.AddressTypeServiceImpl;
import io.nzbee.entity.party.address.type.IAddressTypeService;
import io.nzbee.entity.party.person.CustomerMapperImpl;
import io.nzbee.entity.party.person.CustomerViewMapperImpl;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.party.person.PersonServiceImpl;
import io.nzbee.entity.party.type.IPartyTypeService;
import io.nzbee.entity.party.type.PartyTypeEntity;
import io.nzbee.entity.party.type.PartyTypeServiceImpl;
import io.nzbee.entity.product.ProductEntityServiceImpl;
import io.nzbee.entity.role.IRoleTypeRepository;
import io.nzbee.entity.role.RoleEntity;
import io.nzbee.entity.role.RoleTypeEntity;
import io.nzbee.entity.role.customer.CustomerEntity;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.exceptions.handle.CustomControllerAdvice;
import io.nzbee.resources.controllers.CustomerController;
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
import io.nzbee.security.user.role.IUserRoleService;
import io.nzbee.security.user.role.UserRole;
import io.nzbee.security.user.role.UserRoleServiceImpl;
import io.nzbee.security.user.verificationtoken.VerificationToken;
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
		CustomerViewMapperImpl.class, BagItemEntityServiceImpl.class, BagEntityServiceImpl.class,
		PartyAddressEntityServiceImpl.class })
@WebMvcTest(CustomerController.class)
@Transactional
public class CT_CustomerRegistrationControllerIntegrationTest {

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	@Autowired
	private EntityManager em;

	@Autowired
	private IUserRoleService userRoleService;

	@Autowired
	private IPartyTypeService partyTypeService;

	@Autowired
	private IPersonService personService;

	@Autowired
	private IAddressTypeService addressTypeService;

	@Autowired
	private IRoleTypeRepository roleTypeRepository;

	private MockMvc mockMvc;
	private String token;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private String  TEST_NEW_USERNAME = "test01@test01";
	private String  TEST_NEW_PASSWORD = "test01";
	private Boolean TEST_ISENABLED = false;
	private String  TEST_CUSTOMERID = "74185293";
	private String  TEST_GIVENNAME = "Ron";
	private String  TEST_FAMILYNAME = "Moody";


	@Before
	public void setUp() {
		
		//initialise the database
		IntegrationTestSetupHelper.dbInit(database);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		
		//create a new user
		Optional<PartyTypeEntity> opte = partyTypeService.findByPartyTypeDesc(Constants.partyTypePerson);
		UserRole ur = userRoleService.findByName(Constants.partyRoleCustomer);
		User u = new User();
		u.addUserRole(ur);
		ur.addUser(u);
		u.setUsername(TEST_NEW_USERNAME);
		u.setPassword(TEST_NEW_PASSWORD);
		u.setEnabled(false);
		u.setUsing2FA(false);

		RoleEntity re = new RoleEntity();
		CustomerEntity c = new CustomerEntity();
		re.setRoleCustomer(c);
		c.setCustomerRole(re);
		c.setCustomerNumber(TEST_CUSTOMERID);
		re.setRoleStart(LocalDateTime.now());

		RoleTypeEntity roleType = roleTypeRepository.findByRoleTypeDesc(Constants.partyRoleCustomer).get();
		re.setRoleType(roleType);

		Party pty = new Party();
		pty.setPartyType(opte.get());
		pty.setUser(u);

		PersonEntity p = new PersonEntity();

		p.setPersonParty(pty);
		pty.setPartyPerson(p);

		p.setGivenName(TEST_GIVENNAME);
		p.setFamilyName(TEST_FAMILYNAME);
		p.setEnabled(TEST_ISENABLED);

		p.getPersonParty().setUser(u);
		u.setParty(p.getPersonParty());
		p.getPersonParty().addRole(re);
		re.setRoleParty(p.getPersonParty());

		// by default we create a new bag for the customer on sign-up
		BagEntity b = new BagEntity();
		b.setBagCreatedDateTime(LocalDateTime.now());
		b.setBagUpdatedDateTime(LocalDateTime.now());
		p.getPersonParty().setBag(b);
		b.setParty(p.getPersonParty());

		// we need to add some empty addresses just like we create an empty bag
		PartyAddressEntity ba = new PartyAddressEntity();
		PartyAddressEntity ma = new PartyAddressEntity();
		Optional<AddressTypeEntity> obat = addressTypeService.findByCode(Constants.billingAddressCode);
		Optional<AddressTypeEntity> omat = addressTypeService.findByCode(Constants.shippingAddressCode);

		AddressTypeEntity bat = obat.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.addressTypeNotFound,
				Constants.localeENGB, Constants.billingAddressCode));
		AddressTypeEntity mat = omat.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.addressTypeNotFound,
				Constants.localeENGB, Constants.shippingAddressCode));

		ba.setType(bat);
		ma.setType(mat);

		p.getPersonParty().addAddress(ba);
		p.getPersonParty().addAddress(ma);
		
		personService.save(p);
		
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
		mockMvc.perform(MockMvcRequestBuilders.get("/api/registrationConfirmation?token=" + token)
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.ALL)).andDo(print())
				.andExpect(status().isOk());

//        ResultActions resultActions = this.mockMvc.perform(get("/registrationConfirm?token=" + token));
//        resultActions.andExpect(status().is3xxRedirection());
//        resultActions.andExpect(model().attribute("messageKey", "message.accountVerified"));
//        resultActions.andExpect(view().name("redirect:/console"));
	}

}
