package io.nzbee.integration.entity.product.search;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.adapters.view.PhysicalProductLightAdapterImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOServiceImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTOServiceImpl;
import io.nzbee.entity.product.department.DepartmentDaoImpl;
import io.nzbee.entity.product.department.IDepartmentDao;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightDao;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightMapper;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightDTOService;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDaoImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightMapperImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDTOServiceImpl;
import io.nzbee.entity.tag.ITagDao;
import io.nzbee.entity.tag.TagDaoPostgresImpl;
import io.nzbee.entity.tag.view.facet.ITagFacetDao;
import io.nzbee.entity.tag.view.facet.TagFacetDTOPostgresDaoImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOServiceImpl;
import io.nzbee.integration.entity.beans.product.physical.PhysicalProductEntityBeanFactory;
import io.nzbee.integration.entity.product.ConfigProductEntityTests;
import io.nzbee.search.FacetServicesImpl;
import io.nzbee.search.IFacetService;
import io.nzbee.search.IFacetServices;
import io.nzbee.search.ISearchService;
import io.nzbee.search.SearchServiceImpl;
import io.nzbee.view.ports.IPhysicalProductLightPortService;

@Configuration
@Import({ConfigProductEntityTests.class})
public class ConfigProductSearchEntityTests {
	
	@Bean
	IPhysicalProductLightPortService physicalProductLightPortService() {
		return new PhysicalProductLightAdapterImpl();
	}
	
	@Bean
	IPhysicalProductLightDTOService physicalProductLightService() {
		return new PhysicalProductLightDTOServiceImpl();
	}
	
	@Bean
	IPhysicalProductLightDao physicalProductLightDao() {
		return new PhysicalProductLightDaoImpl();
	}
	
	@Bean 
	IPhysicalProductLightMapper physicalProductLightMapper() {
		return new PhysicalProductLightMapperImpl();
	}
	
	@Bean
	ISearchService searchService() {
		return new SearchServiceImpl();
	}
	
	@Bean
	IFacetServices facetServices() {
		return new FacetServicesImpl();
	}
	
	@Bean
	List<IFacetService> facets() {
		return new ArrayList<IFacetService>();
	}
	
	@Bean
	IFacetService tagFacetService() {
		return new TagFacetDTOServiceImpl();
	}
	
	@Bean
	ITagFacetDao tagFacetDao() {
		return new TagFacetDTOPostgresDaoImpl();
	}
	
	@Bean
	IFacetService categoryFacetService() {
		return new ProductCategoryFacetDTOServiceImpl();
	}
	
	@Bean
	IFacetService brandFacetService() {
		return new BrandFacetDTOServiceImpl();
	}
	
	@Bean 
	PhysicalProductEntityBeanFactory productEntityBeanFactory() {
		return new PhysicalProductEntityBeanFactory();
	}

	
	@Bean
	IDepartmentDao departmentDao() {
		return new DepartmentDaoImpl();
	}
	
	@Bean
	ITagDao tagDao() {
		return new TagDaoPostgresImpl();
	}
	
}
