package io.nzbee.entity.category.type;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryTypeServiceImpl implements ICategoryTypeService {

	@Autowired
	private ICategoryTypeRepository categoryTypeRepository;
	
	@Override
	public List<CategoryTypeEntity> findAll() {
		return categoryTypeRepository.findAll();
	}
	
	@Override
	public Optional<CategoryTypeEntity> findByCode(String code) {
		return categoryTypeRepository.findByCategoryTypeCode(code);
	}

}
