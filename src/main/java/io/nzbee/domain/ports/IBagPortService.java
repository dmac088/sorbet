package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;

public interface IBagPortService  extends IDomainPortService<Bag> {

	Bag findByCode(String locale, String currency, String userName);

	void save(Bag domainObject);

}
