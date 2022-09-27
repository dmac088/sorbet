package io.nzbee.domain.promotion.bag;

import io.nzbee.domain.valueObjects.Quantity;
import io.nzbee.domain.valueObjects.UserName;

public interface IPromotionBag {

	UserName getUserName();

	Quantity getQuantity();
	
	int getItemCount();

	Boolean isErrors();

	void setErrors(Boolean errors);

	String getError();

	void setError(String error);

}
