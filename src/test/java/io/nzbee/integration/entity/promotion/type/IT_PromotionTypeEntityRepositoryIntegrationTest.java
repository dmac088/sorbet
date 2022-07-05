package io.nzbee.integration.entity.promotion.type;

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
import io.nzbee.entity.promotion.type.IPromotionTypeService;
import io.nzbee.entity.promotion.type.PromotionTypeEntity;
import io.nzbee.integration.entity.beans.promotion.type.IPromotionTypeEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes= ConfigPromotionTypeEntityTests.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_PromotionTypeEntityRepositoryIntegrationTest {

	@Autowired
	private IPromotionTypeEntityBeanFactory promotionTypeEntityBeanFactory;

	@Autowired
	private IPromotionTypeService promotionTypeService;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private static PromotionTypeEntity promotionType = null;

	private static boolean setUpIsDone = false;

	public void persistNewPromotionType() {

		promotionType = promotionTypeEntityBeanFactory.getBean();

		// persist a new transient test promotionType
		promotionTypeService.save(promotionType);
	}

	@Before
	public void persistANewPromotionType() {
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
		this.persistNewPromotionType();
		setUpIsDone = true;
	}

	@Test
	@Rollback(false)
	public void whenFindById_thenReturnPromotionTypeEntity() {

		// when
		Optional<PromotionTypeEntity> found = promotionTypeService.findById(promotionType.getPromotionTypeId());

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnPromotionTypeEntity() {

		// when
		Optional<PromotionTypeEntity> found = promotionTypeService.findByCode("TST01");

		// then
		assertFoundEntity(found);
	}
     
	private void assertFoundEntity(Optional<PromotionTypeEntity> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getPromotionTypeCode()).isEqualTo("TST01");
		assertThat(found.get().getPromotionTypeDesc()).isEqualTo("test promotion type");
		assertThat(found.get().getPromotionClass()).isEqualTo("TestPromotionType");
	}


}
