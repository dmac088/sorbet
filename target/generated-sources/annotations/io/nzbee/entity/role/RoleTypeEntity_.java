package io.nzbee.entity.role;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoleTypeEntity.class)
public abstract class RoleTypeEntity_ {

	public static volatile SingularAttribute<RoleTypeEntity, Long> roleTypeId;
	public static volatile SingularAttribute<RoleTypeEntity, String> roleTypeDesc;

	public static final String ROLE_TYPE_ID = "roleTypeId";
	public static final String ROLE_TYPE_DESC = "roleTypeDesc";

}

