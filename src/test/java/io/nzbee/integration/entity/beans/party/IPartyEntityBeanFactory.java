package io.nzbee.integration.entity.beans.party;

import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.integration.entity.beans.IEntityBeanFactory;
import io.nzbee.security.user.User;

public interface IPartyEntityBeanFactory extends IEntityBeanFactory<PersonEntity> {

	User getUserBean();

}
