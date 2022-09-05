package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RegularBagItemDomainDTO {

	public static final String BAG_ITEM_UPC_ALIAS = "upc_cd";

	public static final String BAG_ITEM_PRICE_ALIAS = "prc_val";

	public static final String BAG_ITEM_INSTOCK_ALIAS = "in_stock";

	public static final String BAG_ITEM_STATUS_CODE_ALIAS = "bag_item_sts_cd";

	public static final String BAG_ITEM_TYPE_CODE_ALIAS = "bag_item_typ_cd";

	public static final String BAG_ITEM_WEIGHT_ALIAS = "weight";

	public static final String BAG_ITEM_QUANTITY_ALIAS = "qty";
	
	public static final String BRAND_CODE_ALIAS = "bnd_cd";
	
	public static final String CATEGORY_CODES_ALIAS = "lst_cat_cd";

	
	private final String productUPC;

	private final String bagItemStatus;

	private final String bagItemType;

	private final BigDecimal markdownPrice;

	private final BigDecimal weight;

	private final Boolean inStock;

	private final Long quantity;
	
	private final String brandCode;
	
	private final List<String> categoryCodes;
	

	public RegularBagItemDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagItemStatus = tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_CODE_ALIAS)].toString();
		this.bagItemType = tuple[aliasToIndexMap.get(BAG_ITEM_TYPE_CODE_ALIAS)].toString();
		this.weight = ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_WEIGHT_ALIAS)]);
		this.quantity = ((Number) tuple[aliasToIndexMap.get(BAG_ITEM_QUANTITY_ALIAS)]).longValue();
		this.productUPC = tuple[aliasToIndexMap.get(BAG_ITEM_UPC_ALIAS)].toString();
		this.markdownPrice = ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_PRICE_ALIAS)]);
		this.inStock = ((Boolean) tuple[aliasToIndexMap.get(BAG_ITEM_INSTOCK_ALIAS)]);
		this.brandCode = tuple[aliasToIndexMap.get(BRAND_CODE_ALIAS)].toString();
		this.categoryCodes = Arrays.asList(tuple[aliasToIndexMap.get(CATEGORY_CODES_ALIAS)].toString().split(","));
	}

	public String getProductUPC() {
		return productUPC;
	}

	public String getBagItemStatus() {
		return bagItemStatus;
	}

	public String getBagItemType() {
		return bagItemType;
	}

	public BigDecimal getMarkdownPrice() {
		return markdownPrice;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public Boolean isInStock() {
		return inStock;
	}

	public Long getQuantity() {
		return quantity;
	}
	
	public String getBrandCode() {
		return brandCode;
	}

	public List<String> getCategoryCodes() {
		return categoryCodes;
	}

}
