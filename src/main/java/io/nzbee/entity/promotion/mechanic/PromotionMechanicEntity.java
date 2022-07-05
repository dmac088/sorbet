package io.nzbee.entity.promotion.mechanic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "promotion_mechanic", schema = "mochi")
public class PromotionMechanicEntity implements Serializable {

	private static final long serialVersionUID = -4306692799747340776L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "promotionMechanicIdGenerator")
	@SequenceGenerator(name="promotionMechanicIdGenerator", sequenceName = "promotion_mechanic_prm_mec_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="prm_mec_id")
	private Long promotionMechanicId;
	
	@NaturalId
	@Column(name="prm_mec_cd", unique = true, updatable = false)
	private String promotionMechanicCode;
	
	@Column(name="prm_mec_desc")
	private String promotionMechanicDesc;

	public Long getPromotionMechanicId() {
		return promotionMechanicId;
	}

	public void setPromotionMechanicId(Long promotionMechanicId) {
		this.promotionMechanicId = promotionMechanicId;
	}

	public String getPromotionMechanicCode() {
		return promotionMechanicCode;
	}

	public void setPromotionMechanicCode(String promotionMechanicCode) {
		this.promotionMechanicCode = promotionMechanicCode;
	}

	public String getPromotionMechanicDesc() {
		return promotionMechanicDesc;
	}

	public void setPromotionMechanicDesc(String promotionMechanicDesc) {
		this.promotionMechanicDesc = promotionMechanicDesc;
	}
	
}
