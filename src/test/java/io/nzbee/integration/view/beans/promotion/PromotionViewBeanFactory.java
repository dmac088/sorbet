package io.nzbee.integration.view.beans.promotion;

import java.time.LocalDateTime;
import java.time.Month;
import org.springframework.stereotype.Service;
import io.nzbee.domain.promotion.Promotion;

@Service

public class PromotionViewBeanFactory implements IPromotionViewBeanFactory {

	public final Promotion getBean() {
		
		return new 			Promotion(	 "B2G50", 
										 LocalDateTime.of(2015, 
		                                         Month.JULY, 29, 19, 30, 40),
										 LocalDateTime.of(2019, 
		                                         Month.JULY, 29, 19, 30, 40),
										 "TST01",
										 "TST01");
		
	}
	

	
}
