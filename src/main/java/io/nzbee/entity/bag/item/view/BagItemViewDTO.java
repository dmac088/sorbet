package io.nzbee.entity.bag.item.view;

import java.util.Map;
import java.util.Objects;

import io.nzbee.entity.bag.view.BagViewDTO;
import io.nzbee.entity.product.physical.view.PhysicalProductDTO;

public class BagItemViewDTO {
	
	public static final String ID_ALIAS = "bag_item_id";
	
    public static final String QUANTITY_ALIAS = "qty";
    
    public static final String BAG_ITEM_STATUS_CODE_ALIAS = "bag_item_sts_cd";
    
    public static final String BAG_ITEM_STATUS_DESC_ALIAS = "bag_item_sts_desc";

	private final Long bagItemId;
	
	private final BagViewDTO bag;
	
	private final PhysicalProductDTO product;
	
	private final String bagItemStatusCode;
	
	private final String bagItemStatusDesc; 
	
	private int quantity;

	public BagItemViewDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bag = new BagViewDTO(tuple, aliasToIndexMap);
		this.product = new PhysicalProductDTO(tuple, aliasToIndexMap);
		
		this.bagItemId 			= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.bagItemStatusCode 	= tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_CODE_ALIAS)].toString();
		this.bagItemStatusDesc 	= tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_DESC_ALIAS)].toString();
		this.quantity			= ((Number) tuple[aliasToIndexMap.get(QUANTITY_ALIAS)]).intValue();
	}

	public Long getBagItemId() {
		return bagItemId;
	}

	public BagViewDTO getBag() {
		return bag;
	}

	public PhysicalProductDTO getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getBagItemStatusCode() {
		return bagItemStatusCode;
	}

	public String getBagItemStatusDesc() {
		return bagItemStatusDesc;
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

}
