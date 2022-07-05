package io.nzbee.entity.party.address.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "address_type", schema = "mochi")
public class AddressTypeEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "partyAddressTypeIdGenerator")
	@SequenceGenerator(name="partyAddressTypeIdGenerator", sequenceName = "address_type_addr_typ_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="addr_typ_id")
	private Long addressTypeId;
	
	@Column(name="addr_typ_cd")
	private String addressTypeCode;
	
	@Column(name="addr_typ_desc")
	private String addressTypeDesc;

	public Long getAddressTypeId() {
		return addressTypeId;
	}

	public void setAddressTypeId(Long addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public String getAddressTypeCode() {
		return addressTypeCode;
	}

	public void setAddressTypeCode(String addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}

	public String getAddressTypeDesc() {
		return addressTypeDesc;
	}

	public void setAddressTypeDesc(String addressTypeDesc) {
		this.addressTypeDesc = addressTypeDesc;
	}

}
