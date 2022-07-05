package io.nzbee.entity.promotion.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.promotion.PromotionEntity;

@Entity
@Table(name = "promotion_product", schema = "mochi")
public class PromotionProductEntity implements Serializable {

	private static final long serialVersionUID = -3935115006992696518L;

	@Id
	@Column(name="prm_id")
	private Long promotionId;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "category_promotion", schema = "mochi", joinColumns = @JoinColumn(name = "prm_id"), inverseJoinColumns = @JoinColumn(name = "cat_id"))
	private Set<CategoryEntity> categories = new HashSet<CategoryEntity>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "product_promotion", schema = "mochi", joinColumns = @JoinColumn(name = "prm_id"), inverseJoinColumns = @JoinColumn(name = "prd_id"))
	private Set<ProductEntity> products = new HashSet<ProductEntity>();

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="prm_id")
	private PromotionEntity productPromotion;
	
	public Set<CategoryEntity> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryEntity> categories) {
		this.categories = categories;
	}

	public Set<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductEntity> products) {
		this.products = products;
	}

	public void addProduct(ProductEntity product) {
		this.products.add(product);
		product.getPromotions().add(this);
	}

	public void removeProduct(ProductEntity product) {
		this.products.remove(product);
		product.removePromotion(this);
	}

	public void addCategory(CategoryEntity category) {
		this.getCategories().add(category);
		category.getPromotions().add(this);
	}

	public void removeCategory(CategoryEntity category) {
		this.getCategories().remove(category);
		category.removePromotion(this);
	}

	public PromotionEntity getProductPromotion() {
		return productPromotion;
	}

	public void setProductPromotion(PromotionEntity productPromotion) {
		this.productPromotion = productPromotion;
	}
	
}
