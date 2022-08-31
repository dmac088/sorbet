package io.nzbee.integration.view.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.customer.dto.in.CustomerDTOIn;
import io.nzbee.integration.view.beans.customer.ICustomerViewBeanFactory;
import io.nzbee.view.customer.CustomerDTOOut;
import io.nzbee.view.ports.ICustomerPortService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = ConfigCustomerViewTests.class)
public class IT_CustomerDoServiceImplIntegrationTest {

	@Autowired
	private ICustomerPortService customerService;

	@Autowired
	private ICustomerViewBeanFactory customerViewBeanFactory;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private static boolean setUpIsDone = false;
	
	@Before
	@WithUserDetails(value = "admin")
	public void setUp() {
		if (setUpIsDone) {
			return;
		}
		try (Connection con = database.getConnection()) {
			ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/mochi_schema.sql"));
			ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/mochi_data.sql"));
			ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/security_schema.sql"));
			ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/security_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.persistNewCustomer();
		setUpIsDone = true;
	}
	
	public void persistNewCustomer() { 
		CustomerDTOIn customer = customerViewBeanFactory.getBean();
	    	
		customerService.save(customer, Constants.localeENGB);
	}
	
	@After
    public void clear() {
        SecurityContextHolder.clearContext();
    }
	
	@Test
	@Rollback(false)
	@WithUserDetails(value = "admin")
	public void whenFindCustomerByUsername_thenReturnCustomer() {
		
		// when
		CustomerDTOOut found = customerService.findByUsername("tst088");

		// then
		assertFound(found);
	}

	private void assertFound(final CustomerDTOOut found) {

		assertNotNull(found);
		
		assertThat(found.getCustomerId())
	    .isEqualTo("123456789");
		
	}
	
}
