package io.nzbee.entity.role;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.role.customer.CustomerEntity;
import io.nzbee.entity.role.supplier.SupplierEntity;


@Entity
@Table(name = "role", schema = "mochi") 
public class RoleEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "roleIdGenerator")
	@SequenceGenerator(name="roleIdGenerator", sequenceName = "role_rle_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="rle_id")
	private Long roleId;
	
	@Column(name="rle_start_dt")
	private Date RoleStart; 
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="rle_typ_id")
	private RoleTypeEntity roleType;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional=false)
	@JoinColumn(name="pty_id", nullable=false)
	private Party roleParty;

	@OneToOne(mappedBy="customerRole",
			 fetch = FetchType.LAZY,
			 cascade = CascadeType.ALL,
			 orphanRemoval = true)
	private CustomerEntity roleCustomer;
	
	@OneToOne(mappedBy="supplierRole",
			 fetch = FetchType.LAZY,
			 cascade = CascadeType.ALL,
			 orphanRemoval = true)
	private SupplierEntity roleSupplier;
	
	public RoleEntity() {
		
	}
		
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public RoleTypeEntity getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleTypeEntity roleType) {
		this.roleType = roleType;
	}
	
	public Party getRoleParty() {
		return roleParty;
	}

	public void setRoleParty(Party roleParty) {
		this.roleParty = roleParty;
	}

	public Date getRoleStart() {
		return RoleStart;
	}

	public void setRoleStart(Date roleStart) {
		RoleStart = roleStart;
	}
	
	public String getClassName() {
		return this.getClass().toString();
	}

	public CustomerEntity getRoleCustomer() {
		return roleCustomer;
	}

	public void setRoleCustomer(CustomerEntity roleCustomer) {
		this.roleCustomer = roleCustomer;
	}

	public SupplierEntity getRoleSupplier() {
		return roleSupplier;
	}

	public void setRoleSupplier(SupplierEntity roleSupplier) {
		this.roleSupplier = roleSupplier;
	}
	
}
