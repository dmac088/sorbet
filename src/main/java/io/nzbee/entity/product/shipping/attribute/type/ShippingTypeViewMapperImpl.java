package io.nzbee.entity.product.shipping.attribute.type;

import org.springframework.stereotype.Component;

import io.nzbee.view.product.shipping.type.ShippingTypeView;

@Component
public class ShippingTypeViewMapperImpl implements IShippingTypeViewMapper {

	@Override
	public ShippingTypeView toView(ShippingTypeDTO d) {
		ShippingTypeView stv = new ShippingTypeView();
		stv.setShippingTypeCode(d.getShippingTypeCode());
		stv.setShippingTypeDesc(d.getShippingTypeDesc());
		return stv;
	}

}
