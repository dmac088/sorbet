package io.nzbee.entity.role.customer;


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
@Table(name = "customer", schema = "mochi")
public class CustomerEntity {
 
	@Transient
	private Long roleTypeId = (long) 1;
	
	@Id
	@Column(name="rle_id")
	private Long customerId;
	
	public CustomerEntity() {
		super();
	}
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "customerIdGenerator")
	@SequenceGenerator(name="customerIdGenerator", sequenceName = "customer_cst_num_seq", schema="mochi",  allocationSize=1)
	@Column(name="cst_num", insertable = false)
	private String customerNumber;
	
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="rle_id")
	private RoleEntity customerRole;

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public RoleEntity getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(RoleEntity re) {
		this.customerRole = re;
	}

}
