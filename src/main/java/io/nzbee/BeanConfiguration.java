package io.nzbee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PagedResourcesAssembler;
import io.nzbee.domain.bag.BagDomainServiceImpl;
import io.nzbee.domain.bag.IBagDomainService;
import io.nzbee.domain.bag.item.regular.IRegularBagItemDomainService;
import io.nzbee.domain.bag.item.regular.RegularBagItemServiceImpl;
import io.nzbee.domain.bag.item.shipping.IShippingBagItemDomainService;
import io.nzbee.domain.bag.item.shipping.ShippingBagItemDomainServiceImpl;
import io.nzbee.domain.customer.CustomerServiceImpl;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.domain.promotion.IPromotionService;
import io.nzbee.domain.promotion.PromotionServiceImpl;
import io.nzbee.entity.adapters.hkpost.HKPostAdapter;
import io.nzbee.entity.adapters.util.PhysicalProductMasterAdapter;
import io.nzbee.entity.adapters.view.BagViewAdapterImpl;
import io.nzbee.entity.adapters.view.BrandAdapterImpl;
import io.nzbee.entity.adapters.view.BrandFacetAdapterImpl;
import io.nzbee.entity.adapters.view.CustomerViewAdapter;
import io.nzbee.entity.adapters.view.PhysicalProductFullAdapterImpl;
import io.nzbee.entity.adapters.view.PhysicalProductLightAdapterImpl;
import io.nzbee.entity.adapters.view.ProductCategoryAdapterImpl;
import io.nzbee.entity.adapters.view.ShippingDestinationAdapterImpl;
import io.nzbee.entity.adapters.view.ShippingProductAdapterImpl;
import io.nzbee.entity.adapters.view.ShippingTypeAdapterImpl;
import io.nzbee.entity.bag.view.BagViewDTOServiceImpl;
import io.nzbee.entity.bag.view.IBagViewDTOService;
import io.nzbee.entity.bag.domain.BagDomainDTODaoImpl;
import io.nzbee.entity.bag.domain.BagDomainDTOServiceImpl;
import io.nzbee.entity.bag.domain.IBagDomainDTODao;
import io.nzbee.entity.bag.domain.IBagDomainDTOService;
import io.nzbee.entity.bag.item.domain.IRegularBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.IShippingBagItemDomainDTODao;
import io.nzbee.entity.bag.item.domain.IShippingBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.IBagItemDomainDTOService;
import io.nzbee.entity.bag.item.domain.IRegularBagItemDomainDTODao;
import io.nzbee.entity.bag.item.type.BagItemTypeServiceImpl;
import io.nzbee.entity.bag.item.type.IBagItemTypeService;
import io.nzbee.entity.bag.item.domain.RegularBagItemDomainDTOMapperImpl;
import io.nzbee.entity.bag.item.domain.ShippingBagItemDomainDTO;
import io.nzbee.entity.bag.item.domain.ShippingBagItemDomainDTOMapperImpl;
import io.nzbee.entity.bag.item.domain.RegularBagItemDomainDTO;
import io.nzbee.entity.bag.item.domain.BagItemDomainDTODaoImpl;
import io.nzbee.entity.bag.item.domain.BagItemDomainDTOServiceImpl;
import io.nzbee.entity.brand.view.BrandDTOServiceImpl;
import io.nzbee.entity.brand.view.IBrandDTOService;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOMapperImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOServiceImpl;
import io.nzbee.entity.brand.view.facet.IBrandFacetDTOMapper;
import io.nzbee.entity.brand.view.facet.IBrandFacetDTOService;
import io.nzbee.entity.category.type.CategoryTypeServiceImpl;
import io.nzbee.entity.category.type.ICategoryTypeService;
import io.nzbee.entity.party.person.CustomerViewMapperImpl;
import io.nzbee.entity.party.person.ICustomerViewMapper;
import io.nzbee.entity.product.physical.full.IPhysicalProductFullViewMapper;
import io.nzbee.entity.product.physical.full.PhysicalProductFullViewMapperImpl;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightMapper;
import io.nzbee.entity.product.physical.light.PhysicalProductLightMapperImpl;
import io.nzbee.entity.product.physical.view.IPhysicalProductDTODao;
import io.nzbee.entity.product.physical.view.IPhysicalProductDTOMapper;
import io.nzbee.entity.product.physical.view.PhysicalProductDTODaoPostgresImpl;
import io.nzbee.entity.product.physical.view.PhysicalProductDTOMapperImpl;
import io.nzbee.entity.product.shipping.view.IShippingProductViewDTOMapper;
import io.nzbee.entity.product.shipping.view.ShippingProductViewDTOMapperImpl;
import io.nzbee.entity.product.status.IProductStatusService;
import io.nzbee.entity.product.status.ProductStatusServiceImpl;
import io.nzbee.hkpost.IHKPostPort;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModel;
import io.nzbee.resources.product.shipping.ShippingProductResource;
import io.nzbee.util.ports.IPhysicalProductMasterPort;
import io.nzbee.view.bag.BagViewServiceImpl;
import io.nzbee.view.bag.IBagViewService;
import io.nzbee.view.category.product.IProductCategoryViewService;
import io.nzbee.view.category.product.ProductCategoryViewServiceImpl;
import io.nzbee.view.customer.CustomerViewServiceImpl;
import io.nzbee.view.customer.ICustomerViewService;
import io.nzbee.view.ports.IBagPortService;
import io.nzbee.view.ports.IBrandFacetViewPortService;
import io.nzbee.view.ports.IBrandViewPortService;
import io.nzbee.view.ports.ICategoryViewPortService;
import io.nzbee.view.ports.ICustomerPortService;
import io.nzbee.view.ports.IPhysicalProductFullPortService;
import io.nzbee.view.ports.IPhysicalProductLightPortService;
import io.nzbee.view.ports.IShippingDestinationPortService;
import io.nzbee.view.ports.IShippingProductPortService;
import io.nzbee.view.ports.IShippingTypePortService;
import io.nzbee.view.product.brand.IBrandViewService;
import io.nzbee.view.product.brand.BrandViewServiceImpl;
import io.nzbee.view.product.brand.facet.BrandFacetViewServiceImpl;
import io.nzbee.view.product.brand.facet.IBrandFacetViewService;
import io.nzbee.view.product.physical.full.IPhysicalProductFullService;
import io.nzbee.view.product.physical.full.PhysicalProductFullServiceImpl;
import io.nzbee.view.product.physical.light.IPhysicalProductLightViewService;
import io.nzbee.view.product.physical.light.PhysicalProductLightViewServiceImpl;
import io.nzbee.view.product.shipping.IShippingProductViewService;
import io.nzbee.view.product.shipping.ShippingProductViewServiceImpl;
import io.nzbee.view.product.shipping.destination.IShippingDestiantionViewService;
import io.nzbee.view.product.shipping.destination.ShippingDestinationViewServiceImpl;
import io.nzbee.view.product.shipping.type.IShippingTypeViewService;
import io.nzbee.view.product.shipping.type.ShippingTypeViewServiceImpl;
import io.nzbee.view.product.tag.facet.ITagFacetViewService;
import io.nzbee.view.product.tag.facet.TagFacetViewServiceImpl;

@Configuration
class BeanConfiguration {
	
	@Bean
	IRegularBagItemDomainDTODao<RegularBagItemDomainDTO> regularBagItemDomainDTODao() {
		return new BagItemDomainDTODaoImpl();
	}
	
	@Bean
	IShippingBagItemDomainDTODao<ShippingBagItemDomainDTO> shippingBagItemDomainDTODao() {
		return new BagItemDomainDTODaoImpl();
	}
	
	@Bean
	IShippingBagItemDomainDTOMapper shippingBagItemDomainDTOMapper() {
		return new ShippingBagItemDomainDTOMapperImpl();
	}
	
	@Bean 
	IShippingBagItemDomainService shippingBagItemDomainService() {
		return new ShippingBagItemDomainServiceImpl();
	}
	
	@Bean
	IBagItemTypeService bagItemTypeService() {
		return new BagItemTypeServiceImpl();
	}
	
	@Bean
	ICategoryTypeService categoryTypeService() {
		return new CategoryTypeServiceImpl();
	}
	
	@Bean
	IRegularBagItemDomainService regularBagItemDomainService() {
		return new RegularBagItemServiceImpl();
	}
	
	@Bean
	IBagDomainDTODao bagDomainDTODao() {
		return new BagDomainDTODaoImpl();
	}
	
	@Bean
	IBagDomainDTOService bagDomainDTOService() {
		return new BagDomainDTOServiceImpl();
	}
	
	@Bean
	IShippingProductViewDTOMapper shippingProductViewMapper() {
		return new ShippingProductViewDTOMapperImpl();
	}
	
	@Bean
	IShippingProductViewService shippingProductViewService() {
		return new ShippingProductViewServiceImpl();
	}
	
	@Bean 
	IRegularBagItemDomainDTOMapper bagItemDomainDTOMapper() {
		return new RegularBagItemDomainDTOMapperImpl();
	}
	
	@Bean
	IBagItemDomainDTOService bagItemDomainDTOService() {
		return new BagItemDomainDTOServiceImpl();
	}
	
	@Bean 
	IShippingTypePortService shippingTypePortService() {
		return new ShippingTypeAdapterImpl();
	}
	
	@Bean
	IShippingTypeViewService shippingTypeViewService() {
		return new ShippingTypeViewServiceImpl(); 
	}
	
	@Bean
	IShippingDestinationPortService shippingDestinationPortService() {
		return new ShippingDestinationAdapterImpl();
	}
	
	@Bean
	IShippingDestiantionViewService shippingDestiantionViewService() {
		return new ShippingDestinationViewServiceImpl();
	}
	
	@Bean
	IProductStatusService productStatusService() {
		return new ProductStatusServiceImpl();
	}
	
	@Bean 
	IPhysicalProductMasterPort physicalProductMasterPort() {
		return new PhysicalProductMasterAdapter();
	}
	
	@Bean 
	IHKPostPort hKPostPort() { 
		return new HKPostAdapter();
	}
	
	@Bean
	IProductCategoryViewService productCategoryViewService() {
		return new ProductCategoryViewServiceImpl();
	}
	
	@Bean
	ICustomerPortService customerPortService() {
		return new CustomerViewAdapter();
	}
	
	@Bean
	ICustomerViewService customerViewService() {
		return new CustomerViewServiceImpl();
	}
	
	@Bean
	ICustomerViewMapper customerViewMapper() {
		return new CustomerViewMapperImpl();
	}
	
	@Bean
	IBagViewDTOService bagViewDTOService() {
		return new BagViewDTOServiceImpl();
	}
	
	@Bean 
	IBagPortService bagPortService() {
		return new BagViewAdapterImpl();
	}
	
	@Bean
	IBagViewService bagViewService() {
		return new BagViewServiceImpl();
	}

	@Bean
	IPhysicalProductDTOMapper physicalProductViewDTOMapper() {
		return new PhysicalProductDTOMapperImpl();
	}
	
	@Bean
	IBrandFacetDTOService brandFAcetDTOService() {
		return new BrandFacetDTOServiceImpl();
	}
	
	@Bean
	ICategoryViewPortService productCategoryPortService() {
		return new ProductCategoryAdapterImpl();
	}
	
	@Bean
	IBrandFacetDTOMapper brandFacetViewMapper() { 
		return new BrandFacetDTOMapperImpl();
	}
	
	@Bean
	IBrandFacetViewPortService brandEntityViewService() {
		return new BrandFacetAdapterImpl();
	}
	
	@Bean
	IBrandFacetViewService brandFacetViewService() {
		return new BrandFacetViewServiceImpl();
	}
	
	@Bean
	IBrandDTOService test() {
		return new BrandDTOServiceImpl();
	}
	
	@Bean
	IBrandViewService brandViewService() {
		return new BrandViewServiceImpl();
	}
	
	@Bean
	IBrandViewPortService brandViewPortService() {
		return new BrandAdapterImpl();
	}
	
	@Bean
	IPhysicalProductLightViewService physicalProductLightService() {
		return new PhysicalProductLightViewServiceImpl();
	}
	
	@Bean
	IPhysicalProductFullService physicalProductFullService() {
		return new PhysicalProductFullServiceImpl();
	}
	
	@Bean
	IShippingProductPortService shippingProductPortService() {
		return new ShippingProductAdapterImpl();
	}
	
	@Bean	
	IPhysicalProductLightPortService productLightPortService() {
		return new PhysicalProductLightAdapterImpl();
	}
	
	@Bean
	IPhysicalProductLightMapper productLightMapper() {
		return new PhysicalProductLightMapperImpl();
	}

	
	@Bean
	IPhysicalProductFullPortService productFullPortService() {
		return new PhysicalProductFullAdapterImpl();
	}
	
	@Bean
	IPhysicalProductFullViewMapper productFullMapper() {
		return new PhysicalProductFullViewMapperImpl();
	}
	
	@Bean
    PagedResourcesAssembler<ShippingProductResource> pagedShippingProductResourceAssembler() {
    	return new PagedResourcesAssembler<ShippingProductResource>(null, null);
    }
	
	@Bean
    PagedResourcesAssembler<PhysicalProductLightModel> pagedPhysicalProductLightResourceAssembler() {
    	return new PagedResourcesAssembler<PhysicalProductLightModel>(null, null);
    }
    
    @Bean
    ITagFacetViewService tagService() {
        return new TagFacetViewServiceImpl();
    }
    
    @Bean
    IPromotionService promotionService() {
        return new PromotionServiceImpl();
    }
    
    @Bean
    ICustomerService customerService() {
        return new CustomerServiceImpl();
    }
    
    @Bean
    IBagDomainService bagService() {
        return new BagDomainServiceImpl();
    }
    
    @Bean 
    IPhysicalProductDTODao physicalProductDao() {
    	return new PhysicalProductDTODaoPostgresImpl();
    }
    
  
}