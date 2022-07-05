package io.nzbee.integration.entity.party;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.role.customer.CustomerEntity;
import io.nzbee.integration.entity.beans.party.IPartyEntityBeanFactory;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {ConfigPartyEntityTests.class})
public class IT_PartyPersonEntityRepositoryIntegrationTest {
	
	//ensure the mochiEntityManagerFactory not the security EM
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	@Autowired
    private IPersonService personService;
	
	@Autowired
	private IPartyEntityBeanFactory partyEntityBeanFactory;
	
	private static PersonEntity customer = null;
	
	private static boolean setUpIsDone = false;
	
	@Before
	public void setUp() {
		if (setUpIsDone) {
			return;
		}
		try (Connection con = database.getConnection()) {
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/security_schema.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/security_data.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_schema.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.persistNewCustomer();
		setUpIsDone = true;
	}
	

    public PersonEntity persistNewCustomer() { 
		
		customer = partyEntityBeanFactory.getBean();
	    	
   	    entityManager.persist(customer);
   
   	    return customer;
    }
	
    //as long as the admin account can fetch the new user, we know that it was persisted properly by hibernate
	@Test
	@Rollback(false)
	@WithUserDetails(value = "admin")
    public void whenFindByUsernameAndRole_thenReturnParty() {
		
		// when
	    Optional<PersonEntity> found = personService.findByUsernameAndRole("mackdada", "Customer");
     
	    // then
	    assertFound(found);
	    	
    }

	
    private void assertFound(Optional<PersonEntity> found) {
    	assertNotNull(found);
    	assertTrue(found.isPresent());
    	
	    assertThat(((PersonEntity) found.get()).getGivenName())
	    .isEqualTo("Test Given Name");
	    assertThat(((PersonEntity) found.get()).getFamilyName())
	    .isEqualTo("Test Family Name");
	    assertThat(((PersonEntity) found.get()).getPersonParty().getUser().getUsername())
	    .isEqualTo("mackdada");
	    assertThat(((PersonEntity) found.get()).getPersonParty().getUser().getUserRoles().stream().findFirst().get().getName())
	    .isEqualTo("Customer");
	    assertThat(((CustomerEntity)(((PersonEntity) found.get()).getPersonParty().getPartyRoles().stream().findFirst().get().getRoleCustomer())).getCustomerNumber())
	    .isEqualTo("9832145731");
	    
    }
	
    
	
}
