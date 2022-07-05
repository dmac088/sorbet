package io.nzbee.domain.ports;

import io.nzbee.domain.bag.item.BagItem;
import io.nzbee.domain.customer.Customer;

public interface ICustomerPortService {

	void addItemToBag(String locale, Customer c, BagItem bagItem);

	Customer findByUsername(String locale, String userName);

}
