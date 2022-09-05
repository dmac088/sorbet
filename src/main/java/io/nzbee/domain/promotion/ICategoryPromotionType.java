package io.nzbee.domain.promotion;

import java.util.List;

import io.nzbee.domain.promotion.value.CategoryCode;

public interface ICategoryPromotionType {

    CategoryCode getCategoryCode();
	
	Boolean forCategoryCodes(List<String> categoryCodes);
	
}
