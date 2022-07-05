package io.nzbee.integration.entity.category.brand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.integration.entity.beans.category.brand.IBrandCategoryEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {ConfigCategoryBrandEntityTests.class})
public class IT_BrandCategoryEntityRepositoryIntegrationTest {
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private IBrandCategoryEntityBeanFactory categoryEntityBeanFactory;
 
    @Autowired
    private ICategoryService categoryService;
    
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
 
	private static CategoryBrandEntity category = null;
	
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
		this.persistNewCategory();
		setUpIsDone = true;
	}
	
    
	public CategoryBrandEntity persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getBrandCategoryEntityBean();
	    	
	    //persist a new transient test category
		categoryService.save(category.getCategory());
	    
	    return category;
	}
   
    
    @Test
    @Rollback(false)
    public void whenFindById_thenReturnBrandCategory() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findById(category.getCategory().getCategoryId());
     
        // then
    	assertFound(found);
    }
    
    // write test cases here
    @Test
    @Rollback(false)
    public void whenFindByCode_thenReturnBrandCategory() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findByCode("TST03");
     
        // then
    	assertFound(found);
    }
    
    
    private void assertFound(Optional<CategoryEntity> found) {
    	
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getClass().getSimpleName())
    	.isEqualTo(CategoryEntity.class.getSimpleName());
    	
    	CategoryBrandEntity cb = found.get().getCategoryBrand();
    	
    	assertThat(cb.getCategory().getCategoryCode())
        .isEqualTo("TST03");
	    
	    assertThat(cb.getCategory().getCategoryDescENGB())
	    .isEqualTo("test brand category");
	    
	  
    }
    
 
}