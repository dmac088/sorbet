package io.nzbee.entity.tag.attribute;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;

@Service
public class TagAttributeServiceImpl implements ITagAttributeService {

	@Autowired
	private ITagAttributeRepository tagAttributeRepository; 
	
	@Override
	public Optional<TagAttributeEntity> findById(long id) {
		return tagAttributeRepository.findById(id);
	}

	@Override
	public Optional<TagAttributeEntity> getTagAttribute(Long id, String locale) {
		return tagAttributeRepository.findByLclCdAndTagTagId(locale, id);
	}
	
	@Override
	public Optional<TagAttributeEntity> getTagAttributeEN(Long id) {
		return tagAttributeRepository.findByLclCdAndTagTagId(Constants.localeENGB, id);
	}
	
	@Override
	public Optional<TagAttributeEntity> getTagAttributeHK(Long id) {
		return tagAttributeRepository.findByLclCdAndTagTagId(Constants.localeZHHK, id);
	}
	
}
