package io.nzbee.util.ports;

import java.util.List;

import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.util.product.physical.PhysicalProductMasterSchema;

public interface IPhysicalProductMasterPort {
	
	void save(PhysicalProductMasterSchema p, List<BrandEntity> cachedBrandList,
			List<DepartmentEntity> cachedDepartmentList, List<ProductStatusEntity> cachedProductStatusList,
			List<CategoryProductEntity> cachedProductCategoryList, List<ProductPriceType> cachedPriceTypes,
			List<Currency> cachedCurrencies, List<TagEntity> cachedTags);
	
}
