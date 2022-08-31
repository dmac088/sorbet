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
import io.nzbee.domain.bag.item.regular.IRegularBagItemDomainService;
import io.nzbee.domain.bag.item.regular.RegularBagItem;
import io.nzbee.domain.dto.promotion.in.CouponDTO;
import io.nzbee.resources.bag.BagResource;
import io.nzbee.resources.bag.BagResourceAssembler;
import io.nzbee.resources.bag.item.BagItemResource;
import io.nzbee.resources.bag.item.BagItemResourceAssembler;
import io.nzbee.view.bag.BagView;
import io.nzbee.view.bag.IBagViewMapper;
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
	private IBagViewMapper bagDTOMapper;

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
		Bag b = domainBagService.findByCode(locale, currency, principal.getName());

		// domainBagService.checkAllBagRules(b);

		// get the view containing additional attributes
		BagView bv = viewBagService.toView(locale, currency, b);

		return ResponseEntity.ok(bagResourceAssembler.toModel(bv));
	}

	@PostMapping("/Bag/{locale}/{currency}/Coupon/Add")
	public ResponseEntity<BagResource> addCouponToBag(@PathVariable String locale, @PathVariable String currency,
			 @RequestBody CouponDTO dto, Principal principal) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".addCouponToBag");

		Bag b = domainBagService.findByCode(locale, currency, principal.getName());

		b.addCoupon(dto.getCoupon());

		domainBagService.checkAllBagRules(b);

		domainBagService.save(b);

		return ResponseEntity.ok(bagResourceAssembler.toModel(bagDTOMapper.toView(b)));
	}

	@GetMapping("/Bag/{locale}/{currency}/Items")
	public ResponseEntity<CollectionModel<BagItemResource>> getBagContents(@PathVariable String locale,
			@PathVariable String currency, Principal principal) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".getBagContents");

		Bag b = domainBagService.findByCode(locale, currency, principal.getName());

		Set<BagItemViewOut> sbi = viewBagService.findByCode(locale, currency, principal.getName(), b).getBagItems();

		return ResponseEntity.ok(bagItemResourceAssembler.toCollectionModel(sbi));

	}

	@PostMapping("/Bag/{locale}/{currency}/Items/Physical/Add")
	public ResponseEntity<BagResource> addPhysicalItemToBag(@PathVariable String locale, @PathVariable String currency,
			@RequestBody BagItemViewIn dto, Principal principal) {

		LOGGER.debug("call " + getClass().getSimpleName() + ".addPhysicalItemToBag with parameters {}, {}, {}, {}", locale,
				currency, dto.getItemUPC(), dto.getItemQty());

		// persist the domain BagItem to the Bag
		domainBagService.addPhysicalItem(locale, currency, dto, principal.getName());

		// rehydrate the bag domain model and view, then return it
		Bag b = domainBagService.findByCode(locale, currency, principal.getName());

		BagView bv = viewBagService.toView(locale, currency, b);

		return ResponseEntity.ok(bagResourceAssembler.toModel(bv));
	}

	@PostMapping("/Bag/{locale}/{currency}/Items/Shipping/Add")
	public ResponseEntity<BagResource> addShippingItemToBag(@PathVariable String locale, @PathVariable String currency,
			@RequestBody ShippingItemDTOIn dto, Principal principal) {

		LOGGER.debug("call " + getClass().getSimpleName() + ".addShippingItemToBag with parameters {}, {}, {}", locale,
				currency, dto.getShippingProductCode());

		// persist the domain BagItem to the Bag
		domainBagService.addShippingItem(locale, currency, dto, principal.getName());

		Bag b = domainBagService.findByCode(locale, currency, principal.getName());

		// re-retrieve the bag view and return it
		BagView bv = viewBagService.findByCode(locale, currency, principal.getName(), b);

		return ResponseEntity.ok(bagResourceAssembler.toModel(bv));
	}

	@GetMapping("/Bag/{locale}/{currency}/Items/Remove/{itemCode}")
	public ResponseEntity<Void> removeItemFromBag(@PathVariable String locale, @PathVariable String currency,
			@PathVariable String itemCode, Principal principal) {

		LOGGER.debug("call " + getClass().getSimpleName() + ".removeItemFromBag for parameters {}, {}, {} ", locale,
				currency, itemCode);
		// here we get the bag and bagItems but the products are null
		Bag b = domainBagService.findByCode(locale, currency, principal.getName());

		Optional<RegularBagItem> obi = b.getBagItems().stream()
				.filter(bi -> bi.getBagItem().getProductUPC().equals(itemCode)).findAny();

		if (obi.isPresent()) {
			domainBagItemService.delete(obi.get());
		}
		return null;
	}

}
