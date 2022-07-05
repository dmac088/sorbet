package io.nzbee.integration.entity.beans.bag;

import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.party.Party;
import io.nzbee.integration.entity.beans.IEntityBeanFactory;

public interface IBagEntityBeanFactory extends IEntityBeanFactory<BagEntity> {

	BagEntity getBean(Party customer);

}
