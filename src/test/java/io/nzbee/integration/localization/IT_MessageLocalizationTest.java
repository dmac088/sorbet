package io.nzbee.integration.localization;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.exceptions.LocalizedException;

@RunWith(SpringRunner.class)
public class IT_MessageLocalizationTest {

	@Test
	public void givenGbEnglishProvidedLocale_whenLocalizingMessage_thenMessageComesFromDefaultMessage() {
	    LocalizedException localizedException = new LocalizedException("message", Constants.localeENGB);
	    String usEnglishLocalizedExceptionMessage = localizedException.getLocalizedMessage();
	    assertThat(usEnglishLocalizedExceptionMessage).isEqualTo("I am an exception.");
	}

	@Test
	public void givenTraditionalChineseProvidedLocale_whenLocalizingMessage_thenMessageComesFromChineseMessage() {
	    LocalizedException localizedException = new LocalizedException("message", Constants.localeZHHK);
	    String zhChineseLocalizedExceptionMessage = localizedException.getLocalizedMessage();
	    assertThat(zhChineseLocalizedExceptionMessage).isEqualTo("我是個例外。");
	}

}
