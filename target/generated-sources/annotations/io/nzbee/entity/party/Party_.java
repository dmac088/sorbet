package io.nzbee.entity.party;

import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.party.organization.OrganizationEntity;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.party.type.PartyTypeEntity;
import io.nzbee.entity.role.RoleEntity;
import io.nzbee.security.user.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Party.class)
public abstract class Party_ {

	public static volatile SingularAttribute<Party, PersonEntity> partyPerson;
	public static volatile SingularAttribute<Party, OrganizationEntity> partyOrganisation;
	public static volatile SingularAttribute<Party, BagEntity> bag;
	public static volatile SingularAttribute<Party, Long> partyId;
	public static volatile SingularAttribute<Party, PartyTypeEntity> partyType;
	public static volatile SetAttribute<Party, RoleEntity> partyRoles;
	public static volatile SingularAttribute<Party, User> partyUser;

	public static final String PARTY_PERSON = "partyPerson";
	public static final String PARTY_ORGANISATION = "partyOrganisation";
	public static final String BAG = "bag";
	public static final String PARTY_ID = "partyId";
	public static final String PARTY_TYPE = "partyType";
	public static final String PARTY_ROLES = "partyRoles";
	public static final String PARTY_USER = "partyUser";

}

