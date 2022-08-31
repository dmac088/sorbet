package io.nzbee.integration.entity.promotion.category;

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
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import io.nzbee.Constants;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.promotion.IPromotionEntityService;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.entity.promotion.disc.PromotionDiscEntity;
import io.nzbee.util.promotion.category.CategoryPromotionMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = ConfigCategoryPromotionEntityTests.class)

public class IT_CategoryPromotionMappingUploadForCreateIntegrationTest {

	@Autowired
	private CategoryPromotionMasterService pms;

	@Autowired
	private IPromotionEntityService promotionService;
	
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
    	this.loadPromotions();
        setUpIsDone = true;
	}

	public void loadPromotions() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeCategoryPromotionMaster(file.getAbsolutePath() + "/data/promotion/mapping/category/category_promotion_mapping.tsv");
	}

	@Test
	@Rollback(false)
	public void whenPromotionUploadedForCreate_thenReturnCorrectlyCreatedPromotion() {
		// when
		Optional<PromotionEntity> found = promotionService.findByCode("RB3G33");

		// then
		assertFound(found);
	}

	private void assertFound(Optional<PromotionEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		PromotionDiscEntity cp = (PromotionDiscEntity) found.get().getPromotionProduct();
		
		assertThat(cp.getCategories().size())
		.isEqualTo(2);
		
		assertTrue(cp.getCategories().stream().filter(p -> p.getCategoryCode().equals("CIT01")).findAny().isPresent());
		
		assertTrue(cp.getCategories().stream().filter(p -> p.getCategoryCode().equals("TRO01")).findAny().isPresent());
		
	}

}
