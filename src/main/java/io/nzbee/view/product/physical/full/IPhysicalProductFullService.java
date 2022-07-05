package io.nzbee.view.product.physical.full;

import java.util.Optional;

import io.nzbee.view.IViewService;

public interface IPhysicalProductFullService extends IViewService<PhysicalProductFullView> {

	Optional<PhysicalProductFullView> findByCode(String locale, String currency, String code);

}
