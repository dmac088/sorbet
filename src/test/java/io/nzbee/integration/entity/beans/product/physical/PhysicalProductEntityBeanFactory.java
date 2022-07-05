package io.nzbee.integration.entity.beans.product.physical;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.physical.entity.PhysicalProductEntity;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.promotion.IPromotionEntityService;
import io.nzbee.entity.promotion.product.PromotionProductEntity;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagEntity;

@Service

public class PhysicalProductEntityBeanFactory implements IPhysicalProductEntityBeanFactory {
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired 
	private IProductPriceTypeService productPriceTypeService; 
	
	@Autowired
	private IBrandService brandService;
	    
	@Autowired
	private IDepartmentService departmentService;
	    
	@Autowired
	private IProductStatusRepository productStatusRepository;
	    
	@Autowired
	private ICategoryProductService categoryService;
	
	@Autowired
	private IPromotionEntityService promotionService;
	
	@Autowired
	private ITagService tagService;

	@Override
	public ProductEntity getBean() {
		ProductEntity pe = new ProductEntity();		
		PhysicalProductEntity product = new PhysicalProductEntity();
		pe.setProductPhysical(product);
		product.setPhysicalProduct(pe);
		product.getPhysicalProduct().setProductCreateDt(LocalDateTime.now());
		product.getPhysicalProduct().setUPC("123456789");
		
		ProductAttributeEntity paEng = new ProductAttributeEntity();
		paEng.setProductDesc("test product");
		paEng.setProductImage("testpath/");
		paEng.setLclCd("en-GB");
		paEng.setProduct(product.getPhysicalProduct());
		product.getPhysicalProduct().addProductAttribute(paEng);
		
		ProductAttributeEntity paCn = new ProductAttributeEntity();
		paCn.setProductDesc("測試產品");
		paEng.setProductImage("testpath/");
		paCn.setLclCd("zh-HK");
		paCn.setProduct(product.getPhysicalProduct());
		product.getPhysicalProduct().addProductAttribute(paCn);

		ProductPriceType pptRetail = productPriceTypeService.findByCode(Constants.retailPriceCode).get();
		ProductPriceType pptMarkdown = productPriceTypeService.findByCode(Constants.markdownPriceCode).get();
		Currency currHKD = currencyService.findByCode(Constants.currencyHKD).get();
		Currency currUSD = currencyService.findByCode(Constants.currencyUSD).get();
		ProductPriceEntity retailPriceHKD = new ProductPriceEntity();
		ProductPriceEntity retailPriceUSD = new ProductPriceEntity();
		ProductPriceEntity markdownPriceHKD = new ProductPriceEntity();
		ProductPriceEntity markdownPriceUSD = new ProductPriceEntity();
		retailPriceHKD.setType(pptRetail);
		retailPriceUSD.setType(pptRetail);
		markdownPriceHKD.setType(pptMarkdown);
		markdownPriceUSD.setType(pptMarkdown);
		
		retailPriceHKD.setCurrency(currHKD);
		retailPriceHKD.setPriceValue(BigDecimal.valueOf(60.48));
		retailPriceUSD.setCurrency(currUSD);
		retailPriceUSD.setPriceValue(BigDecimal.valueOf(7.8));
		
		markdownPriceHKD.setCurrency(currHKD);
		markdownPriceHKD.setPriceValue(BigDecimal.valueOf(50.00));
		markdownPriceUSD.setCurrency(currUSD);
		markdownPriceUSD.setPriceValue(BigDecimal.valueOf(6.45));
		
		product.getPhysicalProduct().getPrices().add(retailPriceHKD);
		product.getPhysicalProduct().getPrices().add(retailPriceUSD);
		product.getPhysicalProduct().getPrices().add(markdownPriceHKD);
		product.getPhysicalProduct().getPrices().add(markdownPriceUSD);
		
		retailPriceHKD.setProduct(product.getPhysicalProduct());
		retailPriceUSD.setProduct(product.getPhysicalProduct());
		
		markdownPriceHKD.setProduct(product.getPhysicalProduct());    
		markdownPriceUSD.setProduct(product.getPhysicalProduct());
		
		//we need a brand
		product.getPhysicalProduct().setBrand(brandService.findByCode("PLA01").get());
				
		//we need a type
		product.getPhysicalProduct().setDepartment(departmentService.findByCode("ACC01").get());
				
		//we need a status
		product.getPhysicalProduct().setProductStatus(productStatusRepository.findByProductStatusCode("ACT01").get());
				
		//we need a category
		CategoryProductEntity cpf = (CategoryProductEntity) categoryService.findByCode("POM01").get();
				
		CategoryProductEntity cpv = (CategoryProductEntity) categoryService.findByCode("CIT01").get();
		
		//add the category to the product
		product.getPhysicalProduct().addProductCategory(cpf);
		product.getPhysicalProduct().addProductCategory(cpv);
		
		//we should add a tag
		TagEntity t = tagService.findByCode("ORG01").get();
		
		product.getPhysicalProduct().addTag(t);
		
		//we should add a promotion 
		product.getPhysicalProduct().addPromotion((PromotionProductEntity) promotionService.findByCode("RB2G50").get().getPromotionProduct());
		
		return product.getPhysicalProduct();
	}
	
	
}
