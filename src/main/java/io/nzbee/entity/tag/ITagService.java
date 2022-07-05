package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ITagService {

	Optional<TagEntity> findByCode(String tagCode);

	List<TagEntity> findAll();

	List<TagEntity> findAll(Set<String> codes);

	String tokenToCode(String token);

	void save(TagEntity t);

	void update(TagEntity t);

	void delete(TagEntity t);
	
}
