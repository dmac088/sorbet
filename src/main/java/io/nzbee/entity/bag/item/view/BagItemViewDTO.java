package io.nzbee.entity.bag.item.view;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import io.nzbee.entity.bag.view.BagViewDTO;

public class BagItemViewDTO {
	
	public static final String ID_ALIAS = "bag_item_id";
	
    public static final String QUANTITY_ALIAS = "qty";
    
    public static final String BAG_ITEM_STATUS_CODE_ALIAS = "bag_item_sts_cd";
    
    public static final String BAG_ITEM_TYPE_CODE_ALIAS = "bag_item_typ_cd";
    
    public static final String BAG_ITEM_STATUS_DESC_ALIAS = "bag_item_sts_desc";
    
    public static final String BAG_ITEM_DESC_ALIAS = "bag_item_desc";
    
    public static final String BAG_ITEM_DISCOUNT_ALIAS = "bag_item_dis_pctg";
    
    public static final String BAG_ITEM_TOTAL_WEIGHT_ALIAS = "bag_item_tot_wgt";
    
    public static final String BAG_ITEM_UPC_ALIAS = "upc_cd";
    
    private static final String BAG_ITEM_MARKDOWN_PRICE_ALIAS = "markdown_price";
    

	private final Long bagItemId;
	
	private final BagViewDTO bag;
	
	private final String bagItemStatusCode;
	
	private final String bagItemTypeCode;
	
	private final String bagItemStatusDesc; 
	
	private final String bagItemDesc; 
	
	private final Long quantity;
	
	private final BigDecimal bagItemDiscountPercentage;
	
	private final BigDecimal bagItemTotalWeight;
	
	private final String bagItemUPC;
	
	private final BigDecimal markdownPrice;
	

	public BagItemViewDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bag = new BagViewDTO(tuple, aliasToIndexMap);
		this.bagItemId 				= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.bagItemStatusCode 		= tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_CODE_ALIAS)].toString();
		this.bagItemTypeCode 		= tuple[aliasToIndexMap.get(BAG_ITEM_TYPE_CODE_ALIAS)].toString();
		this.bagItemStatusDesc 		= tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_DESC_ALIAS)].toString();
		this.bagItemDesc 			= tuple[aliasToIndexMap.get(BAG_ITEM_DESC_ALIAS)].toString();
		this.quantity				= ((Number) tuple[aliasToIndexMap.get(QUANTITY_ALIAS)]).longValue();
		this.bagItemDiscountPercentage	= ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_DISCOUNT_ALIAS)]);
		this.bagItemUPC				= tuple[aliasToIndexMap.get(BAG_ITEM_UPC_ALIAS)].toString();
		this.markdownPrice			= ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_MARKDOWN_PRICE_ALIAS)]);
		this.bagItemTotalWeight		= ((BigDecimal) tuple[aliasToIndexMap.get(BAG_ITEM_TOTAL_WEIGHT_ALIAS)]);
	}

	public Long getBagItemId() {
		return bagItemId;
	}

	public BagViewDTO getBag() {
		return bag;
	}
	
	public Long getQuantity() {
		return quantity;
	}

	public String getBagItemStatusCode() {
		return bagItemStatusCode;
	}

	public String getBagItemStatusDesc() {
		return bagItemStatusDesc;
	}
	
	public String getBagItemTypeCode() {
		return bagItemTypeCode;
	}
	
	public String getBagItemDesc() {
		return bagItemDesc;
	}

	public String getBagItemUPC() {
		return bagItemUPC;
	}

	public BigDecimal getMarkdownPrice() {
		return markdownPrice;
	}

	public BigDecimal getBagTotalWeight() {
		return bagItemTotalWeight;
	}


	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     BagItemViewDTO that = (BagItemViewDTO) o;
	     return this.getBagItemId() == that.getBagItemId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getBagItemId());
	}

	public BigDecimal getBagItemDiscountPercentage() {
		return bagItemDiscountPercentage;
	}

}
