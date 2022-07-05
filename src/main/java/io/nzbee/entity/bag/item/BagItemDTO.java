package io.nzbee.entity.bag.item;

import java.util.Map;
import java.util.Objects;

import io.nzbee.entity.bag.view.BagViewDTO;
import io.nzbee.entity.product.physical.view.PhysicalProductDTO;

public class BagItemDTO {
	
	public static final String ID_ALIAS = "upc_cd";
	
    public static final String QUANTITY_ALIAS = "qty";
    
    public static final String BAG_ITEM_STATUS_CODE_ALIAS = "bag_item_sts_cd";
    
    public static final String BAG_ITEM_STATUS_DESC_ALIAS = "bag_item_sts_desc";

	private String bagItemId;
	
	private BagViewDTO bag;
	
	private PhysicalProductDTO product;
	
	private String bagItemStatusCode;
	
	private String bagItemStatusDesc; 
	
	private int quantity;

	public BagItemDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagItemId 			= (tuple[aliasToIndexMap.get(ID_ALIAS)]).toString();
		this.bagItemStatusCode 	= tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_CODE_ALIAS)].toString();
		this.bagItemStatusDesc 	= tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_DESC_ALIAS)].toString();
		this.quantity			= ((Number) tuple[aliasToIndexMap.get(QUANTITY_ALIAS)]).intValue();
	}

	public String getBagItemId() {
		return bagItemId;
	}

	public BagViewDTO getBag() {
		return bag;
	}

	public void setBag(BagViewDTO bag) {
		this.bag = bag;
	}

	public PhysicalProductDTO getProduct() {
		return product;
	}

	public void setProduct(PhysicalProductDTO product) {
		this.product = product;
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
	     BagItemDTO that = (BagItemDTO) o;
	     return this.getBagItemId() == that.getBagItemId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getBagItemId());
	}

}
