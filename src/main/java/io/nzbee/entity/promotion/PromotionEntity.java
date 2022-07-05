package io.nzbee.entity.promotion;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import io.nzbee.Constants;
import io.nzbee.entity.promotion.attribute.PromotionAttributeEntity;
import io.nzbee.entity.promotion.level.PromotionLevelEntity;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicEntity;
import io.nzbee.entity.promotion.order.PromotionOrderEntity;
import io.nzbee.entity.promotion.product.PromotionProductEntity;
import io.nzbee.entity.promotion.type.PromotionTypeEntity;

@Entity
@Table(name = "promotion", schema = "mochi")
public class PromotionEntity implements Serializable {

	private static final long serialVersionUID = -8035431077837286610L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "promotionIdGenerator")
	@SequenceGenerator(name="promotionIdGenerator", sequenceName = "promotion_prm_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="prm_id")
	private Long promotionId;
	
	@NaturalId
	@Column(name="prm_cd", unique = true, updatable = false)
	private String promotionCode;
	
	@Column(name="prm_st_dt")
	private LocalDateTime promotionStartDate;
	
	@Column(name="prm_en_dt")
	private LocalDateTime promotionEndDate;
	
	@Column(name="prm_act")
	private Boolean promotionActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prm_mec_id")
	private PromotionMechanicEntity promotionMechanic;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prm_typ_id")
	private PromotionTypeEntity promotionType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prm_lvl_id")
	private PromotionLevelEntity promotionLevel;
	
	@OneToMany(	mappedBy="promotion",  
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<PromotionAttributeEntity> attributes = new HashSet<PromotionAttributeEntity>();
	
	@OneToOne(	mappedBy="orderPromotion",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private PromotionOrderEntity promotionOrder;
	
	@OneToOne(	mappedBy="productPromotion",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private PromotionProductEntity promotionProduct;

	@Transient
	private String locale;
	
	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public LocalDateTime getPromotionStartDate() {
		return promotionStartDate;
	}

	public void setPromotionStartDate(LocalDateTime promotionStartDate) {
		this.promotionStartDate = promotionStartDate;
	}

	public LocalDateTime getPromotionEndDate() {
		return promotionEndDate;
	}

	public void setPromotionEndDate(LocalDateTime promotionEndDate) {
		this.promotionEndDate = promotionEndDate;
	}

	public Boolean getPromotionActive() {
		return promotionActive;
	}

	public void setPromotionActive(Boolean promotionActive) {
		this.promotionActive = promotionActive;
	}

	public PromotionMechanicEntity getPromotionMechanic() {
		return promotionMechanic;
	}

	public void setPromotionMechanic(PromotionMechanicEntity promotionMechanic) {
		this.promotionMechanic = promotionMechanic;
	}
	
	public PromotionTypeEntity getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(PromotionTypeEntity promotionType) {
		this.promotionType = promotionType;
	}

	public Set<PromotionAttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<PromotionAttributeEntity> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(PromotionAttributeEntity promotionAttribute) {
		this.getAttributes().add(promotionAttribute);
		promotionAttribute.setPromotion(this);		
	}
	
	public void removeAttribute(PromotionAttributeEntity promotionAttribute) {
		this.getAttributes().remove(promotionAttribute);
		promotionAttribute.setPromotion(null);
	}
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public PromotionLevelEntity getPromotionLevel() {
		return promotionLevel;
	}

	public void setPromotionLevel(PromotionLevelEntity promotionLevel) {
		this.promotionLevel = promotionLevel;
	}
	
	public PromotionOrderEntity getPromotionOrder() {
		return promotionOrder;
	}

	public void setPromotionOrder(PromotionOrderEntity promotionOrder) {
		this.promotionOrder = promotionOrder;
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeENGB))
	public String getPromotionDescENGB() {
		return this.getAttributes().stream().filter(pa -> pa.getLocale().equals(Constants.localeENGB)).findFirst().get().getPromotionDesc();
	}
	
	@Transient
	@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeZHHK))
	public String getPromotionDescZHHK() {
		return this.getAttributes().stream().filter(pa -> pa.getLocale().equals(Constants.localeZHHK)).findFirst().get().getPromotionDesc();
	}
	
	public PromotionProductEntity getPromotionProduct() {
		return promotionProduct;
	}

	public void setPromotionProduct(PromotionProductEntity promotionProduct) {
		this.promotionProduct = promotionProduct;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromotionEntity)) return false;
        return promotionCode != null && promotionCode.equals(((PromotionEntity) o).getPromotionCode());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getPromotionCode());
    }
}
