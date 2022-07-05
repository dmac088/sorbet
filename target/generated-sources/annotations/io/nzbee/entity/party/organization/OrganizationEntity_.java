package io.nzbee.entity.party.organization;

import io.nzbee.entity.party.Party;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrganizationEntity.class)
public abstract class OrganizationEntity_ {

	public static volatile SingularAttribute<OrganizationEntity, Long> organisationId;
	public static volatile SingularAttribute<OrganizationEntity, String> organizationName;
	public static volatile SingularAttribute<OrganizationEntity, Party> organisationParty;

	public static final String ORGANISATION_ID = "organisationId";
	public static final String ORGANIZATION_NAME = "organizationName";
	public static final String ORGANISATION_PARTY = "organisationParty";

}

