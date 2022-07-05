package io.nzbee.entity.tag.attribute;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITagAttributeRepository extends JpaRepository<TagAttributeEntity, Long> {

	List<TagAttributeEntity> findAll();

	Optional<TagAttributeEntity> findByLclCdAndTagTagId(String lcl, Long id);
	
	Optional<TagAttributeEntity> findByLclCdAndTagTagCode(String lcl, String code);

}
