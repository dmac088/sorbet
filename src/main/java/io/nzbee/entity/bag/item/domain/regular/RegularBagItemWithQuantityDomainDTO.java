package io.nzbee.entity.bag.item.domain.regular;

import java.math.BigDecimal;
import java.util.Map;

public class RegularBagItemWithQuantityDomainDTO {

	public static final String ID_ALIAS = "bag_item_id";
	
    public static final String BAG_ITEM_STATUS_CODE_ALIAS = "bag_item_sts_cd";
    
    public static final String BAG_ITEM_WEIGHT_ALIAS = "weight";
    
    public static final String BAG_ITEM_QUANTITY_ALIAS = "qty";
    
    public static final String BAG_ITEM_UPC_ALIAS = "upc_cd";
    
    public static final String BAG_ITEM_PRICE_ALIAS = "prc_val";
    
    public static final String BAG_ITEM_INSTOCK_ALIAS = "in_stock";
    

    private final Long bagItemId;
    
	private final String productUPC;
	
	private final String bagItemStatus;
	
	private final BigDecimal markdownPrice;
	
	private final BigDecimal weight;
	
	private final Boolean inStock;

	private final int quantity;
	
	public RegularBagItemWithQuantityDomainDTO(	Long bagItemId, 
												String productUPC,  
												String bagItemStatus,
												BigDecimal markdownPrice, 
												BigDecimal weight,
												boolean inStock,
												int quantity) {
		super();
		this.bagItemId		= bagItemId;
		this.productUPC 	= productUPC;
		this.bagItemStatus 	= bagItemStatus;
		this.markdownPrice 	= markdownPrice;
		this.weight 		= weight;
		this.inStock 		= inStock;
		this.quantity		= quantity;
	}
	

	public RegularBagItemWithQuantityDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagItemId 			= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.bagItemStatus 		= tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_CODE_ALIAS)].toString();
		this.weight				= ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_WEIGHT_ALIAS)]);
		this.quantity 			= ((Number) tuple[aliasToIndexMap.get(BAG_ITEM_QUANTITY_ALIAS)]).intValue();
		this.productUPC 		= tuple[aliasToIndexMap.get(BAG_ITEM_UPC_ALIAS)].toString();
		this.markdownPrice 		= ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_PRICE_ALIAS)]);
		this.inStock 			= ((boolean) (tuple[aliasToIndexMap.get(BAG_ITEM_INSTOCK_ALIAS)]));
	}

	public Long getBagItemId() {
		return bagItemId;
	}
	
	public String getProductUPC() {
		return productUPC;
	}

	public String getBagItemStatus() {
		return bagItemStatus;
	}

	public BigDecimal getMarkdownPrice() {
		return markdownPrice;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public boolean isInStock() {
		return inStock;
	}

	public int getQuantity() {
		return quantity;
	}
	
}
