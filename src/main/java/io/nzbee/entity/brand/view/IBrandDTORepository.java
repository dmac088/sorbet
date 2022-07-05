package io.nzbee.entity.brand.view;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import io.nzbee.Constants;
import io.nzbee.entity.brand.BrandEntity;


public interface IBrandDTORepository  extends JpaRepository<BrandEntity, Long>  {
	
	@Query(	  " SELECT new io.nzbee.entity.brand.view.BrandDTO("
			+ "												be.brandCode, "
			+ "												at.brandDesc, "
			+ "												at.lclCd "		
			+ ") "
			+ " FROM BrandEntity be "
			+ " JOIN be.attributes at "
			+ " WHERE at.lclCd = :locale")
	List<BrandDTO> findAll(String locale);
	

	
	@Query(	  " SELECT distinct new io.nzbee.entity.brand.view.BrandDTO("
			+ "												be.brandCode, "
			+ "												at.brandDesc, "
			+ "												at.lclCd "		
			+ ") "
			+ " FROM BrandEntity be "
			+ " JOIN be.attributes at "
			+ " JOIN be.products prd "
			+ " JOIN prd.department dept "
			+ " WHERE at.lclCd = :locale"
			+ " AND dept.departmentCode = '" + Constants.shippingProductDepartmentCode + "'"
			)
	List<BrandDTO> findByAllShippingProviders(String locale);
	
}
