package io.nzbee.util.promotion.mechanic;

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
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PromotionMechanicMasterService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPromotionMechanicService pmService;
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
    @Transactional
	public void writePromotionMechanicMaster(String fileName) {
		LOGGER.debug("called writePromotionMechanicMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<PromotionMechanicMasterSchema> readValues =
	        	mapper.readerFor(PromotionMechanicMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(pm -> {
	        	this.persistPromotionMechanicMaster(pm);
	        });
	        
	        
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	}
	
	public void persistPromotionMechanicMaster(PromotionMechanicMasterSchema pms) {
		LOGGER.debug("called persistPromotionMechanicMaster() ");
		
		PromotionMechanicEntity pm = mapToPromotionMechanic(pms.get_PROMOTION_MECHANIC_CODE(),
													  pms.get_PROMOTION_MECHANIC_DESC());

		pmService.save(pm);
	}
	
	private PromotionMechanicEntity mapToPromotionMechanic(	 String promotionMechanicCode,
													 String promotionMechanicDesc
												 ) {
		LOGGER.debug("called mapToPromotionMechanic() ");
		
		Optional<PromotionMechanicEntity> oPm = pmService.findByCode(promotionMechanicCode);
		
		PromotionMechanicEntity pm = oPm.isPresent()
							   ? oPm.get()
							   : new PromotionMechanicEntity();
		
		pm.setPromotionMechanicCode(promotionMechanicCode);
		pm.setPromotionMechanicDesc(promotionMechanicDesc);
		
		return pm;
	}
	
	
	
}
