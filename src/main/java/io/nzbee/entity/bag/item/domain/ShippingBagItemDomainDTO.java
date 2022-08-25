package io.nzbee.entity.bag.item.domain;

import java.math.BigDecimal;
import java.util.Map;

public class ShippingBagItemDomainDTO {

//public static final String ID_ALIAS = "bag_item_id";

	public static final String BAG_ITEM_UPC_ALIAS = "upc_cd";

	public static final String BAG_ITEM_PRICE_ALIAS = "prc_val";

	public static final String BAG_ITEM_STATUS_CODE_ALIAS = "bag_item_sts_cd";

	public static final String BAG_ITEM_TYPE_CODE_ALIAS = "bag_item_typ_cd";

	public static final String BAG_ITEM_QUANTITY_ALIAS = "qty";

	// private final Long bagItemId;

	private final String productUPC;

	private final String bagItemStatus;

	private final String bagItemType;

	private final BigDecimal markdownPrice;

	private final int quantity;

	public ShippingBagItemDomainDTO(Long bagItemId, String productUPC, String bagItemStatus, String bagItemType,
			BigDecimal markdownPrice, int quantity) {
		super();
		// this.bagItemId = bagItemId;
		this.productUPC = productUPC;
		this.bagItemStatus = bagItemStatus;
		this.bagItemType = bagItemType;
		this.markdownPrice = markdownPrice;
		this.quantity = quantity;
	}

	public ShippingBagItemDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		// this.bagItemId = ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.bagItemStatus = tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_CODE_ALIAS)].toString();
		this.bagItemType = tuple[aliasToIndexMap.get(BAG_ITEM_TYPE_CODE_ALIAS)].toString();
		this.quantity = ((Number) tuple[aliasToIndexMap.get(BAG_ITEM_QUANTITY_ALIAS)]).intValue();
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

	public int getQuantity() {
		return quantity;
	}

}
