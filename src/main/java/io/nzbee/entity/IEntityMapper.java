package io.nzbee.entity;


public interface IEntityMapper<D, E> {

	E toEntity(D d);

}
