package io.nzbee.entity.inventory;

import java.time.LocalDateTime;
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
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.entity.inventory.type.InventoryType;
import io.nzbee.entity.party.organization.OrganizationEntity;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.currency.Currency;

@Entity
@Table(name = "inventory_transaction", schema = "mochi")
public class InventoryTransaction {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "inventoryTransactionIdGenerator")
	@SequenceGenerator(name="inventoryTransactionIdGenerator", sequenceName = "inventory_transaction_inv_trx_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="inv_trx_id")
	private Long inventroyTransactionId;
	
	@ManyToOne
	@JoinColumn(name="inv_loc_id")
	private InventoryLocation inventoryLocation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="inv_prd_id")
	private ProductEntity product;
	
	@Column(name="inv_qty")
	private Long quantity;
	
	@Column(name="inv_prc")
	private Double price;
	
	@ManyToOne
	@JoinColumn(name="inv_ccy_id")
	private Currency currency;
	
	@ManyToOne
	@JoinColumn(name="inv_trx_typ_id")
	private InventoryType inventoryType;
	
	@ManyToOne
	@JoinColumn(name="inv_pty_id")
	private OrganizationEntity supplier;
	
	@Column(name="inv_trx_dt")
	private LocalDateTime inventoryTransactionDate;
	
	public Long getInventroyTransactionId() {
		return inventroyTransactionId;
	}

	public void setInventroyTransactionId(Long inventroyTransactionId) {
		this.inventroyTransactionId = inventroyTransactionId;
	}

	public InventoryLocation getInventoryLocation() {
		return inventoryLocation;
	}

	public void setInventoryLocation(InventoryLocation inventoryLocation) {
		this.inventoryLocation = inventoryLocation;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public OrganizationEntity getSupplier() {
		return supplier;
	}

	public void setSupplier(OrganizationEntity supplier) {
		this.supplier = supplier;
	}

	public LocalDateTime getInventoryTransactionDate() {
		return inventoryTransactionDate;
	}

	public void setInventoryTransactionDate(LocalDateTime inventoryTransactionDate) {
		this.inventoryTransactionDate = inventoryTransactionDate;
	}
	
}
