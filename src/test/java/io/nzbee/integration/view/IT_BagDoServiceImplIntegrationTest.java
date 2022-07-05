package io.nzbee.integration.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.integration.view.beans.bag.IBagViewBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_BagDoServiceImplIntegrationTest {

	@Autowired
	private IBagPortService bagService;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IBagViewBeanFactory bagViewBeanFactory;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	private static boolean setUpIsDone = false;
	
	@Before
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
		this.persistNewBag();
		setUpIsDone = true;
	}
	
	public void persistNewBag() {

		Customer c = customerService.findByUsername(Constants.localeENGB, "bob@bob");

		Bag bag = bagViewBeanFactory.getBean(c);
		
//		bag.addItem(bagItemService.findByCode(Constants.localeENGB, Constants.currencyHKD, "23464789"), 2);
//		bag.addItem(bagItemService.findByCode(Constants.localeENGB, Constants.currencyHKD, "12345678"), 3);
//		bag.addItem(bagItemService.findByCode(Constants.localeENGB, Constants.currencyHKD, "12345678"), 3);
//		
		bag.addItem(new RegularBagItem(new BagItem(bag, "17235347", 0, new BigDecimal(10)), new BigDecimal(1), true),1);
		
		bagService.save(bag);
	}

	@Test
	@Rollback(false)
	@WithUserDetails(value = "admin")
	public void whenValidCode_thenBagShouldBeFound() {
		Bag found = bagService.findByCode(Constants.localeENGB, Constants.currencyHKD, "bob@bob");

		assertFound(found);
	}

	private void assertFound(Bag found) {
		
		assertNotNull(found);
		
		assertTrue(!found.getBagItems().isEmpty());

		assertThat(found.getBagItems().size()).isEqualTo(3);
		
		assertThat(found.getTotalQuantity()).isEqualTo(9);

	}
}
