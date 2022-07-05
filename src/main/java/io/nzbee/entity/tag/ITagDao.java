package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ITagDao {

	Optional<TagEntity> findByCode(String code);

	Optional<TagEntity> findById(Long id);

	List<TagEntity> findAll();

	List<TagEntity> findAll(Set<String> codes);

}
