package io.nzbee.util.brand;

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
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.brand.attribute.BrandAttributeEntity;
import io.nzbee.util.FileStorageServiceUpload;

@Service
@Transactional
public class BrandMasterService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBrandService brandService; 
	
    @Autowired
    private FileStorageServiceUpload fileStorageServiceUpload;
	
    @Transactional
	public void writeBrandMaster(String fileName) {
		LOGGER.debug("called writeBrandMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
	    	CsvSchema bootstrapSchema = 
	    			CsvSchema.emptySchema()
	    				.withHeader()
	    				.withColumnSeparator('\t')
	    				.withQuoteChar('"');
	    	
	        CsvMapper mapper = new CsvMapper();
	        MappingIterator<BrandMasterSchema> readValues =
	        	mapper.readerFor(BrandMasterSchema.class).with(bootstrapSchema).readValues(file);
	        
	        readValues.readAll().stream().forEach(c -> {
	        	this.persistBrandMaster(c);
	        });
	        
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	}
	
	public void persistBrandMaster(BrandMasterSchema b) {
		LOGGER.debug("called persistBrandMaster() ");
		
		BrandEntity bEN = mapToBrand( 	b.get_BRAND_CODE(),
										b.get_BRAND_DESC_EN(),
										Constants.localeENGB);
				
		brandService.save(bEN);
		
		BrandEntity bCN = mapToBrand(	b.get_BRAND_CODE(),
				 				 		b.get_BRAND_DESC_HK(),
				 				 		Constants.localeZHHK);
		
		brandService.save(bCN);
	}
	
	
	private BrandEntity mapToBrand(	 String brandCode,
									 String brandDesc,
									 String locale) {
		
		Optional<BrandEntity> ob = brandService.findByCode(brandCode);
						
		BrandEntity b = 
		(ob.isPresent())
		? ob.get() 
		: new BrandEntity();
		
		BrandAttributeEntity ba = new BrandAttributeEntity();
		if(ob.isPresent()) {
			Optional<BrandAttributeEntity> oba =
					ob.get().getAttributes().stream().filter(a -> a.getLclCd().equals(locale)).findFirst();
			
			ba = (oba.isPresent()) 
			? oba.get()
			: new BrandAttributeEntity();
		}
							
		b.setBrandCode(brandCode);
		ba.setBrandDesc(brandDesc);
		ba.setLclCd(locale);
		b.addAttribute(ba);
		ba.setBrand(b);
		
		return b;
	}
	
}
