package io.nzbee.entity.product.shipping.attribute.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.nzbee.entity.product.shipping.entity.ShippingProductEntity;

@Entity
@Table(name = "product_shipping_attr_lcl", schema = "mochi")
public class ShippingProductAttributeEntity implements Serializable {

	private static final long serialVersionUID = 1442836684351542603L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "shippingProductAttributeIdGenerator")
	@SequenceGenerator(name="shippingProductAttributeIdGenerator", sequenceName = "product_shipping_attr_lcl_prd_lcl_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="prd_lcl_id")
	private Long Id;

	@Column(name="prd_shp_typ_desc")
	private String shippingTypeDesc;
	
	@Column(name="prd_shp_ctry_desc")
	private String shippingCountryDesc;
	
	@Column(name="lcl_cd")
	private String lclCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prd_id")
	private ShippingProductEntity product;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	public Long getProductId() {
		return this.getProduct().getShippingProduct().getProductId();
	}
	
	public ShippingProductEntity getProduct() {
		return product;
	}

	public void setProduct(ShippingProductEntity product) {
		this.product = product;
	}
	
	public String getShippingTypeDesc() {
		return shippingTypeDesc;
	}

	public void setShippingTypeDesc(String shippingTypeDesc) {
		this.shippingTypeDesc = shippingTypeDesc;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}

	public String getShippingCountryDesc() {
		return shippingCountryDesc;
	}

	public void setShippingCountryDesc(String shippingCountryDesc) {
		this.shippingCountryDesc = shippingCountryDesc;
	}

}
