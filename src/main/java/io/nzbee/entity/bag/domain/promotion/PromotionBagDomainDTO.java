package io.nzbee.entity.bag.domain.promotion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import io.nzbee.entity.bag.item.domain.promotion.PromotionBagItemDomainDTO;
import io.nzbee.entity.party.person.PersonDomainDTO;

public class PromotionBagDomainDTO {
	
	public static final String ID_ALIAS = "bag_id";
	
	public static final String COUPONS_ALIAS = "coupons";
	
	public static final String CURRENCY_CODE_ALIAS = "curr";
	
	public static final String LOCALE_CODE_ALIAS = "lcl";
	
	private final Long bagId;
	
	private List<String> coupons;
	
	private final Set<PromotionBagItemDomainDTO> promotionBagItems = new HashSet<PromotionBagItemDomainDTO>();
	
	private final PersonDomainDTO customer;
	
	private final String locale;
	
	private final String currency;
	
	
	public PromotionBagDomainDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagId = ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.customer = new PersonDomainDTO(tuple, aliasToIndexMap);
		this.currency = tuple[aliasToIndexMap.get(CURRENCY_CODE_ALIAS)].toString();
		this.locale = tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
		this.coupons = Arrays.asList(tuple[aliasToIndexMap.get(COUPONS_ALIAS)].toString().split(","));
	}
	
	public Long getBagId() {
		return bagId;
	}

	public Set<PromotionBagItemDomainDTO> getPromotionBagItems() {
		return promotionBagItems;
	}

	public PersonDomainDTO getCustomer() {
		return customer;
	}

	public List<String> getCoupons() {
		return coupons;
	}
	
	public String getLocale() {
		return locale;
	}

	public String getCurrency() {
		return currency;
	}
	
}
