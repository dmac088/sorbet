package io.nzbee.resources.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBagDomainService;
import io.nzbee.enums.FacetNameEnum;
import io.nzbee.resources.brand.BrandViewModel;
import io.nzbee.resources.brand.BrandViewModelAssembler;
import io.nzbee.resources.dto.BrowseProductResultDto;
import io.nzbee.resources.product.physical.full.PhysicalProductFullModel;
import io.nzbee.resources.product.physical.full.PhysicalProductFullModelAssembler;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModel;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModelAssembler;
import io.nzbee.resources.product.shipping.ShippingProductResource;
import io.nzbee.resources.product.shipping.ShippingProductResourceAssembler;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResource;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResourceAssembler;
import io.nzbee.resources.product.shipping.type.ShippingTypeResource;
import io.nzbee.resources.product.shipping.type.ShippingTypeResourceAssembler;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.product.brand.BrandView;
import io.nzbee.view.product.brand.IBrandViewService;
import io.nzbee.view.product.physical.full.IPhysicalProductFullService;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;
import io.nzbee.view.product.physical.light.IPhysicalProductLightViewService;
import io.nzbee.view.product.physical.light.PhysicalProductLightView;
import io.nzbee.view.product.shipping.IShippingProductViewService;
import io.nzbee.view.product.shipping.destination.IShippingDestiantionViewService;
import io.nzbee.view.product.shipping.destination.ShippingDestinationView;
import io.nzbee.view.product.shipping.type.IShippingTypeViewService;
import io.nzbee.view.product.shipping.type.ShippingTypeView;

@RestController
@RequestMapping("/api")
public class ProductController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPhysicalProductFullService physicalProductFullService;

	@Autowired
	private IPhysicalProductLightViewService physicalProductLightService;

	@Autowired
	private IBagDomainService bagService;
	
	@Autowired
	private IBrandViewService brandService;
	
	@Autowired
	private IShippingDestiantionViewService shippingDestiantionService;
	
	@Autowired
	private IShippingTypeViewService shippingTypeService;
	
	@Autowired
	private IShippingProductViewService shippingProductViewService;

	@Autowired
	private ShippingProductResourceAssembler prodShippingResourceAssembler;
	
	@Autowired
	private PhysicalProductLightModelAssembler prodLightResourceAssembler;

	@Autowired
	private BrandViewModelAssembler brandResourceAssembler;
	
	@Autowired
	private ShippingDestinationResourceAssembler shippingDestinationResourceAssembler;

	@Autowired
	private ShippingTypeResourceAssembler shippingTypeResourceAssembler;
	
	@Autowired
	private PhysicalProductFullModelAssembler prodFullResourceAssembler;

	@Autowired
	private PagedResourcesAssembler<PhysicalProductLightModel> prodPhysicalPagedAssembler;

	@GetMapping("/Product/{locale}/{currency}/Code/{code}")
	public ResponseEntity<PhysicalProductFullModel> get(@PathVariable String locale, 
														@PathVariable String currency,
														@PathVariable String code) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".get with parameter {}, {}, {}", locale, currency, code);
		PhysicalProductFullModel pr = prodFullResourceAssembler.toModel(
				(PhysicalProductFullView) physicalProductFullService.findByCode(locale, currency, code).get());
		return new ResponseEntity<>(pr, HttpStatus.OK);
	}

	@GetMapping(value = "/Product/Image/{imageFileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody ResponseEntity<byte[]> getImageWithMediaType(@PathVariable String imageFileName) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getImageWithMediaType with parameter {}", imageFileName);
		InputStream in = getClass().getResourceAsStream("/public/images/" + imageFileName);
		try {
			if (!(in == null)) {
				return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);
			}
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/Product/{locale}/{currency}/Category/Code/{code}", params = { "page", "size", "sort" })
	public ResponseEntity<BrowseProductResultDto> getProducts(	@PathVariable String locale,
																@PathVariable String currency, 
																@PathVariable String code, 
																@RequestParam("page") String page,
																@RequestParam("size") String size, 
																@RequestParam("sort") String sort,
																@RequestBody Set<IFacet> selectedFacets) {

		LOGGER.debug("call " + getClass().getSimpleName() + ".getProducts with parameters : {}, {}, {}, {}, {}, {}, {}", locale, currency, code, page, size, sort, selectedFacets.size());

		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price"))
				.map(p -> p.getValue()).findFirst();
		Double maxPrice = null;
		if (oMaxPrice.isPresent()) {
			maxPrice = Double.valueOf(oMaxPrice.get());
		}

		Page<PhysicalProductLightView> sp = physicalProductLightService.findAll(locale,
																				currency,
																				code,
																				selectedFacets.stream().filter(c -> FacetNameEnum.valueOf(c.getFacetingName()).equals(FacetNameEnum.category))
																					.map(c -> c.getValue()).collect(Collectors.toSet()),
																				selectedFacets.stream().filter(c -> FacetNameEnum.valueOf(c.getFacetingName()).equals(FacetNameEnum.brand))
																					.map(c -> c.getValue()).collect(Collectors.toSet()),
																				selectedFacets.stream().filter(c -> FacetNameEnum.valueOf(c.getFacetingName()).equals(FacetNameEnum.tag))
																					.map(c -> c.getValue()).collect(Collectors.toSet()),
																				maxPrice, 
																				page, 
																				size, 
																				sort);

		Page<PhysicalProductLightModel> pages = sp.map(p -> prodLightResourceAssembler.toModel(p));

		return ResponseEntity.ok(new BrowseProductResultDto(prodPhysicalPagedAssembler.toModel(pages)));
	}

	@GetMapping(value = "/Product/Shipping/Provider/{locale}/{currency}")
	public ResponseEntity<CollectionModel<BrandViewModel>> getShippingProviders(@PathVariable String locale,
																				@PathVariable String currency) {

		LOGGER.debug("Fetching shipping providers for parameters : {}, {}", locale, currency);

		List<BrandView> lb = brandService.findByAllShippingProviders(locale);

		return ResponseEntity.ok(brandResourceAssembler.toCollectionModel(lb));
	}

	@GetMapping("/Product/Shipping/Destination/{locale}")
	public ResponseEntity<CollectionModel<ShippingDestinationResource>> getShippingDestinations(	@PathVariable String locale) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getShippingDestinations with parameter {}", locale);
		
		List<ShippingDestinationView> pr = shippingDestiantionService.findByAllShippingDestinations(locale);
		return ResponseEntity.ok(shippingDestinationResourceAssembler.toCollectionModel(pr));
	}
	
	@GetMapping("/Product/Shipping/Type/{locale}/Destination/Code/{destination}")
	public ResponseEntity<CollectionModel<ShippingTypeResource>> getShippingTypes(	@PathVariable String locale,
																					@PathVariable String destination,
																					Principal principal) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getShippingTypes with parameter {} ,{}", locale, destination);
		
		List<ShippingTypeView> pr = shippingTypeService.findByAllShippingTypesByDestinationAndWeight(locale, destination, principal.getName());
		return ResponseEntity.ok(shippingTypeResourceAssembler.toCollectionModel(pr));
	}
	
	@GetMapping("/Product/{locale}/{currency}/Destination/{code}/Type/{type}")
	public ResponseEntity<ShippingProductResource> getByDestinationAndType(	@PathVariable String locale,
																			@PathVariable String currency,
																			@PathVariable String code,
																			@PathVariable String type, 
																			Principal principal) {
		Bag b = bagService.findByCode(locale, currency, principal.getName());
		
		
		LOGGER.debug("total weight = "+ b.getTotalWeight());
		LOGGER.debug("total quantity = "+ b.getTotalQuantity());
 
		ShippingProductResource pr = prodShippingResourceAssembler
				.toModel(shippingProductViewService.findByDestinationAndTypeAndBagWeight(locale, currency, code, type, b.getTotalWeight()));

		return new ResponseEntity<>(pr, HttpStatus.OK);
	}
	
}
