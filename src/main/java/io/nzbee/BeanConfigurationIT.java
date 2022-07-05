package io.nzbee;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PagedResourcesAssembler;
import io.nzbee.domain.bag.BagDomainServiceImpl;
import io.nzbee.domain.bag.IBagDomainService;
import io.nzbee.domain.customer.CustomerServiceImpl;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.domain.promotion.IPromotionService;
import io.nzbee.domain.promotion.PromotionServiceImpl;
import io.nzbee.entity.adapters.view.BrandAdapterImpl;
import io.nzbee.entity.adapters.view.BrandFacetAdapterImpl;
import io.nzbee.entity.adapters.view.PhysicalProductFullAdapterImpl;
import io.nzbee.entity.adapters.view.PhysicalProductLightAdapterImpl;
import io.nzbee.entity.adapters.view.ProductCategoryAdapterImpl;
import io.nzbee.entity.adapters.view.ShippingProductAdapterImpl;
import io.nzbee.entity.brand.view.BrandDTOServiceImpl;
import io.nzbee.entity.brand.view.IBrandDTOService;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOMapperImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOServiceImpl;
import io.nzbee.entity.brand.view.facet.IBrandFacetDTOMapper;
import io.nzbee.entity.brand.view.facet.IBrandFacetDTOService;
import io.nzbee.entity.product.physical.full.IPhysicalProductFullViewMapper;
import io.nzbee.entity.product.physical.full.PhysicalProductFullViewMapperImpl;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightMapper;
import io.nzbee.entity.product.physical.light.PhysicalProductLightMapperImpl;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModel;
import io.nzbee.resources.product.shipping.ShippingProductResource;
import io.nzbee.view.ports.IBrandFacetViewPortService;
import io.nzbee.view.ports.IBrandViewPortService;
import io.nzbee.view.ports.ICategoryViewPortService;
import io.nzbee.view.ports.IPhysicalProductFullPortService;
import io.nzbee.view.ports.IPhysicalProductLightPortService;
import io.nzbee.view.ports.IShippingProductPortService;
import io.nzbee.view.product.brand.BrandViewServiceImpl;
import io.nzbee.view.product.brand.IBrandViewService;
import io.nzbee.view.product.brand.facet.BrandFacetViewServiceImpl;
import io.nzbee.view.product.brand.facet.IBrandFacetViewService;
import io.nzbee.view.product.physical.full.IPhysicalProductFullService;
import io.nzbee.view.product.physical.full.PhysicalProductFullServiceImpl;
import io.nzbee.view.product.physical.light.IPhysicalProductLightViewService;
import io.nzbee.view.product.physical.light.PhysicalProductLightViewServiceImpl;
import io.nzbee.view.product.tag.facet.ITagFacetViewService;
import io.nzbee.view.product.tag.facet.TagFacetViewServiceImpl;


@Configuration

public class BeanConfigurationIT {
	
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
  
}