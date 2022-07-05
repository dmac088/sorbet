package io.nzbee.entity.bag.item.entity;

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
	
	@Column(name="qty")
	private int quantity;
	
	public Long getBagItemId() {
		return bagItemId;
	}
	
	public BagItemEntity() {
	
	}
	
	public BagItemEntity(ProductEntity p) {
		this.product = p;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
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
