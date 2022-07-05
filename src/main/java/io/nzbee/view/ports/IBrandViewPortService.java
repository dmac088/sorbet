package io.nzbee.view.ports;

import java.util.List;

import io.nzbee.view.product.brand.BrandView;

public interface IBrandViewPortService {

	List<BrandView> findByAllShippingProviders(String locale);


}
