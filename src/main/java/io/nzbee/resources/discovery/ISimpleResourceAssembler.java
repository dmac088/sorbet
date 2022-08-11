package io.nzbee.resources.discovery;


public interface ISimpleResourceAssembler<T> {

	T toModel(LocaliseDTO l);

}
