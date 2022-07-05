package io.nzbee.entity.category.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.product.ProductEntity;

@Entity
@Table(name = "category_product", schema = "mochi")
public class CategoryProductEntity implements Serializable {

	private static final long serialVersionUID = 8166164110478278072L;
	
	@Transient
	private Long productCount;
	
	@Transient
	private boolean hasParent;
	
	@Id
	@Column(name="cat_id")
	private Long categoryId;
	
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="cat_id")
	private CategoryEntity category;
	
	@ManyToMany(mappedBy = "categories")
    private Set<ProductEntity> products = new HashSet<ProductEntity>();
	
	public Set<ProductEntity> getProducts() {
		return products;
	}

	public boolean hasParent() {
		return hasParent;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}
	
	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryProductEntity)) return false;
        return category.getCategoryCode() != null && category.getCategoryCode().equals(((CategoryProductEntity) o).getCategory().getCategoryCode());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getCategory().getCategoryCode());
    }
}
