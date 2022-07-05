package io.nzbee.util.promotion.regular;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.entity.promotion.attribute.PromotionAttributeEntity;
import io.nzbee.entity.promotion.level.IPromotionLevelService;
import io.nzbee.entity.promotion.level.PromotionLevelEntity;
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicEntity;
import io.nzbee.entity.promotion.product.PromotionProductEntity;
import io.nzbee.entity.promotion.type.IPromotionTypeService;
import io.nzbee.entity.promotion.type.PromotionTypeEntity;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.entity.promotion.IPromotionEntityService;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class PromotionProductMasterService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPromotionEntityService promotionService;
	
	@Autowired
	private IPromotionTypeService promotionTypeService;
	
	@Autowired
	private IPromotionMechanicService promotionMechanicService;
	
	@Autowired
	private IPromotionLevelService promotionLevelService;

	@Autowired
	private FileStorageServiceUpload fileStorageServiceUpload;

	
	@Transactional
	public void writePromotionProductMaster(String fileName) {
		LOGGER.debug("called writePromotionProductMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t')
					.withQuoteChar('"');

			CsvMapper mapper = new CsvMapper();
			MappingIterator<PromotionProductSchema> readValues = mapper.readerFor(PromotionProductSchema.class)
					.with(bootstrapSchema).readValues(file);

			readValues.readAll().stream().forEach(c -> {
				this.persistPromotionProductMaster(c);
			});

		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	}

	public void persistPromotionProductMaster(PromotionProductSchema pms) {
		LOGGER.debug("called persistPromotionProductMaster() ");

		Optional<PromotionEntity> op = promotionService.findByCode(pms.get_PROMOTION_CODE());
										
		PromotionEntity p = 	(op.isPresent()) 
								? (PromotionEntity) op.get()
								: new PromotionEntity();
							
		p.setPromotionCode(pms.get_PROMOTION_CODE());
		
		
		PromotionProductEntity pe = (op.isPresent()) 
									? (PromotionProductEntity) p.getPromotionProduct()
									: new PromotionProductEntity();
									
		p.setPromotionProduct(pe);	
		pe.setProductPromotion(p);
									
		LocalDateTime psd = LocalDateTime.parse(pms.get_PROMOTION_START_DATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime ped = LocalDateTime.parse(pms.get_PROMOTION_END_DATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		Optional<PromotionMechanicEntity> pm = promotionMechanicService.findByCode(pms.get_PROMOTION_MECHANIC_CODE());
		
		Optional<PromotionLevelEntity> pl = promotionLevelService.findByCode(pms.get_PROMOTION_LEVEL_CODE());
		
		Optional<PromotionTypeEntity> pt = promotionTypeService.findByCode(pms.get_PROMOTION_TYPE_CODE());
		
		PromotionAttributeEntity paEN = mapAttribute(p, pms.get_PROMOTION_DESC_EN(), Constants.localeENGB);
		PromotionAttributeEntity paCN = mapAttribute(p, pms.get_PROMOTION_DESC_HK(), Constants.localeZHHK);
		paEN.setPromotion(p);
		paCN.setPromotion(p);
		p.addAttribute(paEN);
		p.addAttribute(paCN);
		p.setPromotionStartDate(psd);
		p.setPromotionEndDate(ped);
		p.setPromotionMechanic(pm.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.promotionMechanicNotFound, Constants.localeENGB, pms.get_PROMOTION_MECHANIC_CODE())));
		p.setPromotionLevel(pl.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.promotionLevelNotFound, Constants.localeENGB, pms.get_PROMOTION_LEVEL_CODE())));
		p.setPromotionType(pt.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.promotionTypeNotFound, Constants.localeENGB, pms.get_PROMOTION_TYPE_CODE())));
		p.setPromotionActive(pms.get_PROMOTION_ACTIVE());

		promotionService.save(p);
	}
	
	private PromotionAttributeEntity mapAttribute(PromotionEntity p, String promotionDesc, String locale) {
		LOGGER.debug("called mapAttribute() ");

		Optional<PromotionAttributeEntity> opa = p.getAttributes().stream()
					.filter(a -> a.getLocale().equals(locale)).findFirst();

		PromotionAttributeEntity pa = (opa.isPresent()) ? opa.get() : new PromotionAttributeEntity();
		pa.setLocale(locale);
		pa.setPromotionDesc(promotionDesc);
		
		return pa;
	}

}
