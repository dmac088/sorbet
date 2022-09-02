package io.nzbee.entity.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.bngn.IPromotionBngnMapper;
import io.nzbee.entity.promotion.bngn.PromotionBngnDTO;
import io.nzbee.entity.promotion.disc.IPromotionDiscMapper;
import io.nzbee.entity.promotion.disc.PromotionDiscDTO;
import io.nzbee.entity.promotion.valdisc.IPromotionValDiscMapper;
import io.nzbee.entity.promotion.valdisc.PromotionValDiscDTO;

@Component(value = "promotionMapper")
public class PromotionMapperImpl implements IPromotionMapper {

	@Autowired
	private IPromotionBngnMapper promotionBngnMapper;
	
	@Autowired
	private IPromotionDiscMapper promotionDiscMapper;
	
	@Autowired
	private IPromotionValDiscMapper promotionValDiscMapper;
	
	@Override
	public Promotion DTOToDo(PromotionDomainDTO dto) {
		if(dto instanceof PromotionBngnDTO) {
			return this.DTOToDo((PromotionBngnDTO) dto);
		}
		
		if(dto instanceof PromotionDiscDTO) {
			return this.DTOToDo((PromotionDiscDTO) dto);
		}
		
		if(dto instanceof PromotionValDiscDTO) {
			return this.DTOToDo((PromotionValDiscDTO) dto);
		}
		return null;
	}

	@Override
	public Promotion DTOToDo(PromotionBngnDTO dto) {
		return promotionBngnMapper.DTOToDo(dto);
	}
	
	@Override
	public Promotion DTOToDo(PromotionDiscDTO dto) {
		return promotionDiscMapper.DTOToDo(dto);
	}
	
	@Override
	public Promotion DTOToDo(PromotionValDiscDTO dto) {
		return promotionValDiscMapper.DTOToDo(dto);
	}
		
	@Override
	public PromotionEntity doToEntity(Promotion d) {
		// TODO Auto-generated method stub
		return null;
	}



}
