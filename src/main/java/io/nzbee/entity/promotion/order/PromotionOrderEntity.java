package io.nzbee.entity.promotion.order;

import java.io.Serializable;
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
@Table(name = "promotion_order", schema = "mochi")
public class PromotionOrderEntity implements Serializable {

	private static final long serialVersionUID = 9148075546941323177L;
	
	@Id
	@Column(name="prm_id")
	private Long promotionId;
	
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="prm_id")
	private PromotionEntity orderPromotion;
	
	@Column(name="prm_cpn_cd")
	private String promotionCouponCode;

	public String getPromotionCouponCode() {
		return promotionCouponCode;
	}

	public void setPromotionCouponCode(String promotionCouponCode) {
		this.promotionCouponCode = promotionCouponCode;
	}

	public PromotionEntity getOrderPromotion() {
		return orderPromotion;
	}

	public void setOrderPromotion(PromotionEntity orderPromotion) {
		this.orderPromotion = orderPromotion;
	}
	
}
