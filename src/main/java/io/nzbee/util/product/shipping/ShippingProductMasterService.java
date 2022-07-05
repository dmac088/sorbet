package io.nzbee.util.product.shipping;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.shipping.entity.ShippingProductEntity;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class ShippingProductMasterService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
    
    @Autowired
    private IProductService productService;
    
	@Autowired
	private ICategoryProductService categoryService;
	
	@Autowired
	private ITagService tagService;
	
	@Autowired
	private IBrandService brandService; 
	
	@Autowired
	private IDepartmentService departmentService; 

	@Autowired
	private IProductStatusRepository productStatusService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
	private IProductPriceTypeService productPriceTypeService;
	
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
	public void writeShippingProductMaster(String fileName) {
		LOGGER.debug("called writeShippingProductMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<ShippingProductMasterSchema> readValues =
	        	mapper.readerFor(ShippingProductMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        
	        cachedBrandList.addAll(brandService.findAll());
	        cachedDepartmentList.addAll(departmentService.findAll());
	        cachedProductStatusList.addAll(productStatusService.findAll());
	        cachedProductCategoryList.addAll(categoryService.findAll());
	        cachedCurrencies.addAll(currencyService.findAll());
	        cachedPriceTypes.addAll(productPriceTypeService.findAll());
	        cachedTags.addAll(tagService.findAll());
	        
	        readValues.readAll().stream().forEach(sdms -> {
	        	this.persistShippingProductMaster(sdms);
	        });
	        
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	}
	
	public void persistShippingProductMaster(ShippingProductMasterSchema sdms) {
		LOGGER.debug("called persistShippingProductMaster");
		
		ShippingProductEntity spEn = this.mapToShippingProduct(
																  Constants.localeENGB,
																  Constants.currencyUSD,
																  sdms.get_PRODUCT_UPC_CODE(),
																  sdms.get_BRAND_CODE(),
																  sdms.get_PRODUCT_TEMPLATE_CODE(),
																  sdms.get_PRODUCT_CREATED_DATE(),
																  sdms.get_PRODUCT_DESCRIPTION_EN(),
																  sdms.get_PRODUCT_LONG_DESCRIPTION_EN(),
																  sdms.get_PRODUCT_RETAIL_PRICE_USD(),
																  sdms.get_PRODUCT_RETAIL_PRICE_USD(),
																  sdms.get_SERVICE_TYPE_CODE(), 
																  sdms.get_ZONE_CODE(),
																  sdms.get_DESTINATION_CODE(),
																  sdms.get_WEIGHT_LIMIT(),
																  sdms.get_WEIGHT_FROM(), 
																  sdms.get_WEIGHT_TO(), 
																  sdms.get_TRACKING_LEVEL(),
																  sdms.get_SHIP_TYPE_CODE(),
																  sdms.get_SHIP_COUNTRY_CODE());

		productService.save(Constants.localeENGB,
							Constants.currencyUSD,
							spEn.getShippingProduct());
		
		ShippingProductEntity spHk = this.mapToShippingProduct(
																  Constants.localeZHHK,
																  Constants.currencyHKD,
																  sdms.get_PRODUCT_UPC_CODE(),
																  sdms.get_BRAND_CODE(),
																  sdms.get_PRODUCT_TEMPLATE_CODE(),
																  sdms.get_PRODUCT_CREATED_DATE(),
																  sdms.get_PRODUCT_DESCRIPTION_HK(),
																  sdms.get_PRODUCT_LONG_DESCRIPTION_HK(),
																  sdms.get_PRODUCT_RETAIL_PRICE_HKD(),
																  sdms.get_PRODUCT_RETAIL_PRICE_HKD(),
																  sdms.get_SERVICE_TYPE_CODE(), 
																  sdms.get_ZONE_CODE(),
																  sdms.get_DESTINATION_CODE(),
																  sdms.get_WEIGHT_LIMIT(),
																  sdms.get_WEIGHT_FROM(), 
																  sdms.get_WEIGHT_TO(), 
																  sdms.get_TRACKING_LEVEL(), 
																  sdms.get_SHIP_TYPE_CODE(),
																  sdms.get_SHIP_COUNTRY_CODE());

		productService.save(Constants.localeZHHK,
				  			Constants.currencyHKD,
				  			spHk.getShippingProduct());
		
		
	}
	
	
	private ShippingProductEntity mapToShippingProduct(
														String locale, 
														String currency,
														String upcCode,
														String brandCode,
														String templateCode,
														String productCreateDate,
														String productDesc,
														String productLongDesc,
														String retailPrice,
														String markdownPrice,
														String serviceCode,
														String zoneCode,
														String destinationCode,
														String weightLimit,
														String weightFrom,
														String weightTo,
														String trackingLevel, 
														String ShipTypeCode, 
														String ShipCountryCode) {
		
	
		Optional<ProductEntity> op 	= productService.findByCode(upcCode);

		BrandEntity be 				= cachedBrandList.stream().filter(b -> b.getBrandCode().equals(brandCode)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.brandNotFound, Constants.localeENGB, brandCode));
		
		DepartmentEntity de 		= cachedDepartmentList.stream().filter(d -> d.getDepartmentCode().equals(templateCode)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.departmentNotFound, Constants.localeENGB, templateCode));
		
		Optional<ProductStatusEntity> ops 		= cachedProductStatusList.stream().filter(ps -> ps.getCode().equals(Constants.activeSKUCode)).findAny();
		
		CategoryProductEntity pce 	= cachedProductCategoryList.stream().filter(pc -> pc.getCategory().getCategoryCode().equals(Constants.shippingRootCategoryCode)).findAny()
				.orElseThrow(() ->  new EntityNotFoundException(ErrorKeys.categoryNotFound, Constants.localeENGB, Constants.shippingRootCategoryCode));
				
		Optional<ProductAttributeEntity> opa 	= 	(op.isPresent()) 
				 									? op.get().getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findAny().isPresent()
				 									  ? op.get().getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findAny()
				 									  : Optional.ofNullable(new ProductAttributeEntity())
				 									: Optional.ofNullable(new ProductAttributeEntity());
		
		CategoryProductEntity sc = pce;
		
		ProductEntity pe 				= (op.isPresent()) 
				  						  ? (ProductEntity) op.get()
				  						  : new ProductEntity();
		
		ShippingProductEntity sp = 	op.isPresent() 
						  			? (ShippingProductEntity) op.get().getProductShipping()
						  			: new ShippingProductEntity();
						  			
		pe.setProductShipping(sp);			
		sp.setShippingProduct(pe);
		
		sp.setShippingTypeCode(ShipTypeCode);
		sp.setShippingCountryCode(ShipCountryCode);
		
		ProductAttributeEntity pa = opa.isPresent()
									? opa.get()
									: (new ProductAttributeEntity());
					
		LocalDateTime createdDate = LocalDateTime.parse(productCreateDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));							
		
		sp.getShippingProduct().addCategory(sc);
		sp.getShippingProduct().setBrand(be);
		sp.getShippingProduct().setDepartment(de);
		sp.getShippingProduct().setProductUPC(upcCode);
		sp.getShippingProduct().setProductCreateDt(createdDate);
		sp.getShippingProduct().setProductStatus(ops.get());
		
		pa.setProductDesc(productDesc);
		pa.setProductLongDesc(productLongDesc);
		pa.setLclCd(locale);
		
		sp.getShippingProduct().addProductAttribute(pa);
		pa.setProduct(sp.getShippingProduct());
		
		
		Currency curr 				= cachedCurrencies.stream().filter(c -> c.getCode().equals(currency)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.currencyNotFound, Constants.localeENGB, currency));
		
		ProductPriceType ptr 		= cachedPriceTypes.stream().filter(pt -> pt.getCode().equals(Constants.retailPriceCode)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.priceTypeNotFound, Constants.localeENGB, Constants.retailPriceCode));
		
		ProductPriceType ptm 		= cachedPriceTypes.stream().filter(pt -> pt.getCode().equals(Constants.markdownPriceCode)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.priceTypeNotFound, Constants.localeENGB, Constants.markdownPriceCode));
		
		

		Optional<ProductPriceEntity> oprcr = sp.getShippingProduct().getPrices().stream()
				.filter(p -> p.getType().getCode().equals(Constants.retailPriceCode) && p.getCurrency().getCode().equals(currency)).findAny();

		//retail price
		ProductPriceEntity prcr = (	oprcr.isPresent()) 
									? oprcr.get()
									: new ProductPriceEntity();

		prcr.setType(ptr);
		prcr.setCurrency(curr);
		
		if(prcr.getPriceValue() != null && !(prcr.getPriceValue().setScale(2, BigDecimal.ROUND_HALF_EVEN).equals(
				new BigDecimal(retailPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN)))) {
			prcr.setPriceValue(new BigDecimal(retailPrice));
		}

		Optional<ProductPriceEntity> oprcm = sp.getShippingProduct().getPrices().stream()
				.filter(p -> p.getType().getCode().equals(Constants.markdownPriceCode) && p.getCurrency().getCode().equals(currency)).findAny();

		//markdown price
		ProductPriceEntity prcm = (oprcm.isPresent()) 
		? oprcm.get()
		: new ProductPriceEntity();
		
		prcm.setType(ptm);
		prcm.setCurrency(curr);
		
		sp.getShippingProduct().setProductStatus(ops.get());

		if(prcm.getPriceValue() != null &&!(prcm.getPriceValue().setScale(2, BigDecimal.ROUND_HALF_EVEN).equals(new BigDecimal(markdownPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN)))) {
			prcm.setPriceValue(new BigDecimal(markdownPrice));
		}
		
		sp.getShippingProduct().setInStock(true);
		
		sp.setWeightLimit(new BigDecimal(weightLimit));
		sp.setWeightFrom(new BigDecimal(weightFrom));
		sp.setWeightTo(new BigDecimal(weightTo));
		
		return sp;
		
	}
}
