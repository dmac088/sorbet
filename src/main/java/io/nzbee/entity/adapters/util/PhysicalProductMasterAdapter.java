package io.nzbee.entity.adapters.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.physical.entity.PhysicalProductEntity;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.util.ports.IPhysicalProductMasterPort;
import io.nzbee.util.product.physical.PhysicalProductMasterSchema;

public class PhysicalProductMasterAdapter implements IPhysicalProductMasterPort {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private final String locale = Constants.localeENGB;

	@Autowired
	private IProductService productService;


	@Override
	@Transactional
	public void save(PhysicalProductMasterSchema p, List<BrandEntity> cachedBrandList,
			List<DepartmentEntity> cachedDepartmentList, List<ProductStatusEntity> cachedProductStatusList,
			List<CategoryProductEntity> cachedProductCategoryList, List<ProductPriceType> cachedPriceTypes,
			List<Currency> cachedCurrencies, List<TagEntity> cachedTags) {
		
		LOGGER.debug("call PhysicalProductMasterAdapter.save()");

		Optional<ProductEntity> op = productService.findByCode(p.get_PRODUCT_UPC_CODE());
		ProductEntity pe = (op.isPresent()) ? (ProductEntity) op.get() : new ProductEntity();
		PhysicalProductEntity ppe = (op.isPresent()) ? op.get().getProductPhysical() : new PhysicalProductEntity();

		pe.setProductPhysical(ppe);
		ppe.setPhysicalProduct(pe);

		BrandEntity be = getBrand(cachedBrandList, p.get_BRAND_CODE());
		DepartmentEntity de = getDepartment(cachedDepartmentList, p.get_PRODUCT_TEMPLATE_CODE());
		ProductStatusEntity pse = getProductStatus(cachedProductStatusList, Constants.activeSKUCode);
		CategoryProductEntity pce = getProductCategory(cachedProductCategoryList, p.get_PRIMARY_CATEGORY_CODE());
		LocalDateTime createdDate = LocalDateTime.parse(p.get_PRODUCT_CREATED_DATE(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		pe.setBrand(be);
		pe.setDepartment(de);
		pe.setProductUPC(p.get_PRODUCT_UPC_CODE());
		pe.setProductCreateDt(createdDate);
		pe.setProductStatus(pse);
		pe.getCategories();
		pe.addCategory(pce);

		ppe.setWidthDimension(Integer.parseInt(p.get_WIDTH()));
		ppe.setHeightDimension(Integer.parseInt(p.get_HEIGHT()));
		ppe.setLengthDimension(Integer.parseInt(p.get_LENGTH()));
		ppe.setWeightDimension(new BigDecimal(p.get_WEIGHT()));

		ProductAttributeEntity pa_en = getAttribute(op, Constants.localeENGB);
		ProductAttributeEntity pa_zh = getAttribute(op, Constants.localeZHHK);
		pe.addProductAttribute(mapProductAttributeEN(p, pa_en));
		pe.addProductAttribute(mapProductAttributeZH(p, pa_zh));

		Currency currUSD = getCurrency(cachedCurrencies, Constants.currencyUSD);
		Currency currHKD = getCurrency(cachedCurrencies, Constants.currencyHKD);

		ProductPriceType ptrtl = getPriceType(cachedPriceTypes, Constants.retailPriceCode);
		ProductPriceType ptmkd = getPriceType(cachedPriceTypes, Constants.markdownPriceCode);

		// retail price
		ProductPriceEntity retailHKD = mapPrice(getPrice(pe, Constants.retailPriceCode, Constants.currencyHKD), ptrtl, currHKD, p.get_PRODUCT_RETAIL_PRICE_HKD());
		
		ProductPriceEntity retailUSD = mapPrice(getPrice(pe, Constants.retailPriceCode, Constants.currencyUSD), ptrtl, currUSD, p.get_PRODUCT_RETAIL_PRICE_USD());

		ProductPriceEntity markdownHKD = mapPrice(getPrice(pe, Constants.markdownPriceCode, Constants.currencyHKD), ptmkd, currHKD, p.get_PRODUCT_MARKDOWN_PRICE_HKD());
				
		ProductPriceEntity markdownUSD = mapPrice(getPrice(pe, Constants.markdownPriceCode, Constants.currencyUSD), ptmkd, currUSD, p.get_PRODUCT_MARKDOWN_PRICE_USD());

		pe.setProductStatus(pse);
		pe.addProductPrice(retailHKD);
		pe.addProductPrice(retailUSD);
		pe.addProductPrice(markdownHKD);
		pe.addProductPrice(markdownUSD);

		// add the tags to the domain object
		addTagToProduct(p.get_TAG_CODE_A(), pe, cachedTags);
		addTagToProduct(p.get_TAG_CODE_B(), pe, cachedTags);
		addTagToProduct(p.get_TAG_CODE_C(), pe, cachedTags);
		addTagToProduct(p.get_TAG_CODE_D(), pe, cachedTags);
		addTagToProduct(p.get_TAG_CODE_E(), pe, cachedTags);
		
		productService.save(pe);
	}
	
	private void addTagToProduct(String tagCode, ProductEntity p, List<TagEntity> cachedTags) {
		if (tagCode == null) return;
		if(tagCode.length() == 5) {
			Optional<TagEntity> ot = cachedTags.stream().filter(t -> t.getTagCode().equals(tagCode)).findAny();
			if(ot.isPresent()) {
				p.addTag(ot.get());
			}
		}
	}
	
	private ProductPriceEntity mapPrice(ProductPriceEntity ppe, ProductPriceType pt, Currency c, String value) {
		ppe.setPriceValue(new BigDecimal(value));
		ppe.setCurrency(c);
		ppe.setType(pt);
		return ppe;
	}
	
	private ProductPriceEntity getPrice(ProductEntity pe, String priceTypeCode, String currency) {
		Optional<ProductPriceEntity> ope = pe.getPrices().stream().filter(prc -> 
											prc.getCurrency().getCode().equals(currency) && 
											prc.getType().getCode().equals(priceTypeCode)).findAny();
		ProductPriceEntity npe = ope.isPresent()
								? ope.get()
								: new ProductPriceEntity();
		return npe;
	}

	private ProductPriceType getPriceType(List<ProductPriceType> cachedPriceTypes, String priceTypeCode) {
		return cachedPriceTypes.stream().filter(pt -> pt.getCode().equals(priceTypeCode)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.priceTypeNotFound, locale, priceTypeCode));
	}

	private Currency getCurrency(List<Currency> cachedCurrencies, String currency) {
		return cachedCurrencies.stream().filter(c -> c.getCode().equals(currency)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.currencyNotFound, locale, currency));
	}

	private BrandEntity getBrand(List<BrandEntity> cachedBrandList, String brandCode) {
		return cachedBrandList.stream().filter(b -> b.getBrandCode().equals(brandCode)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.brandNotFound, locale, brandCode));
	}

	private DepartmentEntity getDepartment(List<DepartmentEntity> cachedDepartmentList, String departmentCode) {
		return cachedDepartmentList.stream().filter(d -> d.getDepartmentCode().equals(departmentCode))
				.findAny().orElseThrow(() -> new EntityNotFoundException(ErrorKeys.departmentNotFound, locale, departmentCode));
	}

	private ProductStatusEntity getProductStatus(List<ProductStatusEntity> cachedProductStatusList, String activeSKUCode) {
		return cachedProductStatusList.stream().filter(ps -> ps.getCode().equals(activeSKUCode)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.productStatusNotFound, locale, activeSKUCode));
	}

	private CategoryProductEntity getProductCategory(List<CategoryProductEntity> cachedProductCategoryList, String categoryCode) {
		return cachedProductCategoryList.stream()
				.filter(pc -> pc.getCategory().getCategoryCode().equals(categoryCode)).findAny()
				.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.categoryNotFound, locale, categoryCode));
	}

	private boolean attributeExists(ProductEntity pe, String locale) {
		return pe.getAttributes().stream().filter(p -> p.getLclCd().equals(locale)).findAny().isPresent();
	}

	private ProductAttributeEntity getExistingAttribute(ProductEntity pe, String locale) {
		return pe.getAttributes().stream().filter(p -> p.getLclCd().equals(locale)).findAny().get();
	}

	private ProductAttributeEntity getAttribute(Optional<ProductEntity> op, String locale) {
		return (op.isPresent()) ? attributeExists(op.get(), locale) ? getExistingAttribute(op.get(), locale)
				: new ProductAttributeEntity() : new ProductAttributeEntity();
	}

	private ProductAttributeEntity mapProductAttributeEN(PhysicalProductMasterSchema p, ProductAttributeEntity pa) {
		pa.setProductDesc(p.get_PRODUCT_DESCRIPTION_EN());
		pa.setProductLongDesc(p.get_PRODUCT_LONG_DESCRIPTION_EN());
		pa.setLclCd(Constants.localeENGB);
		return pa;
	}

	private ProductAttributeEntity mapProductAttributeZH(PhysicalProductMasterSchema p, ProductAttributeEntity pa) {
		pa.setProductDesc(p.get_PRODUCT_DESCRIPTION_HK());
		pa.setProductLongDesc(p.get_PRODUCT_LONG_DESCRIPTION_HK());
		pa.setLclCd(Constants.localeZHHK);
		return pa;
	}

}
