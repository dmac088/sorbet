package io.nzbee.integration.entity.promotion.mechanic;

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
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicEntity;
import io.nzbee.util.promotion.mechanic.PromotionMechanicMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = ConfigPromotionMechanicEntityTests.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_PromotionMechanicUploadForCreateIntegrationTest {

	@Autowired
	private PromotionMechanicMasterService pms;

	@Autowired
	private IPromotionMechanicService promotionMechanicService;

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
		this.loadPromotions();
		setUpIsDone = true;
	}
	
	public void loadPromotions() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writePromotionMechanicMaster(file.getAbsolutePath() + "/data/promotion/mechanic/create/promotion_mechanics.tsv");
	}

	@Test
	@Rollback(false)
	public void whenPromotionUploadedForCreate_thenReturnCorrectlyCreatedPromotion() {
		// when
		Optional<PromotionMechanicEntity> found = promotionMechanicService.findByCode("BNGNPCT");

		// then
		assertFound(found);
	}

	private void assertFound(Optional<PromotionMechanicEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		PromotionMechanicEntity cp = (PromotionMechanicEntity) found.get();
		
		assertThat(cp.getPromotionMechanicDesc())
		.isEqualTo("Buy N Get X Percent Off");
		
	}

}
