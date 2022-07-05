package io.nzbee.entity.party.person;

import io.nzbee.entity.party.Party;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonEntity.class)
public abstract class PersonEntity_ {

	public static volatile SingularAttribute<PersonEntity, Party> personParty;
	public static volatile SingularAttribute<PersonEntity, String> givenName;
	public static volatile SingularAttribute<PersonEntity, String> familyName;
	public static volatile SingularAttribute<PersonEntity, Long> personId;
	public static volatile SingularAttribute<PersonEntity, Boolean> enabled;

	public static final String PERSON_PARTY = "personParty";
	public static final String GIVEN_NAME = "givenName";
	public static final String FAMILY_NAME = "familyName";
	public static final String PERSON_ID = "personId";
	public static final String ENABLED = "enabled";

}

