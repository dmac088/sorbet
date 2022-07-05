package io.nzbee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PagedResourcesAssembler;
import io.nzbee.domain.bag.BagDomainServiceImpl;
import io.nzbee.domain.bag.IBagDomainService;
import io.nzbee.domain.bag.item.regular.IRegularBagItemDomainService;
import io.nzbee.domain.bag.item.regular.RegularBagItemServiceImpl;
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
import io.nzbee.entity.bag.item.domain.regular.RegularBagItemDomainDTOMapperImpl;
import io.nzbee.entity.bag.item.domain.regular.RegularBagItemDomainDTOServiceImpl;
import io.nzbee.entity.bag.view.BagViewDTOServiceImpl;
import io.nzbee.entity.bag.view.IBagViewDTOService;
import io.nzbee.entity.bag.domain.BagDomainDTODaoImpl;
import io.nzbee.entity.bag.domain.BagDomainDTOServiceImpl;
import io.nzbee.entity.bag.domain.IBagDomainDTODao;
import io.nzbee.entity.bag.domain.IBagDomainDTOService;
import io.nzbee.entity.bag.item.domain.regular.IRegularBagItemDomainDTOMapper;
import io.nzbee.entity.bag.item.domain.regular.IRegularBagItemDomainDTOService;
import io.nzbee.entity.brand.view.BrandDTOServiceImpl;
import io.nzbee.entity.brand.view.IBrandDTOService;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOMapperImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOServiceImpl;
import io.nzbee.entity.brand.view.facet.IBrandFacetDTOMapper;
import io.nzbee.entity.brand.view.facet.IBrandFacetDTOService;
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
public class BeanConfigurationDev {
	
	@Bean
	public IRegularBagItemDomainService regularBagItemDomainService() {
		return new RegularBagItemServiceImpl();
	}
	
	@Bean
	public IBagDomainDTODao bagDomainDTODao() {
		return new BagDomainDTODaoImpl();
	}
	
	@Bean
	public IBagDomainDTOService bagDomainDTOService() {
		return new BagDomainDTOServiceImpl();
	}
	
	@Bean
	public IShippingProductViewDTOMapper shippingProductViewMapper() {
		return new ShippingProductViewDTOMapperImpl();
	}
	
	@Bean
	public IShippingProductViewService shippingProductViewService() {
		return new ShippingProductViewServiceImpl();
	}
	
	@Bean 
	public IRegularBagItemDomainDTOMapper bagItemDomainDTOMapper() {
		return new RegularBagItemDomainDTOMapperImpl();
	}
	
	@Bean
	public IRegularBagItemDomainDTOService bagItemDomainDTOService() {
		return new RegularBagItemDomainDTOServiceImpl();
	}
	
	@Bean 
	public IShippingTypePortService shippingTypePortService() {
		return new ShippingTypeAdapterImpl();
	}
	
	@Bean
	public IShippingTypeViewService shippingTypeViewService() {
		return new ShippingTypeViewServiceImpl(); 
	}
	
	@Bean
	public IShippingDestinationPortService shippingDestinationPortService() {
		return new ShippingDestinationAdapterImpl();
	}
	
	@Bean
	public IShippingDestiantionViewService shippingDestiantionViewService() {
		return new ShippingDestinationViewServiceImpl();
	}
	
	@Bean
	public IProductStatusService productStatusService() {
		return new ProductStatusServiceImpl();
	}
	
	@Bean 
	public IPhysicalProductMasterPort physicalProductMasterPort() {
		return new PhysicalProductMasterAdapter();
	}
	
	@Bean 
	public IHKPostPort hKPostPort() { 
		return new HKPostAdapter();
	}
	
	@Bean
	public IProductCategoryViewService productCategoryViewService() {
		return new ProductCategoryViewServiceImpl();
	}
	
	@Bean
	public ICustomerPortService customerPortService() {
		return new CustomerViewAdapter();
	}
	
	@Bean
	public ICustomerViewService customerViewService() {
		return new CustomerViewServiceImpl();
	}
	
	@Bean
	public ICustomerViewMapper customerViewMapper() {
		return new CustomerViewMapperImpl();
	}
	
	@Bean
	public IBagViewDTOService bagViewDTOService() {
		return new BagViewDTOServiceImpl();
	}
	
	@Bean 
	public IBagPortService bagPortService() {
		return new BagViewAdapterImpl();
	}
	
	@Bean
	public IBagViewService bagViewService() {
		return new BagViewServiceImpl();
	}

	@Bean
	public IPhysicalProductDTOMapper physicalProductViewDTOMapper() {
		return new PhysicalProductDTOMapperImpl();
	}
	
	@Bean
	public IBrandFacetDTOService brandFAcetDTOService() {
		return new BrandFacetDTOServiceImpl();
	}
	
	@Bean
	public ICategoryViewPortService productCategoryPortService() {
		return new ProductCategoryAdapterImpl();
	}
	
	@Bean
	public IBrandFacetDTOMapper brandFacetViewMapper() { 
		return new BrandFacetDTOMapperImpl();
	}
	
	@Bean
	public IBrandFacetViewPortService brandEntityViewService() {
		return new BrandFacetAdapterImpl();
	}
	
	@Bean
	public IBrandFacetViewService brandFacetViewService() {
		return new BrandFacetViewServiceImpl();
	}
	
	@Bean
	public IBrandDTOService test() {
		return new BrandDTOServiceImpl();
	}
	
	@Bean
	public IBrandViewService brandViewService() {
		return new BrandViewServiceImpl();
	}
	
	@Bean
	public IBrandViewPortService brandViewPortService() {
		return new BrandAdapterImpl();
	}
	
	@Bean
	public IPhysicalProductLightViewService physicalProductLightService() {
		return new PhysicalProductLightViewServiceImpl();
	}
	
	@Bean
	public IPhysicalProductFullService physicalProductFullService() {
		return new PhysicalProductFullServiceImpl();
	}
	
	@Bean
	public IShippingProductPortService shippingProductPortService() {
		return new ShippingProductAdapterImpl();
	}
	
	@Bean	
	public IPhysicalProductLightPortService productLightPortService() {
		return new PhysicalProductLightAdapterImpl();
	}
	
	@Bean
	public IPhysicalProductLightMapper productLightMapper() {
		return new PhysicalProductLightMapperImpl();
	}

	
	@Bean
	public IPhysicalProductFullPortService productFullPortService() {
		return new PhysicalProductFullAdapterImpl();
	}
	
	@Bean
	public IPhysicalProductFullViewMapper productFullMapper() {
		return new PhysicalProductFullViewMapperImpl();
	}
	
	@Bean
    public PagedResourcesAssembler<ShippingProductResource> pagedShippingProductResourceAssembler() {
    	return new PagedResourcesAssembler<ShippingProductResource>(null, null);
    }
	
	@Bean
    public PagedResourcesAssembler<PhysicalProductLightModel> pagedPhysicalProductLightResourceAssembler() {
    	return new PagedResourcesAssembler<PhysicalProductLightModel>(null, null);
    }
    
    @Bean
    public ITagFacetViewService tagService() {
        return new TagFacetViewServiceImpl();
    }
    
    @Bean
    public IPromotionService promotionService() {
        return new PromotionServiceImpl();
    }
    
    @Bean
    public ICustomerService customerService() {
        return new CustomerServiceImpl();
    }
    
    @Bean
    public IBagDomainService bagService() {
        return new BagDomainServiceImpl();
    }
    
    @Bean 
    public IPhysicalProductDTODao physicalProductDao() {
    	return new PhysicalProductDTODaoPostgresImpl();
    }
    
  
}