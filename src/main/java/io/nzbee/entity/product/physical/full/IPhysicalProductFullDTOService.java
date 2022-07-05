package io.nzbee.entity.product.physical.full;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component(value="physicalProductFullMapper")
public interface IPhysicalProductFullDTOService {

	Optional<PhysicalProductFullDTO> findByCode(String locale, String currency, String code);

}
