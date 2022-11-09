package io.nzbee.entity.product.shipping.entity;

import java.io.Serializable;
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

	@Column(name="shp_shp_cd")
	private String shippingCode;
	
	@Column(name="shp_dst_cd")
	private String shippingCountryCode;
	
	@Column(name="shp_sts_cd")
	private String shippingStatusCode;
	
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

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public String getShippingStatusCode() {
		return shippingStatusCode;
	}

	public void setShippingStatusCode(String shippingStatusCode) {
		this.shippingStatusCode = shippingStatusCode;
	}

	
}
