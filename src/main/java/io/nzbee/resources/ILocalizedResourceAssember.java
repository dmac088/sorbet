package io.nzbee.resources;

public interface ILocalizedResourceAssember<T, L>  {

	T toModel(L l, String locale, String currency);
	
}
