package io.nzbee.integration.entity.product.physical;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.math.BigDecimal;
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
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.product.physical.view.IPhysicalProductDTOService;
import io.nzbee.entity.product.physical.view.PhysicalProductDTO;
import io.nzbee.util.product.physical.PhysicalProductMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = {ConfigPhysicalProductEntityTests.class})
public class IT_PhysicalProductUploadForCreateIntegrationTest {
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
    @Autowired
    private PhysicalProductMasterService pms;
    
    @Autowired
    private IPhysicalProductDTOService productService;
	
	private static boolean setUpIsDone = false;
	
	@Before
	public void setUp() {
		if (setUpIsDone) {
			return;
		}
		try (Connection con = database.getConnection()) {
			ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/mochi_schema.sql"));
			ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/mochi_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.loadProducts();
		setUpIsDone = true;
	}
	
	
	public void loadProducts() {
		String path = "src/test/resources";
		File file = new File(path);
	
		pms.writeProductMaster(file.getAbsolutePath() + "/data/product/physical/create/product_master.tsv");
	}
	
	@Test
	@Rollback(false)
	public void whenProductUploadedForCreation_thenReturnCreatedProduct_ENGB_USD() {
		 // when
    	Optional<PhysicalProductDTO> found = productService.findByCode( Constants.localeENGB, 
							  								  	Constants.currencyUSD,  
															  	"43254232");
     
        // then
    	assertFound_ENGB_USD(found);
	}
	
	@Test
	@Rollback(false)
	public void whenProductUploadedForCreation_thenReturnCreatedProduct_ZHHK_HKD() {
		 // when
    	Optional<PhysicalProductDTO> found = productService.findByCode( Constants.localeZHHK, 
							  								  	Constants.currencyHKD,  
															  	"43254232");
     
        // then
    	assertFound_ZHHK_HKD(found);
	}
	
	private void assertFound_ENGB_USD(Optional<PhysicalProductDTO> found) {
		assertNotNull(found);
		
		assertTrue(found.isPresent());

		assertThat(found.get().getProductDto().getProductUPC()).isEqualTo("43254232");

		assertThat(found.get().getProductDto().getProductDesc()).isEqualTo("organic cucumber");

		assertThat(found.get().getProductDto().getProductLongDesc()).isEqualTo("newly fresh organic cucumber");

		assertThat(found.get().getProductDto().getProductStatusCode()).isEqualTo("ACT01");

		assertThat(found.get().getProductDto().getRetailPrice()).isEqualTo(BigDecimal.valueOf(8));

		assertThat(found.get().getProductDto().getMarkdownPrice()).isEqualTo(BigDecimal.valueOf(7));

		assertThat(((PhysicalProductDTO) found.get()).getHeight()).isEqualTo(Integer.valueOf(0));
		
		assertThat(((PhysicalProductDTO) found.get()).getWidth()).isEqualTo(Integer.valueOf(0));
		
		assertThat(((PhysicalProductDTO) found.get()).getLength()).isEqualTo(Integer.valueOf(0));
		
		assertThat(((PhysicalProductDTO) found.get()).getWeight().doubleValue()).isEqualTo(Double.valueOf(0.0));
	}

	private void assertFound_ZHHK_HKD(Optional<PhysicalProductDTO> found) {
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getProductDto().getProductUPC()).isEqualTo("43254232");

		assertThat(found.get().getProductDto().getProductDesc()).isEqualTo("有機黃瓜");

		assertThat(found.get().getProductDto().getProductLongDesc()).isEqualTo("新近新鮮的有機黃瓜");

		assertThat(found.get().getProductDto().getProductStatusCode()).isEqualTo("ACT01");

		assertThat(found.get().getProductDto().getRetailPrice()).isEqualTo(BigDecimal.valueOf(60));

		assertThat(found.get().getProductDto().getMarkdownPrice()).isEqualTo(BigDecimal.valueOf(55));
		
		assertThat(((PhysicalProductDTO) found.get()).getHeight()).isEqualTo(Integer.valueOf(0));
		
		assertThat(((PhysicalProductDTO) found.get()).getWidth()).isEqualTo(Integer.valueOf(0));
		
		assertThat(((PhysicalProductDTO) found.get()).getLength()).isEqualTo(Integer.valueOf(0));
		
		assertThat(((PhysicalProductDTO) found.get()).getWeight().doubleValue()).isEqualTo(Double.valueOf(0));
	}
	
}
