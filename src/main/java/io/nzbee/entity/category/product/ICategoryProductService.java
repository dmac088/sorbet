package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IEntityService;

public interface ICategoryProductService extends IEntityService<CategoryProductEntity> {

	List<CategoryProductEntity> findAll();

	Optional<CategoryProductEntity> findByCode(String shippingrootcategorycode);

}
