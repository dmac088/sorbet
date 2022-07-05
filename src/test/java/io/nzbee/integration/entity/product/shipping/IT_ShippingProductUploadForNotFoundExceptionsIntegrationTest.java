package io.nzbee.integration.entity.product.shipping;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.util.product.shipping.ShippingProductMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {ConfigShippingProductEntityTests.class})
public class IT_ShippingProductUploadForNotFoundExceptionsIntegrationTest {
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
    @Autowired
    private ShippingProductMasterService pms;
    
	private static boolean setUpIsDone = false;
	
	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
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
		setUpIsDone = true;
	}
	
	@Test
	public void whenProductUploadedForMissingBrand_thenMissingBrandExceptionIsRaised() {
		exceptionRule.expect(EntityNotFoundException.class);
		exceptionRule.expectMessage("Brand not found: ZZZ99");
		
		 // when
		String path = "src/test/resources";
		File file = new File(path);
	
		pms.writeShippingProductMaster(file.getAbsolutePath() + "/data/product/shipping/exceptions/brandNotFound/shipping_product_master.tsv");

	}
	
}
