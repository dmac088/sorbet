package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Map;

public class RegularBagItemDomainDTO {

	public static final String BAG_ITEM_UPC_ALIAS = "upc_cd";

	public static final String BAG_ITEM_PRICE_ALIAS = "prc_val";

	public static final String BAG_ITEM_INSTOCK_ALIAS = "in_stock";

	public static final String BAG_ITEM_STATUS_CODE_ALIAS = "bag_item_sts_cd";

	public static final String BAG_ITEM_TYPE_CODE_ALIAS = "bag_item_typ_cd";

	public static final String BAG_ITEM_WEIGHT_ALIAS = "weight";

	public static final String BAG_ITEM_QUANTITY_ALIAS = "qty";

	public static final String CURRENCY_CODES_ALIAS = "curr";

	
	private final String productUPC;

	private final String bagItemStatus;

	private final String bagItemType;

	private final BigDecimal markdownPrice;
	
	private final String currency;

	private final BigDecimal weight;

	private final Boolean inStock;

	private final Long quantity;

	public RegularBagItemDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagItemStatus = tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_CODE_ALIAS)].toString();
		this.bagItemType = tuple[aliasToIndexMap.get(BAG_ITEM_TYPE_CODE_ALIAS)].toString();
		this.weight = ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_WEIGHT_ALIAS)]);
		this.quantity = ((Number) tuple[aliasToIndexMap.get(BAG_ITEM_QUANTITY_ALIAS)]).longValue();
		this.productUPC = tuple[aliasToIndexMap.get(BAG_ITEM_UPC_ALIAS)].toString();
		this.markdownPrice = ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_PRICE_ALIAS)]);
		this.currency = tuple[aliasToIndexMap.get(CURRENCY_CODES_ALIAS)].toString();
		this.inStock = ((Boolean) tuple[aliasToIndexMap.get(BAG_ITEM_INSTOCK_ALIAS)]);
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
	
	public String getCurrency() {
		return currency;
	}

}
