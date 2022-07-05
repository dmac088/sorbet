package io.nzbee.entity.role;

import io.nzbee.entity.party.Party;
import io.nzbee.entity.role.customer.CustomerEntity;
import io.nzbee.entity.role.supplier.SupplierEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoleEntity.class)
public abstract class RoleEntity_ {

	public static volatile SingularAttribute<RoleEntity, SupplierEntity> roleSupplier;
	public static volatile SingularAttribute<RoleEntity, Long> roleId;
	public static volatile SingularAttribute<RoleEntity, CustomerEntity> roleCustomer;
	public static volatile SingularAttribute<RoleEntity, Party> roleParty;
	public static volatile SingularAttribute<RoleEntity, Date> RoleStart;
	public static volatile SingularAttribute<RoleEntity, RoleTypeEntity> roleType;

	public static final String ROLE_SUPPLIER = "roleSupplier";
	public static final String ROLE_ID = "roleId";
	public static final String ROLE_CUSTOMER = "roleCustomer";
	public static final String ROLE_PARTY = "roleParty";
	public static final String ROLE_START = "RoleStart";
	public static final String ROLE_TYPE = "roleType";

}

