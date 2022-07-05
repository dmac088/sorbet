package io.nzbee.entity.role.supplier;

import io.nzbee.entity.role.RoleEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SupplierEntity.class)
public abstract class SupplierEntity_ {

	public static volatile SingularAttribute<SupplierEntity, Long> supplierId;
	public static volatile SingularAttribute<SupplierEntity, String> supplierNumber;
	public static volatile SingularAttribute<SupplierEntity, RoleEntity> supplierRole;

	public static final String SUPPLIER_ID = "supplierId";
	public static final String SUPPLIER_NUMBER = "supplierNumber";
	public static final String SUPPLIER_ROLE = "supplierRole";

}

