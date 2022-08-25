package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Map;

public class ShippingBagItemDomainDTO {

//public static final String ID_ALIAS = "bag_item_id";

	public static final String BAG_ITEM_UPC_ALIAS = "upc_cd";

	public static final String BAG_ITEM_PRICE_ALIAS = "prc_val";

	public static final String BAG_ITEM_STATUS_CODE_ALIAS = "bag_item_sts_cd";

	public static final String BAG_ITEM_TYPE_CODE_ALIAS = "bag_item_typ_cd";

	private final String productUPC;

	private final String bagItemStatus;

	private final String bagItemType;

	private final BigDecimal markdownPrice;

	public ShippingBagItemDomainDTO(Long bagItemId, String productUPC, String bagItemStatus, String bagItemType,
			BigDecimal markdownPrice) {
		super();
		this.productUPC = productUPC;
		this.bagItemStatus = bagItemStatus;
		this.bagItemType = bagItemType;
		this.markdownPrice = markdownPrice;
	}

	public ShippingBagItemDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagItemStatus = tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_CODE_ALIAS)].toString();
		this.bagItemType = tuple[aliasToIndexMap.get(BAG_ITEM_TYPE_CODE_ALIAS)].toString();
		this.productUPC = tuple[aliasToIndexMap.get(BAG_ITEM_UPC_ALIAS)].toString();
		this.markdownPrice = ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_PRICE_ALIAS)]);
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

}
