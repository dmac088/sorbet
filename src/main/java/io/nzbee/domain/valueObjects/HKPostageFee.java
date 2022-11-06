package io.nzbee.domain.valueObjects;

import java.math.BigDecimal;
import java.util.Currency;
import org.junit.Assert;
import io.nzbee.Constants;

public class HKPostageFee {

	private final Money money;

	public HKPostageFee(Money money) {
		super();
		this.money = money;
		assertCurrencyIsHKD();
	}
	
	private void assertCurrencyIsHKD() {
		Assert.assertTrue("Currency is HKD", money.currency().equals(Currency.getInstance(Constants.currencyHKD)));
	}
	
	public BigDecimal amount() {
		return this.money.amount();
	}
	
	public BigDecimal amount(Currency c) {
		assertCurrencyIsHKD();
		if(c.equals(Constants.currencyObjectUSD)) {
			return this.money.amount().multiply(Constants.hkdToUsdRate);
		}
		return this.money.amount();
	}
}
