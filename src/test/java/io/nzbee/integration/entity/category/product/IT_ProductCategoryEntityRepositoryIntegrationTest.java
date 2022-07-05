package io.nzbee.integration.entity.category.product;

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
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.category.product.view.facet.IProductCategoryFacetDTOService;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTO;
import io.nzbee.integration.entity.beans.category.product.IProductCategoryEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {ConfigCategoryProductEntityTests.class})
public class IT_ProductCategoryEntityRepositoryIntegrationTest {
	
	@Autowired
	private IProductCategoryEntityBeanFactory categoryEntityBeanFactory;
 
    @Autowired
    private ICategoryService categoryService;
    
    @Autowired
    private IProductCategoryFacetDTOService categoryFacetService;
    
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
   
    private static CategoryProductEntity category = null;
    
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
   
    
	public CategoryProductEntity persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getProductCategoryEntityBean();
	    
	    //persist a new transient test category
		categoryService.save(category.getCategory());
	    	
	    return category;
	}
   
    
    @Test
    @Rollback(false)
    public void whenFindById_thenReturnProductCategoryEntity() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findById(category.getCategory().getCategoryId());
     
        // then
    	assertFoundEntity(found);
    }
    
    @Test
    @Rollback(false)
    public void whenFindByCode_thenReturnProductCategoryEntity() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findByCode("TST02");
     
        // then
    	assertFoundEntity(found);
    }
    
    @Test
    @Rollback(false)
    public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	Set<String> tags = new HashSet<String>();
    	
    	//when
    	List<ProductCategoryFacetDTO> lc = categoryFacetService.findAll(	Constants.localeENGB, 
							    											Constants.currencyUSD, 
							    											"FRT01",
							    											new StringCollectionWrapper(categories),
							    											new StringCollectionWrapper(brands), 
							    											new StringCollectionWrapper(tags), 
							    											null);

        //then
    	assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(7);
    }
    
    @Test
    @Rollback(false)
    public void whenFindAllWithBrandFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	brands.add("ENZ01");
    	Set<String> tags = new HashSet<String>();
    	
    	//when
    	List<ProductCategoryFacetDTO> lc = categoryFacetService.findAll(	Constants.localeENGB, 
					    													Constants.currencyUSD, 
					    													"FRT01", 
					    													new StringCollectionWrapper(categories),
					    													new StringCollectionWrapper(brands), 
					    													new StringCollectionWrapper(tags), 
					    													null);
     
        //then
    	assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(1);
    }
    
    @Test
    @Rollback(false)
    public void whenFindAllWithTagFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	Set<String> tags = new HashSet<String>();
    	tags.add("GFR01");
    	
    	//when
    	List<ProductCategoryFacetDTO> lc = categoryFacetService.findAll(	Constants.localeENGB, 
						    												Constants.currencyUSD, 
						    												"FRT01", 
						    												new StringCollectionWrapper(categories),
						    												new StringCollectionWrapper(brands), 
						    												new StringCollectionWrapper(tags), 
						    												null);
     
        //then
		assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(1);
    }
    
  
    private void assertFoundEntity(Optional<CategoryEntity> found) {
    	
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	CategoryProductEntity cp = (CategoryProductEntity) found.get().getCategoryProduct();
    	
    	assertThat(cp.getCategory().getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(cp.getCategory().getCategoryLevel())
	    .isEqualTo(Long.valueOf(1));
	    
	    assertThat(cp.getCategory().getCategoryDescENGB())
	    .isEqualTo("test product category");
    }
    
 
}