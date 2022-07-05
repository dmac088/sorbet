package io.nzbee.entity.category.brand;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;

@Entity
@Table(name = "category_brand", schema = "mochi")
public class CategoryBrandEntity implements Serializable  {

	private static final long serialVersionUID = 7221370251309880198L;

	@Id
	@Column(name="cat_id")
	private Long categoryId;
	
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="cat_id")
	private CategoryEntity category;
	
	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = {CascadeType.ALL})
    @JoinTable(name = "brand_category", schema="mochi", 
    		   joinColumns 			= @JoinColumn(name = "cat_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "bnd_id"))
    @OrderBy
    private Set<BrandEntity> brands;
	
	@Transient
	private Long brandCount;
	
	public CategoryBrandEntity() {
		super();
	}
	
	public void addBrand(BrandEntity brand) {
		this.brands.add(brand);
		brand.addBrandCategory(this);
	}
	
	public void removeBrand(BrandEntity brand) {
		this.brands.remove(brand);
		brand.removeBrandCategory(this);
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
        return category.getCategoryCode() != null && category.getCategoryCode().equals(((CategoryEntity) o).getCategoryCode());
    }
 
    @Override
    public int hashCode() {
        return 32;
    }
	
}
