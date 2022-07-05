package io.nzbee.integration.view.beans.bag;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.customer.Customer;
import io.nzbee.integration.view.beans.IViewBeanFactory;

public interface IBagViewBeanFactory extends IViewBeanFactory<Bag> {

	Bag getBean(Customer c);

}
