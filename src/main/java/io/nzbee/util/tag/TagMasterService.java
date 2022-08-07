package io.nzbee.util.tag;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.Constants;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.entity.tag.attribute.TagAttributeEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class TagMasterService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ITagService tagService; 
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
    @Transactional
	public void writeTagMaster(String fileName) {
		LOGGER.debug("called writeTagMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<TagMasterSchema> readValues =
	        	mapper.readerFor(TagMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(c -> {
	        	this.persistTagMaster(c);
	        });
	        
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	}
	
	public void persistTagMaster(TagMasterSchema tms) {
		LOGGER.debug("called persistTagMaster() ");
		
		Optional<TagEntity> ot = tagService.findByCode(tms.get_TAG_CODE());
		
		TagEntity t = (ot.isPresent()) ? ot.get() : new TagEntity();
		
		t.setTagCode(tms.get_TAG_CODE());
		t.addTagAttribute(mapAttribute(ot, tms.get_TAG_DESC_EN(), Constants.localeENGB));
		t.addTagAttribute(mapAttribute(ot, tms.get_TAG_DESC_HK(), Constants.localeZHHK));
	
		tagService.save(t);
	}
	
	
	private TagAttributeEntity mapAttribute(Optional<TagEntity> op, String tagDesc, String locale) {
		LOGGER.debug("called mapAttribute() ");

		TagAttributeEntity ta = new TagAttributeEntity();
		if (op.isPresent()) {
			Optional<TagAttributeEntity> opa = op.get().getAttributes().stream()
					.filter(a -> a.getLclCd().equals(locale)).findFirst();

			ta = (opa.isPresent()) ? opa.get() : new TagAttributeEntity();
			ta.setTag(op.get());
		}

		ta.setLclCd(locale);
		ta.setTagDesc(tagDesc);
		
		return ta;
	}
	
}
