package io.nzbee.entity.adapters.hkpost;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.entity.product.price.IProductPriceService;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.shipping.attribute.entity.IShippingProductAttributeEntityService;
import io.nzbee.entity.product.shipping.attribute.entity.ShippingProductAttributeEntity;
import io.nzbee.entity.product.shipping.entity.ShippingProductEntity;
import io.nzbee.entity.product.status.IProductStatusService;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.hkpost.IHKPostPort;
import io.nzbee.hkpost.PostageResponse;
import io.nzbee.keymaps.hkpost.shipping.country.LocalizedCountry;
import io.nzbee.keymaps.hkpost.shipping.description.LocalizedDescription;
import io.nzbee.keymaps.hkpost.shipping.weight.WeightLimits;

public class HKPostAdapter implements IHKPostPort {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private IBrandService brandService;
	
	@Autowired 
	private IDepartmentService departmentService;
	
	@Autowired 
	private ICategoryProductService categoryService;
	
	@Autowired 
	private IProductStatusService statusService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
	private IProductPriceTypeService priceTypeService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IShippingProductAttributeEntityService productAttributeService;
	
	@Autowired
	private IProductPriceService productPriceService;
	
	@Override
	@Transactional
	public void save(String locale, PostageResponse postageResponse, String upc, String weightFrom, String weightTo, String type, String country) {
		LOGGER.debug("call " + getClass() + ".save()");
		LOGGER.debug(postageResponse.toString());
		
		if(postageResponse.getTotalPostage() == null) {
			LOGGER.debug("the postage amount is null or 0, exiting.....");
			return;
		}
		
		CategoryProductEntity c = categoryService.findByCode(Constants.shippingRootCategoryCode).orElseThrow(() 	-> new EntityNotFoundException(ErrorKeys.categoryNotFound, locale, Constants.shippingRootCategoryCode));
		DepartmentEntity d = departmentService.findByCode(Constants.shippingProductDepartmentCode).orElseThrow(() 	-> new EntityNotFoundException(ErrorKeys.departmentNotFound, locale, Constants.shippingProductDepartmentCode));
		BrandEntity b = brandService.findByCode(Constants.hongKongPostBrandCode).orElseThrow(() 					-> new EntityNotFoundException(ErrorKeys.brandNotFound, locale,Constants.hongKongPostBrandCode));
		ProductStatusEntity ps = statusService.findByProductStatusCode(Constants.activeSKUCode).orElseThrow(() 		-> new EntityNotFoundException(ErrorKeys.productStatusNotFound, locale,Constants.activeSKUCode));
		Currency cHKD = currencyService.findByCode(Constants.currencyHKD).orElseThrow(() 							-> new EntityNotFoundException(ErrorKeys.currencyNotFound, locale,Constants.currencyHKD));
		Currency cUSD = currencyService.findByCode(Constants.currencyUSD).orElseThrow(() 							-> new EntityNotFoundException(ErrorKeys.currencyNotFound, locale,Constants.currencyUSD));
		ProductPriceType pteRet = priceTypeService.findByCode(Constants.retailPriceCode).orElseThrow(() 			-> new EntityNotFoundException(ErrorKeys.priceTypeNotFound, locale,Constants.retailPriceCode));
		ProductPriceType pteMkd = priceTypeService.findByCode(Constants.markdownPriceCode).orElseThrow(() 			-> new EntityNotFoundException(ErrorKeys.priceTypeNotFound, locale,Constants.markdownPriceCode));
		LocalDateTime createdDate = LocalDateTime.now();
		
		LOGGER.debug("the weight limit is " + WeightLimits.getWeightLimit(type));
		
		Optional<ProductEntity> op = productService.findByCode(upc);
		
		ProductEntity p = op.isPresent()
						? op.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productNotFound, locale, upc))
						: new ProductEntity();
		
		ShippingProductEntity sp = op.isPresent()
								 ? p.getProductShipping()
								 : new ShippingProductEntity();

		sp.addProductAttribute(getShippingAttribute(Constants.localeENGB, upc, type, country));
		sp.addProductAttribute(getShippingAttribute(Constants.localeZHHK, upc, type, country));
		
		sp.setShippingProduct(p);
		p.setProductShipping(sp);
		
		sp.setWeightFrom(new BigDecimal(weightFrom));
		sp.setWeightTo(new BigDecimal(weightTo));
		sp.setWeightLimit(new BigDecimal(WeightLimits.getWeightLimit(type)));
		sp.setShippingCountryCode(country);
		sp.setShippingTypeCode(type);
		
		p.addCategory(c);
		p.setDepartment(d);
		p.setBrand(b);
		
		p.setInStock(true);
		p.setProductStatus(ps);
		p.setProductCreateDt(createdDate);
		p.setUPC(upc);
		
		getPrice(p, pteRet, cUSD, convertHKDToUSD(postageResponse.getTotalPostage()));
		getPrice(p, pteMkd, cUSD, convertHKDToUSD(postageResponse.getTotalPostage()));
		getPrice(p, pteRet, cHKD, postageResponse.getTotalPostage());
		getPrice(p, pteMkd, cHKD, postageResponse.getTotalPostage());
		
		productService.save(p);
	}
	
	private ShippingProductAttributeEntity getShippingAttribute(String locale, String upc, String type, String countryCode) {
		Optional<ShippingProductAttributeEntity> opae = productAttributeService.findByCode(locale, upc);
		ShippingProductAttributeEntity pae = opae.isPresent() 
										   ? opae.get()
										   : new ShippingProductAttributeEntity();
		pae.setShippingTypeDesc(new LocalizedDescription(type,locale).getLocalizedDescription());
		pae.setShippingCountryDesc(new LocalizedCountry(countryCode, locale).getLocalizedCountry());
		pae.setLclCd(locale);
		return pae;
	}
	
	private BigDecimal convertHKDToUSD(BigDecimal value) {
		return value.divide(new BigDecimal("7.8"), 2, RoundingMode.HALF_UP);
	}
	
	private ProductPriceEntity getPrice(ProductEntity p, ProductPriceType pt, Currency c, BigDecimal totalPostage) {
		Optional<ProductPriceEntity> op = productPriceService.findByProductCode(p.getProductUPC(), pt.getCode(), c.getCode());
		ProductPriceEntity price = op.isPresent() 
								  ? op.get() 
								  : new ProductPriceEntity();
		price.setType(pt);
		price.setCurrency(c);
		price.setPriceValue(totalPostage);
		price.setProduct(p);
		p.addProductPrice(price);
		return price;
	}

}
