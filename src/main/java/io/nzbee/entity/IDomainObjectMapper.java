package io.nzbee.entity;


public interface IDomainObjectMapper<D, DTO> {

	D toDo(DTO dto);

}
