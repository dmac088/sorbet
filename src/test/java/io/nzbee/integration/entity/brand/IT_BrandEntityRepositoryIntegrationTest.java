package io.nzbee.integration.entity.brand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.brand.view.facet.BrandFacetDTO;
import io.nzbee.entity.brand.view.facet.IBrandFacetDTOService;
import io.nzbee.integration.entity.beans.brand.IBrandEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = { ConfigBrandEntityTests.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_BrandEntityRepositoryIntegrationTest {

	@Autowired
	private IBrandEntityBeanFactory brandEntityBeanFactory;

	@Autowired
	private IBrandService brandService;

	@Autowired
	private IBrandFacetDTOService brandFacetService;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private static BrandEntity brand = null;

	private static boolean setUpIsDone = false;

	@Before
	public void persistANewBrand() {
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
		this.persistNewBrand();
		setUpIsDone = true;
	}

	public void persistNewBrand() {

		brand = brandEntityBeanFactory.getBean();

		// persist a new transient test brand
		brandService.save(brand);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnBrandEntity() {

		// when
		Optional<BrandEntity> found = brandService.findByCode("TST02");

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnBrandDTOENGB() {

		// when
		Optional<BrandFacetDTO> found = brandFacetService.findByCode(Constants.localeENGB,
																	 Constants.primaryProductRootCategoryCode, 
																	 "DRI01");

		// then
		assertFoundDTO_ENGB(found);
	}
	
	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnBrandDTOZHHK() {

		// when
		Optional<BrandFacetDTO> found = brandFacetService.findByCode(Constants.localeZHHK,
																	 Constants.primaryProductRootCategoryCode, 
																	 "DRI01");

		// then
		assertFoundDTO_ZHHK(found);
	}

	@Test
	@Rollback(false)
	public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> tagCodes = new HashSet<String>();

		// when
		List<BrandFacetDTO> lb = brandFacetService.findAll(Constants.localeENGB, Constants.currencyUSD, "FRT01",
				new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(tagCodes), null);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(7);
	}

	@Test
	@Rollback(false)
	public void whenFindAllWithCategoryFacet_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> tagCodes = new HashSet<String>();

		categoryCodes.add("POM01");

		// when
		List<BrandFacetDTO> lb = brandFacetService.findAll(Constants.localeENGB, Constants.currencyUSD, "FRT01",
				new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(tagCodes), null);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(3);
	}

	@Test
	@Rollback(false)
	public void whenFindAllWithPriceFacetHKD_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> tagCodes = new HashSet<String>();

		Double price = Double.valueOf("32.4");

		// when
		List<BrandFacetDTO> lb = brandFacetService.findAll(Constants.localeENGB, Constants.currencyHKD, "FRT01",
				new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(tagCodes), price);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(3);
	}

	@Test
	@Rollback(false)
	public void whenFindAllWithPriceFacetUSDENGB_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> tagCodes = new HashSet<String>();

		Double price = Double.valueOf("4.15");

		// when
		List<BrandFacetDTO> lb = brandFacetService.findAll(Constants.localeENGB, Constants.currencyUSD, "FRT01",
				new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(tagCodes), price);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(2);

	}

	@Test
	@Rollback(false)
	public void whenFindAllWithPriceFacetUSDZHHK_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> tagCodes = new HashSet<String>();

		Double price = Double.valueOf("4.15");

		// when
		List<BrandFacetDTO> lb = brandFacetService.findAll(Constants.localeZHHK, Constants.currencyUSD, "FRT01",
				new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(tagCodes), price);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(2);

	}

	private void assertFoundEntity(Optional<BrandEntity> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getBrandCode()).isEqualTo("TST02");

		assertThat(found.get().getBrandDescENGB()).isEqualTo("test brand");

		assertThat(found.get().getBrandDescZHHK()).isEqualTo("測試品牌");

	}

	private void assertFoundDTO_ENGB(Optional<BrandFacetDTO> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getBrandCode()).isEqualTo("DRI01");

		assertThat(found.get().getBrandDesc()).isEqualTo("Driscolls");
	}

	private void assertFoundDTO_ZHHK(Optional<BrandFacetDTO> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getBrandCode()).isEqualTo("DRI01");

		assertThat(found.get().getBrandDesc()).isEqualTo("Driscolls");
	}

}