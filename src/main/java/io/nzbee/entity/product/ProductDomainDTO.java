package io.nzbee.entity.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import io.nzbee.entity.category.product.CategoryProductDomainDTO;
import io.nzbee.entity.product.department.DepartmentDomainDTO;
import io.nzbee.entity.promotion.PromotionDomainDTO;

public class ProductDomainDTO implements Serializable {
	
	private static final long serialVersionUID = -2313168523583827407L;

	public static final String ID_ALIAS = "prd_id";
	
	public static final String UPC_ALIAS = "upc_cd";
    
    public static final String SHORT_DESC_ALIAS = "prd_desc";
    
    public static final String LONG_DESC_ALIAS = "prd_lng_desc";
    
    public static final String IN_STOCK_ALIAS = "prd_in_stock";
    
    public static final String IMAGE_ALIAS = "prd_img_pth";
    
    public static final String CODE_ALIAS = "prd_sts_cd";

    public static final String RETAIL_PRICE_ALIAS = "retail_price";
    
    public static final String MARKDOWN_PRICE_ALIAS = "markdown_price";
    
    public static final String CURRENCY_ALIAS = "ccy_cd";
    
    public static final String LOCALE_ALIAS = "lcl_cd";
 
    protected final Long productId;

	protected final String productUPC;

	protected final String productDesc;
	
	protected final String productLongDesc;
	

	//department
	protected final DepartmentDomainDTO department;
		
	
	//these fields will contain all the categories related to the product 1->N relationship
	protected final Set<CategoryProductDomainDTO> categories = new HashSet<CategoryProductDomainDTO>();
	
	protected final Set<PromotionDomainDTO> promotions = new HashSet<PromotionDomainDTO>();
	
	//pricing objects
	protected final BigDecimal retailPrice;
	
	protected final BigDecimal markdownPrice;
	
	
	//localization
	protected final String locale;
	
	protected final String currency;
	
	
	//stock and status
	
	protected final boolean inStock;

	protected final String productImage;

	protected final String productStatusCode;
	

	public ProductDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.productId 				= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
        this.productUPC 			= tuple[aliasToIndexMap.get(UPC_ALIAS)].toString();
        this.productDesc 			= tuple[aliasToIndexMap.get(SHORT_DESC_ALIAS)].toString();
        this.productLongDesc 		= !aliasToIndexMap.containsKey(LONG_DESC_ALIAS)
        								|| ((tuple[aliasToIndexMap.get(LONG_DESC_ALIAS)] == null))
        							  ? ""
        							  : tuple[aliasToIndexMap.get(LONG_DESC_ALIAS)].toString();
        this.inStock 				= ((Boolean) tuple[aliasToIndexMap.get(IN_STOCK_ALIAS)]);
        this.productImage 			= !aliasToIndexMap.containsKey(LONG_DESC_ALIAS)
				|| ((tuple[aliasToIndexMap.get(IMAGE_ALIAS)] == null))
			  ? ""
			  : tuple[aliasToIndexMap.get(IMAGE_ALIAS)].toString();
		this.productStatusCode 		= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.currency				= tuple[aliasToIndexMap.get(CURRENCY_ALIAS)].toString();
		this.locale					= tuple[aliasToIndexMap.get(LOCALE_ALIAS)].toString();
		this.retailPrice			= ((BigDecimal) tuple[aliasToIndexMap.get(RETAIL_PRICE_ALIAS)]);
		this.markdownPrice			= ((BigDecimal) tuple[aliasToIndexMap.get(MARKDOWN_PRICE_ALIAS)]);
	
		this.department = new DepartmentDomainDTO(tuple, aliasToIndexMap);
	}
	

	public String getProductUPC() {
		return productUPC;
	}
	
	public String getProductDesc() {
		return productDesc;
	}

	public String getProductLongDesc() {
		return productLongDesc;
	}

	public String getProductImage() {
		return productImage;
	}

	public String getProductStatusCode() {
		return productStatusCode;
	}

	public Set<CategoryProductDomainDTO> getCategories() {
		return categories;
	}
	
	public Set<PromotionDomainDTO> getPromotions() {
		return promotions;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public BigDecimal getMarkdownPrice() {
		return markdownPrice;
	}

	public String getLocale() {
		return locale;
	}

	public String getCurrency() {
		return currency;
	}

	public DepartmentDomainDTO getDepartment() {
		return department;
	}

	public boolean isInStock() {
		return inStock;
	}

}