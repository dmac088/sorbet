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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.integration.view.beans.customer.ICustomerViewBeanFactory;
import io.nzbee.integration.view.beans.customer.address.ICustomerAddressViewBeanFactory;
import io.nzbee.view.customer.address.CustomerAddressDTOIn;
import io.nzbee.view.customer.address.CustomerAddressDTOOut;
import io.nzbee.view.ports.ICustomerAddressPortService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_CustomerAddressDoServiceImplIntegrationTest {

	private static String TEST_USERNAME = "bob@bob";
	private static String TEST_ADDRESS_TYPE = "BIL01";
	
	@Autowired
	private ICustomerAddressPortService customerAddressService;

	@Autowired
	private ICustomerAddressViewBeanFactory customerAddressViewBeanFactory;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	@MockBean
	private ICustomerViewBeanFactory customerViewBeanFactory;
	
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.persistNewAddress();
		setUpIsDone = true;
	}
	
	public void persistNewAddress() { 
		CustomerAddressDTOIn address = customerAddressViewBeanFactory.getBean();
	    	
		customerAddressService.save(address, TEST_USERNAME);
	}
	
	@After
    public void clear() {
        SecurityContextHolder.clearContext();
    }
	
	@Test
	@WithUserDetails(value = "admin")
	@Rollback(false)
	public void whenFindCustomerAddressByUsername_thenReturnCustomerAddress() {
		
		// when
		CustomerAddressDTOOut found = customerAddressService.findByUsernameAndType(TEST_USERNAME, TEST_ADDRESS_TYPE);

		// then
		assertFound(found);
	}

	private void assertFound(CustomerAddressDTOOut found) {

		assertNotNull(found);
		
		assertThat(found.getAddressLine1())
	    .isEqualTo("Test Line 1");
		
		assertThat(found.getAddressLine2())
	    .isEqualTo("Test Line 2");
		
		assertThat(found.getAddressLine3())
	    .isEqualTo("Test Line 3");
		
		assertThat(found.getCountry())
	    .isEqualTo("Test Country");
		
		assertThat(found.getPostCode())
	    .isEqualTo("Test PC");
		
		assertThat(found.getAddressTypeCode())
	    .isEqualTo("BIL01");
		
		assertThat(found.getAddressTypeDesc())
	    .isEqualTo("Billing Address");
		
//		assertThat(found.getCustomer().getUserName())
//	    .isEqualTo("bob@bob");
		
	}
	
}
