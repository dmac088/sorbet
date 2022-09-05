package io.nzbee.domain.promotion;

import java.util.List;

public interface ICategoryPromotionType {

    String getCategoryCode();
	
	Boolean forCategoryCodes(List<String> categoryCodes);
	
}
