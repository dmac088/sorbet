package io.nzbee.integration.entity.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
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
import io.nzbee.Constants;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.physical.entity.IPhysicalProductEntityService;
import io.nzbee.entity.product.physical.view.IPhysicalProductDTOService;
import io.nzbee.entity.product.physical.view.PhysicalProductDTO;
import io.nzbee.integration.entity.beans.product.physical.IPhysicalProductEntityBeanFactory;
import io.nzbee.integration.entity.product.attribute.ConfigProductAttributeEntityTests;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {ConfigProductAttributeEntityTests.class})
public class IT_ProductAttributeEntityRepositoryIntegrationTest {
	
	@Autowired
    @Qualifier("mochiDataSourceOwner")
    private DataSource database;

	@Autowired
	private IPhysicalProductEntityBeanFactory productEntityBeanFactory;
 
    @Autowired
    private IPhysicalProductDTOService productViewService;
    
    @Autowired
    private IPhysicalProductEntityService productEntityService;
    
    private static ProductEntity product = null;

    private static boolean setUpIsDone = false;
    
	public ProductEntity persistNewProduct() {
    	
		product = productEntityBeanFactory.getBean();
	    
		productEntityService.save(product);
	    	
	    return product;
	}
	
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
    	this.persistNewProduct();
        setUpIsDone = true;
	}
	
	@Test
	@Rollback(false)
	public void whenFindById_thenReturnProduct() {
		 // when
    	PhysicalProductDTO found = productViewService.findById(  Constants.localeENGB, 
												  	 Constants.currencyHKD,  
												  	 product.getProductId()).get();
     
        // then
    	assertFound(found);
	}
	
	
	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnProduct() {
		 // when
		PhysicalProductDTO found = productViewService.findByCode(Constants.localeENGB, 
				  								  Constants.currencyHKD,  
												  "123456789").get();
     
        // then
    	assertFound(found);
	}
	 
    private void assertFound(final PhysicalProductDTO found) {
    	assertNotNull(found);
    	
    	assertThat(found.getProductDto().getProductUPC())
        .isEqualTo("123456789");
    	
    	assertNotNull(found.getProductDto().getCategories());
    	
    	assertThat(found.getProductDto().getCategories().stream().filter(f -> f.getCategoryCode().equals("POM01")).findFirst().isPresent())
    	.isTrue();
    	
    	assertThat(found.getProductDto().getDepartment().getDepartmentCode())
    	.isEqualTo("ACC01");
    	
    	assertThat(found.getProductDto().getProductStatusCode())
    	.isEqualTo("ACT01");
    	
//    	assertThat(found.getBrand().getBrandCode())
//    	.isEqualTo("PLA01");
    	
    	assertThat(found.getProductDto().getProductDesc())
    	.isEqualTo("test product");
    }
  
    
}
