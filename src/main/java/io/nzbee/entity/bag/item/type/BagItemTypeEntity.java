package io.nzbee.entity.bag.item.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bag_item_type", schema = "mochi")
public class BagItemTypeEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "bagItemTypeIdGenerator")
	@SequenceGenerator(name="bagItemTypeIdGenerator", sequenceName = "bag_item_type_bag_item_typ_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="bag_item_typ_id")
	
	private Long bagItemTypeId;
	@Column(name="bag_item_typ_cd")
	private String bagItemTypeCode;
	
	@Column(name="bag_item_typ_desc")
	private String bagItemTypeDesc;

	public Long getBagItemTypeId() {
		return bagItemTypeId;
	}

	public String getBagItemTypeCode() {
		return bagItemTypeCode;
	}

	public void setBagItemTypeCode(String bagItemTypeCode) {
		this.bagItemTypeCode = bagItemTypeCode;
	}

	public String getBagItemTypeDesc() {
		return bagItemTypeDesc;
	}

	public void setBagItemTypeDesc(String bagItemTypeDesc) {
		this.bagItemTypeDesc = bagItemTypeDesc;
	}
	
}
