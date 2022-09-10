package io.nzbee.entity.bag.item.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.bag.item.type.BagItemTypeEntity;
import io.nzbee.entity.bag.status.BagItemStatus;
import io.nzbee.entity.product.ProductEntity;

@Entity
@Table(name = "bag_item", schema = "mochi")
public class BagItemEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "bagItemIdGenerator")
	@SequenceGenerator(name="bagItemIdGenerator", sequenceName = "bag_item_bag_item_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="bag_item_id")
	private Long bagItemId;

	@ManyToOne
	@JoinColumn(name="bag_id")
	private BagEntity bag;
	
	@ManyToOne
	@JoinColumn(name="prd_id")
	private ProductEntity product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bag_item_sts_id")
	private BagItemStatus bagItemStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bag_item_typ_id")
	private BagItemTypeEntity bagItemType;
	
	@Column(name="qty")
	private Long quantity;
	
	@Column(name="bag_item_bse_amt")
	private BigDecimal bagItemBaseAmount;
	
	@Column(name="bag_item_dis_amt")
	private BigDecimal bagItemDiscountAmount;
	
	@Column(name="bag_item_tot_amt")
	private BigDecimal bagItemTotalAmount;
	
	@Column(name="bag_item_tot_wgt")
	private BigDecimal bagTotalWeight;
	
	public Long getBagItemId() {
		return bagItemId;
	}
	
	public BagItemEntity() {
	
	}
	
	public BagItemEntity(ProductEntity p) {
		this.product = p;
	}
	
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BagEntity getBag() {
		return bag;
	}

	public void setBag(BagEntity bag) {
		this.bag = bag;
	}
	
	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	public BagItemStatus getBagItemStatus() {
		return bagItemStatus;
	}

	public void setBagItemStatus(BagItemStatus bagItemStatus) {
		this.bagItemStatus = bagItemStatus;
	}
	
	public BagItemTypeEntity getBagItemType() {
		return bagItemType;
	}

	public void setBagItemType(BagItemTypeEntity bagItemTypeEntity) {
		this.bagItemType = bagItemTypeEntity;
	}
	
	public BigDecimal getBagItemBaseAmount() {
		return bagItemBaseAmount;
	}

	public void setBagItemBaseAmount(BigDecimal bagItemBaseAmount) {
		this.bagItemBaseAmount = bagItemBaseAmount;
	}

	public BigDecimal getBagItemDiscountAmount() {
		return bagItemDiscountAmount;
	}

	public void setBagItemDiscountAmount(BigDecimal bagItemDiscountAmount) {
		this.bagItemDiscountAmount = bagItemDiscountAmount;
	}

	public BigDecimal getBagItemTotalAmount() {
		return bagItemTotalAmount;
	}

	public void setBagItemTotalAmount(BigDecimal bagItemTotalAmount) {
		this.bagItemTotalAmount = bagItemTotalAmount;
	}

	public BigDecimal getBagTotalWeight() {
		return bagTotalWeight;
	}

	public void setBagTotalWeight(BigDecimal bagTotalWeight) {
		this.bagTotalWeight = bagTotalWeight;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BagItemEntity)) return false;
        return bagItemId != null && bagItemId.equals(((BagItemEntity) o).getBagItemId());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getBagItemId());
    }
}
