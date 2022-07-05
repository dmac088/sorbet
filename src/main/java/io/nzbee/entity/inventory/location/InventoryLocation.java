package io.nzbee.entity.inventory.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_location", schema = "mochi")
public class InventoryLocation {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "inventoryTransactionLocationIdGenerator")
	@SequenceGenerator(name="inventoryTransactionLocationIdGenerator", sequenceName = "inventory_location_inv_loc_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="inv_loc_id")
	private Long locationId;
	
	@Column(name="inv_loc_cd")
	private String locationCode;
	
	@Column(name="inv_loc_desc")
	private String locationDesc;
	
	@Column(name="inv_loc_act")
	private boolean locationIsActive;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public boolean getLocationIsActive() {
		return locationIsActive;
	}

	public void setLocationIsActive(boolean locationIsActive) {
		this.locationIsActive = locationIsActive;
	}
	
	
}
