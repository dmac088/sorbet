package io.nzbee.entity.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.value.CouponCode;
import io.nzbee.domain.promotion.value.PromotionCode;
import io.nzbee.domain.promotion.value.PromotionTypeCode;
import io.nzbee.entity.promotion.bngn.IPromotionBngnMapper;
import io.nzbee.entity.promotion.bngn.PromotionBngnDTO;
import io.nzbee.entity.promotion.disc.IPromotionDiscMapper;
import io.nzbee.entity.promotion.disc.PromotionDiscDTO;
import io.nzbee.entity.promotion.valdisc.IPromotionDTO;
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
	public Promotion DTOToDo(IPromotionDTO dto) {
		if(dto instanceof PromotionBngnDTO) {
			return this.DTOToDo((PromotionBngnDTO) dto);
		}
		
		if(dto instanceof PromotionDiscDTO) {
			return this.DTOToDo((PromotionDiscDTO) dto);
		}
		
		if(dto instanceof PromotionValDiscDTO) {
			return this.DTOToDo((PromotionValDiscDTO) dto);
		}
		return this.DTOToDo((PromotionDomainDTO) dto);
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
	public Promotion DTOToDo(PromotionDomainDTO dto) {
		return new Promotion(new PromotionCode(dto.getPromotionCode()),
							 new PromotionTypeCode(dto.getPromotionType().typeCode()), 
							 dto.getPromotionStartDate(), 
							 dto.getPromotionEndDate(),
							 dto.getPromotionIsActive(), 
							 dto.getCouponRequired(), 
							 new CouponCode(dto.getCouponCode()));

	}

}
