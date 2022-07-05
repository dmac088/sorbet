package io.nzbee.integration.view;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.integration.view.beans.customer.ICustomerViewBeanFactory;
import io.nzbee.view.customer.CustomerDTOIn;
import io.nzbee.view.customer.CustomerDTOOut;
import io.nzbee.view.ports.ICustomerPortService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
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
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_schema.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_data.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/security_schema.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/security_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.persistNewCustomer();
		setUpIsDone = true;
	}
	
	public void persistNewCustomer() { 
		CustomerDTOIn customer = customerViewBeanFactory.getBean();
	    	
		customerService.save(customer);
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
