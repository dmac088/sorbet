package io.nzbee.resources.discovery;


public interface ISimpleResourceAssembler<T, L> {

	T toModel(L l);

}
