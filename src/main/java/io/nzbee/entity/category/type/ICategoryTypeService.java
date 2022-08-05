package io.nzbee.entity.category.type;

import java.util.List;
import java.util.Optional;

public interface ICategoryTypeService {

	List<CategoryTypeEntity> findAll();

	Optional<CategoryTypeEntity> findByCode(String code);

}
