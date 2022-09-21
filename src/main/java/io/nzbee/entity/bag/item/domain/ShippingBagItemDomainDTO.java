package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Map;

public class ShippingBagItemDomainDTO {
	

	public static final String BAG_ITEM_UPC_ALIAS = "upc_cd";

	public static final String BAG_ITEM_PRICE_ALIAS = "prc_val";

	public static final String BAG_ITEM_STATUS_CODE_ALIAS = "bag_item_sts_cd";

	public static final String BAG_ITEM_TYPE_CODE_ALIAS = "bag_item_typ_cd";
	
	public static final String CURRENCY_CODES_ALIAS = "curr";
	
	

	private final String productUPC;

	private final String bagItemStatus;

	private final String bagItemType;

	private final BigDecimal markdownPrice;
	
	private final String currency;
	
	//private final String brandCode;
	
	

	public ShippingBagItemDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagItemStatus = tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_CODE_ALIAS)].toString();
		this.bagItemType = tuple[aliasToIndexMap.get(BAG_ITEM_TYPE_CODE_ALIAS)].toString();
		this.productUPC = tuple[aliasToIndexMap.get(BAG_ITEM_UPC_ALIAS)].toString();
		this.markdownPrice = ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_PRICE_ALIAS)]);
		this.currency =  tuple[aliasToIndexMap.get(CURRENCY_CODES_ALIAS)].toString();
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

	public String getCurrency() {
		return currency;
	}

}
