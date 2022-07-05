package io.nzbee.entity.promotion.type;

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
@Table(name = "promotion_type", schema = "mochi")
public class PromotionTypeEntity implements Serializable {
	
	private static final long serialVersionUID = -4218197340727931791L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "promotionTypeIdGenerator")
	@SequenceGenerator(name="promotionTypeIdGenerator", sequenceName = "promotion_type_prm_typ_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="prm_typ_id")
	private Long promotionTypeId;
	
	@NaturalId
	@Column(name="prm_typ_cd", unique = true, updatable = false)
	private String promotionTypeCode;
	
	@Column(name="prm_typ_desc")
	private String promotionTypeDesc;
	
	@Column(name="prm_class")
	private String promotionClass;

	public Long getPromotionTypeId() {
		return promotionTypeId;
	}

	public void setPromotionTypeId(Long promotionTypeId) {
		this.promotionTypeId = promotionTypeId;
	}

	public String getPromotionTypeCode() {
		return promotionTypeCode;
	}

	public void setPromotionTypeCode(String promotionTypeCode) {
		this.promotionTypeCode = promotionTypeCode;
	}

	public String getPromotionTypeDesc() {
		return promotionTypeDesc;
	}

	public void setPromotionTypeDesc(String promotionTypeDesc) {
		this.promotionTypeDesc = promotionTypeDesc;
	}

	public String getPromotionClass() {
		return promotionClass;
	}

	public void setPromotionClass(String promotionClass) {
		this.promotionClass = promotionClass;
	}

}
