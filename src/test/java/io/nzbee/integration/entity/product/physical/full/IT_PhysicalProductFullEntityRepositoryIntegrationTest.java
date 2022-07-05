package io.nzbee.integration.entity.product.physical.full;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
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
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.physical.full.IPhysicalProductFullDTOService;
import io.nzbee.entity.product.physical.full.PhysicalProductFullDTO;
import io.nzbee.integration.entity.beans.product.physical.IPhysicalProductEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {ConfigPhysicalProductFullEntityTests.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_PhysicalProductFullEntityRepositoryIntegrationTest {
	
	@Autowired
	private IPhysicalProductEntityBeanFactory productEntityBeanFactory;
 
    @Autowired
    private IProductService productService;
    
    @Autowired
    private IPhysicalProductFullDTOService physicalProductFullService;

    @Autowired
    @Qualifier("mochiDataSourceOwner")
    private DataSource database;
    
    private static ProductEntity product = null;

    private static boolean setUpIsDone = false;
    
    
	public ProductEntity persistNewProduct() {
    	
		product = productEntityBeanFactory.getBean();
	    
		productService.save(product);
	    	
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
	public void whenFindByProductCode_thenReturnPhysicalProductFullDTOs() {
		
		Set<String> codes = new HashSet<String>();
		codes.add(product.getProductUPC());
		
		 // when
    	Optional<PhysicalProductFullDTO> found = physicalProductFullService.findByCode(Constants.localeENGB, 
									  								  	 		Constants.currencyUSD,
									  								  	 		product.getProductUPC());
     
        // then
    	assertFound(found);
	}
	

    private void assertFound(Optional<PhysicalProductFullDTO> physicalProductFullDTO) {
    	
    	assertNotNull(physicalProductFullDTO);
    	
    	assertTrue(physicalProductFullDTO.isPresent());
    
    	assertThat(physicalProductFullDTO.get().getProductupc())
        .isEqualTo("123456789");
    	
    	assertThat(physicalProductFullDTO.get().getBranddesc())
    	.isEqualTo("Planters");
    	
    	assertThat(physicalProductFullDTO.get().getRetailprice().doubleValue())
    	.isEqualTo(Double.valueOf(7.8));
    	
    	assertThat(physicalProductFullDTO.get().getInstock())
    	.isFalse();
    	
    	assertThat(physicalProductFullDTO.get().getMarkdownprice().doubleValue())
    	.isEqualTo(Double.valueOf(6.45));
    	
    }
    
}
