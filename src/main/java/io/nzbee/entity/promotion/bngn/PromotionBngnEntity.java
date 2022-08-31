package io.nzbee.entity.promotion.bngn;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import io.nzbee.entity.promotion.PromotionEntity;

@Entity
@Table(name = "promotion_bngn", schema = "mochi")
public class PromotionBngnEntity implements Serializable {

	private static final long serialVersionUID = 9148075546941323177L;
	
	@Id
	@Column(name="prm_id")
	private Long promotionId;
	
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="prm_id")
	private PromotionEntity bngnPromotion;
	
	@Column(name="bu_qty")
	private Long buyQuantity;
	
	@Column(name="disc_pctg")
	private BigDecimal discountPercentage;


	public PromotionEntity getOrderPromotion() {
		return bngnPromotion;
	}

	public void setOrderPromotion(PromotionEntity bngnPromotion) {
		this.bngnPromotion = bngnPromotion;
	}

	public Long getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Long buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
}
