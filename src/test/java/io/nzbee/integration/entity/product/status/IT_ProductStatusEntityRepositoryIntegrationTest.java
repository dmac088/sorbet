package io.nzbee.integration.entity.product.status;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.integration.entity.beans.product.status.IProductStatusEntityBeanFactory;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {ConfigProductStatusEntityTests.class})
public class IT_ProductStatusEntityRepositoryIntegrationTest {

	@Autowired
	private IProductStatusEntityBeanFactory productStatusEntityBeanFactory;

	@Autowired
	private IProductStatusRepository productStatusRepository;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private static ProductStatusEntity productStatus = null;

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
		productStatus = this.persistNewProductStatus();
		setUpIsDone = true;
	}

	public ProductStatusEntity persistNewProductStatus() {

		productStatus = productStatusEntityBeanFactory.getBean();

		// persist a new transient test product status
		productStatusRepository.save(productStatus);

		return productStatus;
	}

	@Test
	@Rollback(false)
	public void whenFindById_thenReturnProductStatus() {

		// when
		ProductStatusEntity found = productStatusRepository.findById(productStatus.getProductStatusId()).get();

		// then
		assertFound(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnProductStatus() {

		// when
		ProductStatusEntity found = productStatusRepository.findByProductStatusCode(productStatus.getCode()).get();

		// then
		assertFound(found);
	}

	private void assertFound(final ProductStatusEntity found) {

		assertThat(found.getCode()).isEqualTo("TST01");

		assertThat(found.getDesc()).isEqualTo("test product status");

	}

}
