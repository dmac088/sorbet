package io.nzbee.entity.product.shipping.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.shipping.attribute.entity.ShippingProductAttributeEntity;

@Entity
@Table(name = "product_shipping", schema = "mochi")
public class ShippingProductEntity implements Serializable  {

	private static final long serialVersionUID = -2982247105587439319L;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="prd_id")
	private ProductEntity shippingProduct;
	
	@OneToMany(	mappedBy="product",  
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<ShippingProductAttributeEntity> attributes = new HashSet<ShippingProductAttributeEntity>();
    
	@Id
	@Column(name="prd_id")
	private Long productId;

	@Column(name="shp_wgt_lim")
	private BigDecimal weightLimit;
	
	@Column(name="shp_wgt_frm")
	private BigDecimal weightFrom;
	
	@Column(name="shp_wgt_to")
	private BigDecimal weightTo;
	
	@Column(name="shp_typ_cd")
	private String shippingTypeCode;
	
	@Column(name="shp_ctry_cd")
	private String shippingCountryCode;

	public BigDecimal getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(BigDecimal weightLimit) {
		this.weightLimit = weightLimit;
	}

	public BigDecimal getWeightFrom() {
		return weightFrom;
	}

	public void setWeightFrom(BigDecimal weightFrom) {
		this.weightFrom = weightFrom;
	}

	public BigDecimal getWeightTo() {
		return weightTo;
	}

	public void setWeightTo(BigDecimal weightTo) {
		this.weightTo = weightTo;
	}

	public ProductEntity getShippingProduct() {
		return shippingProduct;
	}

	public void setShippingProduct(ProductEntity shippingProduct) {
		this.shippingProduct = shippingProduct;
	}

	public Set<ShippingProductAttributeEntity> getAttributes() {
		return attributes;
	}

	public void addProductAttribute(ShippingProductAttributeEntity productAttribute) {
		this.getAttributes().add(productAttribute);
		productAttribute.setProduct(this);
	}
	
	public void removeProductAttribute(ShippingProductAttributeEntity productAttribute) {
		this.getAttributes().remove(productAttribute);
		productAttribute.setProduct(null);
	}

	public String getShippingCountryCode() {
		return shippingCountryCode;
	}

	public void setShippingCountryCode(String shippingCountryCode) {
		this.shippingCountryCode = shippingCountryCode;
	}

	public String getShippingTypeCode() {
		return shippingTypeCode;
	}

	public void setShippingTypeCode(String shippingTypeCode) {
		this.shippingTypeCode = shippingTypeCode;
	}
	
	

}
