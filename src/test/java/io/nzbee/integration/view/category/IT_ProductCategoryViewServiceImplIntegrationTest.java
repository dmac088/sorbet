package io.nzbee.integration.view.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.view.category.product.ProductCategoryView;
import io.nzbee.view.ports.ICategoryViewPortService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = ConfigCategoryViewTests.class)
public class IT_ProductCategoryViewServiceImplIntegrationTest {
	
	@Autowired
	private Globals globals;
	
	@Autowired
	private ICategoryViewPortService categoryService;
	
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
			ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/mochi_schema.sql"));
			ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/mochi_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setUpIsDone = true;
	}
	

	@Test
	@Rollback(false)
	public void whenFindAll_thenReturnAllCategories() {

		// when
		List<ProductCategoryView> found = categoryService.findAll(Constants.localeENGB, globals.getDefaultProductRootCategoryCode());

		// then
		assertFound(found);
	}

	private void assertFound(List<ProductCategoryView> found) {

		assertThat(found).isNotNull();
		
		assertThat(found).size().isEqualTo(27);
		
	}

}