package io.nzbee.entity.bag.status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bag_item_status", schema = "mochi")
public class BagItemStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "bagItemStatusIdGenerator")
	@SequenceGenerator(name="bagItemStatusIdGenerator", sequenceName = "bag_item_status_bag_item_sts_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="bag_item_sts_id")
	private Long bagStatusId;

	@Column(name="bag_item_sts_cd")
	private String bagStatusCode;
	
	@Column(name="bag_item_sts_desc")
	private String bagStatusDesc;

	public Long getbagStatusId() {
		return bagStatusId;
	}

	public void setId(Long bagStatusId) {
		this.bagStatusId = bagStatusId;
	}

	public String getCode() {
		return bagStatusCode;
	}

	public void setCode(String bagStatusCode) {
		this.bagStatusCode = bagStatusCode;
	}

	public String getDesc() {
		return bagStatusDesc;
	}

	public void setDesc(String bagStatusDesc) {
		this.bagStatusDesc = bagStatusDesc;
	}

}
