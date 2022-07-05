package io.nzbee.entity.role.customer;

import io.nzbee.entity.role.RoleEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerEntity.class)
public abstract class CustomerEntity_ {

	public static volatile SingularAttribute<CustomerEntity, Long> customerId;
	public static volatile SingularAttribute<CustomerEntity, String> customerNumber;
	public static volatile SingularAttribute<CustomerEntity, RoleEntity> customerRole;

	public static final String CUSTOMER_ID = "customerId";
	public static final String CUSTOMER_NUMBER = "customerNumber";
	public static final String CUSTOMER_ROLE = "customerRole";

}

