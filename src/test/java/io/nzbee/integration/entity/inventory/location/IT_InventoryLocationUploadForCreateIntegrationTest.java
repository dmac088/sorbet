package io.nzbee.integration.entity.inventory.location;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.inventory.location.IInventoryLocationService;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.util.inventory.InventoryLocationMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {ConfigInventoryLocationEntityTests.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_InventoryLocationUploadForCreateIntegrationTest {

	@Autowired
	private InventoryLocationMasterService pms;

	@Autowired
	private IInventoryLocationService inventoryLocationService;

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
		this.persistANewInventoryLocation();
		setUpIsDone = true;
	}
	
	public void persistANewInventoryLocation() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeInventoryLocation(file.getAbsolutePath() + "/data/inventory/location/create/inventory_location.tsv");
	}

	@Test
	public void whenInventoryLocationUploadedForCreate_thenReturnCorrectlyCreatedInventoryLocation_ENGB() {
		// when
		Optional<InventoryLocation> found = inventoryLocationService.findByCode("TST01");

		// then
		assertFound(found);
	}

	private void assertFound(Optional<InventoryLocation> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getLocationCode())
		.isEqualTo("TST01");
		
		assertThat(found.get().getLocationDesc())
		.isEqualTo("Test Create Location Description");
		
		assertThat(found.get().getLocationIsActive())
		.isEqualTo(true);
		
	}

}
