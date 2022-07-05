package io.nzbee.util.product.physical;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.util.ports.IPhysicalProductMasterPort;

@Service
public class PhysicalProductMasterService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ICategoryProductService categoryService;

	@Autowired
	private ITagService tagService;
	
	@Autowired
	private IProductStatusRepository productStatusService;
	
	@Autowired
	private IBrandService brandService;
	
	@Autowired 
	private IDepartmentService departmentService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
	private IProductPriceTypeService productPriceTypeService;
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
    
	@Autowired
	private IPhysicalProductMasterPort physicalProductMasterAdapter;
	
    //cached lists for performance improvement
    private List<BrandEntity> cachedBrandList;
    
    private List<DepartmentEntity> cachedDepartmentList;
    
    private List<ProductStatusEntity> cachedProductStatusList;
    
    private List<CategoryProductEntity> cachedProductCategoryList;
    
    private List<ProductPriceType> cachedPriceTypes;
    
    private List<Currency> cachedCurrencies;
    
    private List<TagEntity> cachedTags;
    
    @PostConstruct
    private void initEntities() {
    	cachedBrandList  			= new ArrayList<BrandEntity>();
    	cachedDepartmentList 		= new ArrayList<DepartmentEntity>();
    	cachedProductStatusList 	= new ArrayList<ProductStatusEntity>();
    	cachedProductCategoryList 	= new ArrayList<CategoryProductEntity>();
    	cachedPriceTypes 			= new ArrayList<ProductPriceType>();
    	cachedCurrencies  			= new ArrayList<Currency>();
    	cachedTags 					= new ArrayList<TagEntity>();
    }
    
	@Transactional
	public void writeProductMaster(String fileName) {
		LOGGER.debug("called writeProductMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        
	        MappingIterator<PhysicalProductMasterSchema> readValues =
	        	mapper.readerFor(PhysicalProductMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        cachedBrandList.addAll(brandService.findAll());
	        cachedDepartmentList.addAll(departmentService.findAll());
	        cachedProductStatusList.addAll(productStatusService.findAll());
	        cachedProductCategoryList.addAll(categoryService.findAll());
	        cachedCurrencies.addAll(currencyService.findAll());
	        cachedPriceTypes.addAll(productPriceTypeService.findAll());
	        cachedTags.addAll(tagService.findAll());
	        
	        readValues.readAll().stream().forEach(p -> {
	        	physicalProductMasterAdapter.save(p, 
	        									  cachedBrandList, 
	        									  cachedDepartmentList, 
	        									  cachedProductStatusList, 
	        									  cachedProductCategoryList, 
	        									  cachedPriceTypes, 
	        									  cachedCurrencies, 
	        									  cachedTags);
	        });
	        
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	}
	
}
