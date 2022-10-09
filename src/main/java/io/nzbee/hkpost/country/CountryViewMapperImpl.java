package io.nzbee.hkpost.country;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import io.nzbee.view.product.shipping.country.ShippingCountryView;

@Component
public class CountryViewMapperImpl implements ICountryViewMapper {

	
	@Override
	public List<ShippingCountryView> toView(CountryResponseDTO dto) {
		return dto.getData().stream().map(c -> { 
			ShippingCountryView scv = new ShippingCountryView();
			scv.setProductDestinationCode(c.getHkpCtyCode());
			scv.setProductDestinationDesc(c.getNameE());
			return scv;
		}).collect(Collectors.toList());
	}
	
}
