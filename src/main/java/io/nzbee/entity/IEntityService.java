package io.nzbee.entity;

import java.util.Optional;

public interface IEntityService<T> {

	 void save(T t);
	
	 void update(T t);
	
	 void delete(T t);

	 Optional<T> findById(Long id);

}
