package io.nzbee.entity.role.supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import io.nzbee.entity.role.RoleEntity;

@Entity
@Table(name = "supplier", schema = "mochi")
public class SupplierEntity {
	
	@Transient
	private Long roleTypeId = (long) 2;
	
	@Id
	@Column(name="rle_id")
	private Long supplierId;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "supplierIdGenerator")
	@SequenceGenerator(name="supplierIdGenerator", sequenceName = "supplier_sup_num_seq", schema="mochi",  allocationSize=1)
	@Column(name="sup_num", insertable = false)
	private String supplierNumber;
	
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="rle_id")
	private RoleEntity supplierRole;

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public RoleEntity getSupplierRole() {
		return supplierRole;
	}

	public void setSupplierRole(RoleEntity supplierRole) {
		this.supplierRole = supplierRole;
	}
	
}
