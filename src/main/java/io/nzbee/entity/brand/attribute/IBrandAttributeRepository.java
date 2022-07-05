package io.nzbee.entity.brand.attribute;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandAttributeRepository extends JpaRepository<BrandAttributeEntity, Long> {

	List<BrandAttributeEntity> findAll();

	List<BrandAttributeEntity> findByLclCd(String lcl);

	Optional<BrandAttributeEntity> findByLclCdAndBrandBrandId(String lcl, Long id);
	
	Optional<BrandAttributeEntity> findByLclCdAndBrandBrandCode(String lcl, String code);

}
