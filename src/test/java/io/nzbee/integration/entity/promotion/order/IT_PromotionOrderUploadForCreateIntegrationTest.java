package io.nzbee.integration.entity.promotion.order;

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
import io.nzbee.Constants;
import io.nzbee.entity.promotion.IPromotionEntityService;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.util.promotion.order.PromotionOrderMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {ConfigOrderPromotionEntityTests.class})
public class IT_PromotionOrderUploadForCreateIntegrationTest {

	@Autowired
	private PromotionOrderMasterService pms;

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

		pms.writePromotionCouponMaster(file.getAbsolutePath() + "/data/promotion/coupon/create/promotion.tsv");
	}

	@Test
	@Rollback(false)
	public void whenPromotionUploadedForCreate_thenReturnCorrectlyCreatedPromotion_ENGB() {
		// when
		Optional<PromotionEntity> found = promotionService.findByCode("C10PCT");

		// then
		assertFound_ENGB(found);
	}

	@Test
	@Rollback(false)
	public void whenPromotionUploadedForCreate_thenReturnCorrectlyCreatedPromotion_ZHHK() {
		// when
		Optional<PromotionEntity> found = promotionService.findByCode("C10PCT");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<PromotionEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		PromotionEntity cp = (PromotionEntity) found.get();
		
		assertThat(cp.getPromotionCode())
		.isEqualTo("C10PCT");
		
		assertThat(cp.getAttributes().size()).isEqualTo(2);
		
		assertThat(cp.getAttributes().stream().filter(a -> a.getLocale().equals(Constants.localeENGB)).findAny().get().getPromotionDesc())
		.isEqualTo("10% off total basket");
		
	}

	private void assertFound_ZHHK(Optional<PromotionEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		PromotionEntity cp = (PromotionEntity) found.get();
		
		assertThat(cp.getPromotionCode())
		.isEqualTo("C10PCT");
		
		assertThat(cp.getAttributes().size()).isEqualTo(2);
		
		assertThat(cp.getAttributes().stream().filter(a -> a.getLocale().equals(Constants.localeZHHK)).findAny().get().getPromotionDesc())
		.isEqualTo("總籃可享10％的折扣");
		
	}

}
