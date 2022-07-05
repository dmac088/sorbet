package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository  extends JpaRepository<BrandEntity, Long>  {

	List<BrandEntity> findAll();
	
	Optional<BrandEntity> findByBrandCode(String brandCode);
	
	Optional<BrandEntity> findByAttributesLclCdAndAttributesBrandDesc(String locale, String brandDesc);

}
