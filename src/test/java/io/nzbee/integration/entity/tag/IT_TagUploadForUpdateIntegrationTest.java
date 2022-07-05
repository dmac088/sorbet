package io.nzbee.integration.entity.tag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import javax.persistence.EntityManager;
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
import io.nzbee.entity.tag.view.facet.ITagFacetDTOService;
import io.nzbee.entity.tag.view.facet.TagFacetDTO;
import io.nzbee.util.tag.TagMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {ConfigTagEntityTests.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_TagUploadForUpdateIntegrationTest {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private TagMasterService pms;

	@Autowired
	private ITagFacetDTOService tagService;
	
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
    	this.uploadTags();
        setUpIsDone = true;
	}

	public void uploadTags() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeTagMaster(file.getAbsolutePath() + "/data/product/tag/update/tag_master.tsv");
	}

	@Test
	@Rollback(false)
	public void whenTagUploadedForUpdate_thenReturnCorrectlyUpdatedTag_ENGB() {
		// when
		Optional<TagFacetDTO> found = tagService.findByCode(Constants.localeENGB, Constants.primaryProductRootCategoryCode, "GFR01");

		// then
		assertFound_ENGB(found);
	}

	@Test
	@Rollback(false)
	public void whenTagUploadedForUpdate_thenReturnCorrectlyUpdatedTag_ZHHK() {
		// when
		Optional<TagFacetDTO> found = tagService.findByCode(Constants.localeZHHK, Constants.primaryProductRootCategoryCode, "GFR01");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<TagFacetDTO> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getTagDesc())
		.isEqualTo("Gluten Free Test");
		
	}

	private void assertFound_ZHHK(Optional<TagFacetDTO> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getTagDesc())
		.isEqualTo("無麩質測試");
	}

}
