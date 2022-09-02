package io.nzbee.domain.promotion;

public interface IBagPromotion<T> extends IPromotion {

	void execute(T object);
	
}
