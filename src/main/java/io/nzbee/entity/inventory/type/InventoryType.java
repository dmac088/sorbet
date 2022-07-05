package io.nzbee.entity.inventory.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_transaction_type", schema = "mochi")
public class InventoryType {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "inventoryTransactionTypeIdGenerator")
	@SequenceGenerator(name="inventoryTransactionTypeIdGenerator", sequenceName = "inventory_transaction_type_inv_trx_typ_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="inv_trx_typ_id")
	private Long inventoryTypeId;
	
	@Column(name="inv_trx_typ_cd")
	private String inventoryTypeCode;

	public Long getInventoryTypeId() {
		return inventoryTypeId;
	}

	public void setInventoryTypeId(Long inventoryTypeId) {
		this.inventoryTypeId = inventoryTypeId;
	}

	public String getInventoryTypeCode() {
		return inventoryTypeCode;
	}

	public void setInventoryTypeCode(String inventoryTypeCode) {
		this.inventoryTypeCode = inventoryTypeCode;
	}
	
}
