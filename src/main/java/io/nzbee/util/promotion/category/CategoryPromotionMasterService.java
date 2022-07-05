package io.nzbee.util.promotion.category;

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
import io.nzbee.entity.promotion.product.PromotionProductEntity;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.promotion.IPromotionEntityService;
import io.nzbee.util.FileStorageServiceUpload;

@Service
public class CategoryPromotionMasterService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPromotionEntityService promotionService;
	
	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private FileStorageServiceUpload fileStorageServiceUpload;

	@Transactional
	public void writeCategoryPromotionMaster(String fileName) {
		LOGGER.debug("called writeCategoryPromotionMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t')
					.withQuoteChar('"');

			CsvMapper mapper = new CsvMapper();
			MappingIterator<CategoryPromotionMasterSchema> readValues = mapper.readerFor(CategoryPromotionMasterSchema.class)
					.with(bootstrapSchema).readValues(file);

			readValues.readAll().stream().forEach(c -> {
				this.persistCategoryPromotionMaster(c);
			});

		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	}

	public void persistCategoryPromotionMaster(CategoryPromotionMasterSchema pms) {
		LOGGER.debug("called persistCategoryPromotionMaster() ");

		PromotionProductEntity p = (PromotionProductEntity) promotionService.findByCode(pms.get_PROMOTION_CODE()).get().getPromotionProduct();
		Optional<CategoryEntity> oc = categoryService.findByCode(pms.get_CATEGORY_CODE());

		p.getProductPromotion().setPromotionCode(pms.get_PROMOTION_CODE());
		p.addCategory(oc.get());

		promotionService.save(p.getProductPromotion());
	}
	

}
