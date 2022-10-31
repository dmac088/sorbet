package io.nzbee;

import java.math.BigDecimal;

import io.nzbee.domain.valueObjects.Locale;

public class Constants {

	public static final String testDbScriptPath = "src/test/resources/database";
	public static final String timeFromat = "yyyy-MM-dd HH:mm:ss.SSSX";
	public static final String localeENGB = "en-GB";
	public static final String localeZHHK = "zh-HK";
	public static final String currencyHKD = "HKD";
	public static final String currencyUSD = "USD";
	public static final String retailPriceCode = "RET01";
	public static final String markdownPriceCode = "MKD01";
	public static final String unknownProductDesc = "Not Applicable";
	public static final String activeSKUCode = "ACT01";
	public static final String shippingRootCategoryCode = "SHP01";
	public static final String TOKEN_INVALID = "invalidToken";
	public static final String TOKEN_EXPIRED = "expired";
	public static final String TOKEN_VALID = "valid";
	public static final String bagStatusCodeNew = "NEW01";
	public static final String bagStatusDescNew = "New";
	public static final String bagItemStatusCodeNew = "NEW01";
	public static final String bagItemStatusDescNew = "New";
	public static final String partyRoleCustomer = "Customer";
	public static final String categoryTypeProductCode = "PRD01";
	public static final String categoryTypeProductDiscriminator = "1";
	public static final String productTypeShippingDiscriminator = "2";
	public static final String billingAddressCode = "BIL01";
	public static final String shippingAddressCode = "MAI01";
	public static final String physicalProductDepartmentCode = "ACC01";
	public static final String shippingProductDepartmentCode = "SHP01";
	public static final String indexCategoryHierarchyRootNode = "PRM01";
	public static final String partyTypePerson = "Person";
	public static final String hongKongPostBrandCode = "HKP01";
	public static final String hongKongLocalShipCode = "HKG";
	public static final String categoryTypeProduct = "PRD01";
	public static final String hongKongPostProviderCode = "HKP";
	
	public static final String tokenUrl = "/oauth/token";
	public static final String regularBagItemType = "REG01";
	public static final String shippingBagItemType = "SHP01";
	
	public static final String promotionTypeProduct 	= "PRD";
	public static final String promotionTypeBrand 		= "BND";
	public static final String promotionTypeCategory 	= "CAT";
	public static final String promotionTypeShipping 	= "SHP";
	public static final String promotionTypeBag 		= "BAG";
	
	public static final String promotionDiscriminatorBNGN 		= "BNGNF";
	public static final String promotionDiscriminatorDISC 		= "PCTGOFF";
	public static final String promotionDiscriminatorValDISC 	= "VALPCTOFF";
	
	public static final String genericResponseSuccess = "success";
	public static final String genericResponseFailure = "failure";
	
	public static final int defaultMoneyRounding = BigDecimal.ROUND_HALF_EVEN;
	
	public static final Locale defaultLocale = Locale.localize(Constants.localeENGB, Constants.currencyUSD);
}
