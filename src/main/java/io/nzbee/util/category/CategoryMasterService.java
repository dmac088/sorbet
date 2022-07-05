package io.nzbee.util.category;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.Constants;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class CategoryMasterService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ICategoryService categoryService;
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
    @Transactional
	public void writeCategoryMaster(String fileName) {
		LOGGER.debug("called writeCategoryMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<CategoryMasterSchema> readValues =
	        	mapper.readerFor(CategoryMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(c -> {
	        	this.persistCategoryMaster(c);
	        });
	        
	        
	        List<CategoryEntity> lc = categoryService.findAll();
	        lc.stream().forEach(c -> {
	        	if(!(c.getCategoryParentCode() == null)) {
	        		Optional<io.nzbee.entity.category.CategoryEntity> opc = categoryService.findByCode(c.getCategoryParentCode());
	        		if(opc.isPresent()) {
	        			c.setCategoryParentId(opc.get().getCategoryId());
	        			categoryService.save(c);
	        		}
	        	}
	        });
	        
	        
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	}
	
	public void persistCategoryMaster(CategoryMasterSchema c) {
		LOGGER.debug("called persistCategoryMaster() ");
		
		CategoryEntity cEN = c.get_CATEGORY_TYPE().equals("productcategory")
						? mapToProductCategory(c.get_CATEGORY_CODE(),
											   Long.valueOf(c.get_CATEGORY_LEVEL()),
											   c.get_PARENT_CATEGORY_CODE(),
											   c.get_CATEGORY_DESC_EN(),
											   Constants.localeENGB).getCategory()
						: mapToBrandCategory(c.get_CATEGORY_CODE(),
											 c.get_CATEGORY_DESC_EN(),
											 Constants.localeENGB).getCategory();

		categoryService.save(cEN);
		
		CategoryEntity cCN =	c.get_CATEGORY_TYPE().equals("productcategory")
				?  mapToProductCategory(
						c.get_CATEGORY_CODE(),
						Long.valueOf(c.get_CATEGORY_LEVEL()),
						c.get_PARENT_CATEGORY_CODE(),
						c.get_CATEGORY_DESC_HK(),
						Constants.localeZHHK).getCategory()
				:  mapToBrandCategory(
						c.get_CATEGORY_CODE(),
						c.get_CATEGORY_DESC_HK(),
						Constants.localeZHHK).getCategory();

		categoryService.save(cCN);
	}
	
	private CategoryProductEntity mapToProductCategory(String categoryCode,
												 Long   categoryLevel,
												 String parentCategoryCode,
												 String categoryDesc,
												 String locale
												 ) {
		LOGGER.debug("called mapToProductCategory() ");
		
		Optional<CategoryEntity> oc = categoryService.findByCode(categoryCode);
		
		CategoryEntity ce = (oc.isPresent()) 
							? (oc.get()) 
							: new CategoryEntity();
		
		CategoryProductEntity cp = (oc.isPresent()) 
							 ? oc.get().getCategoryProduct()
							 : new CategoryProductEntity();
		
		ce.setCategoryProduct(cp);
		cp.setCategory(ce);
							 
		CategoryAttributeEntity ca = new CategoryAttributeEntity();
		if (oc.isPresent()) {
			Optional<CategoryAttributeEntity> oca = cp.getCategory().getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findFirst();
			ca = (oca.isPresent()) 
				? oca.get()
				: new CategoryAttributeEntity();
		}
		
		cp.getCategory().setCategoryCode(categoryCode);
		cp.getCategory().setCategoryLevel(categoryLevel);
		cp.getCategory().setCategoryParentCode(parentCategoryCode);
		
		ca.setCategoryDesc(categoryDesc);
		ca.setLclCd(locale);
		ca.setCategory(cp.getCategory());
		cp.getCategory().addCategoryAttribute(ca);
		
		return cp;
	}
	
	private CategoryBrandEntity mapToBrandCategory(	String categoryCode,
											 		String categoryDesc,
											 		String locale
											) {
		LOGGER.debug("called mapToBrandCategory() ");
		
		Optional<CategoryEntity> oc = categoryService.findByCode(categoryCode);
		
		CategoryEntity cb = (oc.isPresent()) 
				 ? (CategoryEntity) oc.get()
				 : new CategoryEntity();
		
		CategoryBrandEntity cbe = (oc.isPresent()) 
	 			? (CategoryBrandEntity) oc.get().getCategoryBrand()
	 			: new CategoryBrandEntity();
		
		cb.setCategoryBrand(cbe);		
		cbe.setCategory(cb);
	
		CategoryAttributeEntity ca = (oc.isPresent()) 
				 ? cb.getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findFirst().get()
				 : new CategoryAttributeEntity();
		
		ca.setCategoryDesc(categoryDesc);
		ca.setLclCd(locale);
		ca.setCategory(cb);
		
		cb.setCategoryCode(categoryCode);
		cb.addCategoryAttribute(ca);
		cb.setCategoryAttribute(ca);
		
		return cbe;
	}
	
	
}
