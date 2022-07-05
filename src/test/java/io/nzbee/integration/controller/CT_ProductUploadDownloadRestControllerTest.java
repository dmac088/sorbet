package io.nzbee.integration.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.File;
import java.io.FileInputStream;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Globals;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.util.PhysicalProductMasterAdapter;
import io.nzbee.entity.adapters.view.PhysicalProductFullAdapterImpl;
import io.nzbee.entity.brand.BrandServiceImpl;
import io.nzbee.entity.category.CategoryServiceImpl;
import io.nzbee.entity.category.product.CategoryProductServiceImpl;
import io.nzbee.entity.inventory.InventoryTransactionServiceImpl;
import io.nzbee.entity.inventory.location.InventoryLocationServiceImpl;
import io.nzbee.entity.inventory.type.InventoryTypeServiceImpl;
import io.nzbee.entity.product.ProductEntityServiceImpl;
import io.nzbee.entity.product.currency.CurrencyServiceImpl;
import io.nzbee.entity.product.department.DepartmentDaoImpl;
import io.nzbee.entity.product.department.DepartmentServiceImpl;
import io.nzbee.entity.product.physical.full.PhysicalProductFullDTOServiceImpl;
import io.nzbee.entity.product.physical.full.PhysicalProductFullDaoImpl;
import io.nzbee.entity.product.physical.full.PhysicalProductFullViewMapperImpl;
import io.nzbee.entity.product.price.ProductPriceServiceImpl;
import io.nzbee.entity.product.price.ProductPriceTypeService;
import io.nzbee.entity.promotion.PromotionDaoPostgresImpl;
import io.nzbee.entity.promotion.PromotionEntityServiceImpl;
import io.nzbee.entity.promotion.level.PromotionLevelServiceImpl;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicServiceImpl;
import io.nzbee.entity.promotion.type.PromotionTypeServiceImpl;
import io.nzbee.entity.role.supplier.SupplierServiceImpl;
import io.nzbee.entity.stock.StockOnHandServiceImpl;
import io.nzbee.entity.tag.TagDaoPostgresImpl;
import io.nzbee.entity.tag.TagServiceImpl;
import io.nzbee.exceptions.handle.CustomControllerAdvice;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.UserService;
import io.nzbee.util.FileController;
import io.nzbee.util.FileStorageProperties;
import io.nzbee.util.FileStorageServiceDownload;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.util.brand.BrandMasterService;
import io.nzbee.util.category.CategoryMasterService;
import io.nzbee.util.inventory.InventoryLocationMasterService;
import io.nzbee.util.inventory.InventoryMasterService;
import io.nzbee.util.product.physical.PhysicalProductMasterService;
import io.nzbee.util.product.shipping.ShippingProductMasterService;
import io.nzbee.util.promotion.category.CategoryPromotionMasterService;
import io.nzbee.util.promotion.mechanic.PromotionMechanicMasterService;
import io.nzbee.util.promotion.order.PromotionOrderMasterService;
import io.nzbee.util.promotion.product.ProductPromotionMasterService;
import io.nzbee.util.promotion.regular.PromotionProductMasterService;
import io.nzbee.util.tag.TagMasterService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataSourceBeanMochi.class,
							     DataSourceBeanSecurity.class,
							     UserService.class,
							     Globals.class,
							     WebSecurityConfig.class,
							     SecurityBeanConfiguration.class,
							     PhysicalProductFullAdapterImpl.class,
							     FileStorageProperties.class,
							     FileController.class,
							     FileStorageServiceDownload.class,
							     FileStorageServiceUpload.class,
							     PhysicalProductMasterService.class,
							     ProductEntityServiceImpl.class,
							     CategoryProductServiceImpl.class,
							     TagServiceImpl.class,
							     TagDaoPostgresImpl.class,
							     PhysicalProductFullDTOServiceImpl.class,
							     PhysicalProductFullDaoImpl.class,
							     PhysicalProductFullViewMapperImpl.class,
							     BrandServiceImpl.class,
							     DepartmentServiceImpl.class,
							     DepartmentDaoImpl.class,
							     CurrencyServiceImpl.class,
							     ProductPriceServiceImpl.class,
							     ProductPriceTypeService.class,
							     CategoryMasterService.class,
							     CategoryServiceImpl.class,
							     BrandMasterService.class,
							     TagMasterService.class,
							     ShippingProductMasterService.class,
							     PromotionProductMasterService.class,
							     PromotionEntityServiceImpl.class,
							     PromotionDaoPostgresImpl.class,
							     PromotionTypeServiceImpl.class,
							     PromotionMechanicServiceImpl.class,
							     PromotionLevelServiceImpl.class,
							     PromotionOrderMasterService.class,
							     CategoryPromotionMasterService.class,
							     ProductPromotionMasterService.class,
							     PromotionMechanicMasterService.class,
							     InventoryMasterService.class,
							     InventoryTransactionServiceImpl.class,
							     InventoryLocationServiceImpl.class,
							     InventoryTypeServiceImpl.class,
							     SupplierServiceImpl.class,
							     StockOnHandServiceImpl.class,
							     InventoryLocationMasterService.class,
							     CustomControllerAdvice.class,
							     PhysicalProductMasterAdapter.class
							     })
@WebMvcTest(FileController.class)
public class CT_ProductUploadDownloadRestControllerTest {
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void setUp() throws Exception {
    	IntegrationTestSetupHelper.dbInit(database);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }
    
	@Test
    public void whenUploadFileIsClean_thenFileUploadsSuccessfully() throws Exception {
		String path = "src/test/resources";
		
		File file = new File(path + "/data/product/physical/create/product_master.tsv");
	   
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", 
        															file.getAbsolutePath(), 
        															MediaType.MULTIPART_FORM_DATA.toString(), 
        															new FileInputStream(file));
        mockMvc.perform(multipart("/api/Product/Upload/")
        				.file(mockMultipartFile)
        				.with(csrf()))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andReturn();
    }
	
	
	@Test
    public void whenUploadFileHasInvalidBrand_thenFileUploadsFailsWithExcption() throws Exception {
		String path = "src/test/resources";
		File file = new File(path + "/data/product/physical/exceptions/brandNotFound/product_master.tsv");
	   
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", file.getAbsolutePath(), "multipart/form-data", new FileInputStream(file));
		
        mockMvc.perform(multipart("/api/Product/Upload/")
				.file(mockMultipartFile)
				.with(csrf()))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
    }
	
}
