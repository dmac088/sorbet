package io.nzbee.resources;


public interface ISimpleResourceAssembler<T, L> {

	T toModel(L l);

}
