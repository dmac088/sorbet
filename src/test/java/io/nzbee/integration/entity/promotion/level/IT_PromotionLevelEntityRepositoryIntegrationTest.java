package io.nzbee.integration.entity.promotion.level;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import io.nzbee.entity.promotion.level.IPromotionLevelService;
import io.nzbee.entity.promotion.level.PromotionLevelEntity;
import io.nzbee.integration.entity.beans.promotion.level.IPromotionLevelEntityBeanFactory;
import io.nzbee.integration.entity.promotion.product.ConfigProductPromotionEntityTests;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {ConfigPromotionLevelEntityTests.class, 
								 ConfigProductPromotionEntityTests.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_PromotionLevelEntityRepositoryIntegrationTest {

	@Autowired
	private IPromotionLevelEntityBeanFactory promotionLevelEntityBeanFactory;

	@Autowired
	private IPromotionLevelService promotionLevelService;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private static PromotionLevelEntity promotionLevel = null;

	private static boolean setUpIsDone = false;

	public void persistNewPromotionLevel() {

		promotionLevel = promotionLevelEntityBeanFactory.getBean();

		// persist a new transient test promotionLevel
		promotionLevelService.save(promotionLevel);
	}

	@Before
	public void persistANewPromotionLevel() {
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
		this.persistNewPromotionLevel();
		setUpIsDone = true;
	}

	@Test
	@Rollback(false)
	public void whenFindById_thenReturnPromotionLevelEntity() {

		// when
		Optional<PromotionLevelEntity> found = promotionLevelService.findById(promotionLevel.getPromotionLevelId());

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnPromotionLevelEntity() {

		// when
		Optional<PromotionLevelEntity> found = promotionLevelService.findByCode("TST01");

		// then
		assertFoundEntity(found);
	}
     
	private void assertFoundEntity(Optional<PromotionLevelEntity> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getPromotionLevelCode()).isEqualTo("TST01");
		assertThat(found.get().getPromotionLevelDesc()).isEqualTo("test promotion level");
	}


}
