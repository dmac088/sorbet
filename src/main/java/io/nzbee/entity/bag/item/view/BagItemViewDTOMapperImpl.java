package io.nzbee.entity.bag.item.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.entity.product.physical.view.IPhysicalProductDTOMapper;
import io.nzbee.view.bag.item.BagItemViewOut;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;

@Component
public class BagItemViewDTOMapperImpl implements IBagItemViewDTOMapper {
	
	@Autowired
	private IPhysicalProductDTOMapper productMapper;

	@Override
	public BagItemViewOut DTOToView(BagItemViewDTO dto) {
		PhysicalProductFullView p = productMapper.toView(dto.getProduct());
		BagItemViewOut bi = new BagItemViewOut(p, dto.getQuantity());
		return bi;
	} 

}
