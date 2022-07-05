package io.nzbee.integration.entity.category.product;

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

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.integration.entity.category.ConfigCategoryEntityTests;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.util.category.CategoryMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {ConfigCategoryEntityTests.class})
public class IT_ProductCategoryUploadForUpdateIntegrationTest {

	@Autowired
	private CategoryMasterService pms;

	@Autowired
	private ICategoryService categoryService;
	
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
    	this.createCategory();
        setUpIsDone = true;
	}

	
	public void createCategory() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeCategoryMaster(file.getAbsolutePath() + "/data/product/category/update/category_master.tsv");
	}

	@Test
	@Rollback(false)
	public void whenCategoryUploadedForUpdate_thenReturnCorrectlyUpdatedCategory_ENGB() {
		// when
		Optional<CategoryEntity> found = categoryService.findByCode("FET01");

		// then
		assertFound_ENGB(found);
	}

	@Test
	@Rollback(false)
	public void whenCategoryUploadedForUpdate_thenReturnCorrectlyUpdatedCategory_ZHHK() {
		// when
		Optional<CategoryEntity> found = categoryService.findByCode( "FET01");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<CategoryEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		CategoryProductEntity cp = (CategoryProductEntity) found.get().getCategoryProduct();
		
		assertThat(cp.getCategory().getCategoryDescENGB())
		.isEqualTo("Featured Test");
		
		assertThat(cp.getCategory().getCategoryLevel())
		.isEqualTo(2);
		
		assertThat(cp.getCategory().getCategoryParentCode())
		.isEqualTo("PRM01");
	}

	private void assertFound_ZHHK(Optional<CategoryEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		CategoryProductEntity cp = (CategoryProductEntity) found.get().getCategoryProduct();
		
		assertThat(cp.getCategory().getCategoryDescZHHK())
		.isEqualTo("特色測試");
		
		assertThat(cp.getCategory().getCategoryLevel())
		.isEqualTo(2);
		
		assertThat(cp.getCategory().getCategoryParentCode())
		.isEqualTo("PRM01");
	}

}
