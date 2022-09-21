package io.nzbee.controllers;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBagDomainService;
import io.nzbee.domain.bag.item.regular.IRegularBagItem;
import io.nzbee.domain.bag.item.regular.IRegularBagItemDomainService;
import io.nzbee.domain.promotion.dto.coupon.CouponDTO;
import io.nzbee.domain.valueObjects.CouponCode;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.domain.valueObjects.ProductUPC;
import io.nzbee.resources.bag.BagResource;
import io.nzbee.resources.bag.BagResourceAssembler;
import io.nzbee.resources.bag.item.BagItemResource;
import io.nzbee.resources.bag.item.BagItemResourceAssembler;
import io.nzbee.view.bag.BagView;
import io.nzbee.view.bag.IBagViewService;
import io.nzbee.view.bag.item.BagItemViewIn;
import io.nzbee.view.bag.item.BagItemViewOut;
import io.nzbee.view.product.shipping.ShippingItemDTOIn;

@RestController
@RequestMapping("/api")
public class BagController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IBagViewService viewBagService;

	@Autowired
	private IBagDomainService domainBagService;

	@Autowired
	private IRegularBagItemDomainService domainBagItemService;
	
	@Autowired
	private BagResourceAssembler bagResourceAssembler;

	@Autowired
	private BagItemResourceAssembler bagItemResourceAssembler;

	public BagController() {
		super();
	}

	@GetMapping("/Bag/{locale}/{currency}")
	public ResponseEntity<BagResource> getCustomerBag(@PathVariable String locale, @PathVariable String currency,
			Principal principal) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getCustomerBag");

		// get the domain model to compute the bag total
		Bag b = domainBagService.findByCode(Locale.localize(locale, currency), principal.getName());

		domainBagService.checkAllBagRules(b);
		
		// promotionService.applyAll(b);
		
		domainBagService.save(b);

		// get the view containing additional attributes
		BagView bv = viewBagService.findByCode(Locale.localize(locale, currency), principal.getName());

		return ResponseEntity.ok(bagResourceAssembler.toModel(bv));
	}

	@PostMapping("/Bag/{locale}/{currency}/Coupon/Add")
	public ResponseEntity<BagResource> addCouponToBag(@PathVariable String locale, @PathVariable String currency,
			 @RequestBody CouponDTO dto, Principal principal) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addCouponToBag");
		
		domainBagService.addItemToBag(Locale.localize(locale, currency), new CouponCode(dto.getCoupon()), principal.getName());
		
		Bag b = domainBagService.findByCode(Locale.localize(locale, currency), principal.getName());
		
		//promotionService.applyAll(b);
		
		domainBagService.save(b);


		BagView bv = viewBagService.findByCode(Locale.localize(locale, currency), principal.getName());

		return ResponseEntity.ok(bagResourceAssembler.toModel(bv));
	}

	@GetMapping("/Bag/{locale}/{currency}/Items")
	public ResponseEntity<CollectionModel<BagItemResource>> getBagContents(@PathVariable String locale,
			@PathVariable String currency, Principal principal) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getBagContents");

		Bag b = domainBagService.findByCode(Locale.localize(locale, currency), principal.getName());

		//promotionService.applyAll(b);
		
		domainBagService.save(b);
		
		Set<BagItemViewOut> sbi = viewBagService.findByCode(Locale.localize(locale, currency), principal.getName()).getBagItems();

		return ResponseEntity.ok(bagItemResourceAssembler.toCollectionModel(sbi));

	}

	@PostMapping("/Bag/{locale}/{currency}/Items/Physical/Add")
	public ResponseEntity<BagResource> addPhysicalItemToBag(@PathVariable String locale, @PathVariable String currency,
			@RequestBody BagItemViewIn dto, Principal principal) {

		LOGGER.debug("call " + getClass().getSimpleName() + ".addPhysicalItemToBag with parameters {}, {}, {}, {}", locale,
				currency, dto.getItemUPC(), dto.getItemQty());
		
		// persist the domain BagItem to the Bag
		domainBagService.addPhysicalItem(Locale.localize(locale, currency), dto, principal.getName());

		BagView bv = viewBagService.findByCode(Locale.localize(locale, currency), principal.getName());

		return ResponseEntity.ok(bagResourceAssembler.toModel(bv));
	}

	@PostMapping("/Bag/{locale}/{currency}/Items/Shipping/Add")
	public ResponseEntity<BagResource> addShippingItemToBag(@PathVariable String locale, @PathVariable String currency,
			@RequestBody ShippingItemDTOIn dto, Principal principal) {

		LOGGER.debug("call " + getClass().getSimpleName() + ".addShippingItemToBag with parameters {}, {}, {}", locale,
				currency, dto.getShippingProductCode());
		
		// persist the domain BagItem to the Bag
		domainBagService.addShippingItem(Locale.localize(locale, currency), dto, principal.getName());

		Bag b = domainBagService.findByCode(Locale.localize(locale, currency), principal.getName());
		
		//promotionService.applyAll(b);
		
		domainBagService.save(b);

		// re-retrieve the bag view and return it
		BagView bv = viewBagService.findByCode(Locale.localize(locale, currency), principal.getName());

		return ResponseEntity.ok(bagResourceAssembler.toModel(bv));
	}

	@GetMapping("/Bag/{locale}/{currency}/Items/Remove/{itemCode}")
	public ResponseEntity<Void> removeItemFromBag(@PathVariable String locale, @PathVariable String currency,
			@PathVariable String itemCode, Principal principal) {

		LOGGER.debug("call " + getClass().getSimpleName() + ".removeItemFromBag for parameters {}, {}, {} ", locale,
				currency, itemCode);
		// here we get the bag and bagItems but the products are null
		Bag b = domainBagService.findByCode(Locale.localize(locale, currency), principal.getName());
		
		//promotionService.applyAll(b);
		
		domainBagService.save(b);

		Optional<IRegularBagItem> obi = b.getBagItems().stream()
				.filter(bi -> bi.getBagItem().getProductUPC().sameAs(new ProductUPC(itemCode))).findAny();

		if (obi.isPresent()) {
			domainBagItemService.delete(obi.get());
		}
		
		return null;
	}


}
