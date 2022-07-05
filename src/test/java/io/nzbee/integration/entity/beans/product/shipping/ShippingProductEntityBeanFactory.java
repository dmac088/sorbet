package io.nzbee.integration.entity.beans.product.shipping;

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
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.shipping.entity.ShippingProductEntity;
import io.nzbee.entity.product.status.IProductStatusRepository;

@Service

public class ShippingProductEntityBeanFactory implements IShippingProductEntityBeanFactory {
	
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
	   
	@Override
	public ProductEntity getBean() {
		ProductEntity pe = new ProductEntity();		
		ShippingProductEntity product = new ShippingProductEntity();
		pe.setProductShipping(product);
		product.setShippingProduct(pe);		
		product.getShippingProduct().setProductCreateDt(LocalDateTime.now());
		product.getShippingProduct().setUPC("123456789");
		
		ProductAttributeEntity paEng = new ProductAttributeEntity();
		paEng.setProductDesc( "Test shipping destination description");
		paEng.setProductImage("testpath/");
		paEng.setLclCd(Constants.localeENGB);
		paEng.setProduct(product.getShippingProduct());
		product.getShippingProduct().addProductAttribute(paEng);
		
		ProductAttributeEntity paCn = new ProductAttributeEntity();
		paCn.setProductDesc("測試產品");
		paEng.setProductImage("testpath/");
		paCn.setLclCd(Constants.localeZHHK);
		paCn.setProduct(product.getShippingProduct());
		product.getShippingProduct().addProductAttribute(paCn);

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
		
		product.getShippingProduct().getPrices().add(retailPriceHKD);
		product.getShippingProduct().getPrices().add(retailPriceUSD);
		product.getShippingProduct().getPrices().add(markdownPriceHKD);
		product.getShippingProduct().getPrices().add(markdownPriceUSD);
		
		retailPriceHKD.setProduct(product.getShippingProduct());
		retailPriceUSD.setProduct(product.getShippingProduct());
		
		markdownPriceHKD.setProduct(product.getShippingProduct());    
		markdownPriceUSD.setProduct(product.getShippingProduct());
		
		//we need a category
		CategoryProductEntity cp = (CategoryProductEntity) categoryService.findByCode("SHP01").get();
				
		//add the category to the product
		product.getShippingProduct().addProductCategory(cp);
		
		//we need a brand
		product.getShippingProduct().setBrand(brandService.findByCode("HKP01").get());
				
		//we need a type
		product.getShippingProduct().setDepartment(departmentService.findByCode("SHP01").get());
				
		//we need a status
		product.getShippingProduct().setProductStatus(productStatusRepository.findByProductStatusCode("ACT01").get());
		
		product.setWeightLimit(new BigDecimal(5));
		product.setWeightFrom(new BigDecimal(2));
		product.setWeightTo(new BigDecimal(3));
		
		product.setShippingTypeCode("LEG");
		product.setShippingCountryCode("HKG");
		
		return product.getShippingProduct();
	}
	
	
}
