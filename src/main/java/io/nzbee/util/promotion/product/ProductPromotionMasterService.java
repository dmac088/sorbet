package io.nzbee.util.promotion.product;

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
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.promotion.IPromotionEntityService;
import io.nzbee.util.FileStorageServiceUpload;
import io.nzbee.util.promotion.product.ProductPromotionMasterService;

@Service
public class ProductPromotionMasterService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPromotionEntityService promotionService;
	
	@Autowired
	private IProductService productService;

	@Autowired
	private FileStorageServiceUpload fileStorageServiceUpload;

	@Transactional
	public void writeProductPromotionMaster(String fileName) {
		LOGGER.debug("called writePromotionMaster with parameter {} ", fileName);
		try {
			File file = fileStorageServiceUpload.loadFileAsResource(fileName).getFile();
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator('\t')
					.withQuoteChar('"');

			CsvMapper mapper = new CsvMapper();
			MappingIterator<ProductPromotionMasterSchema> readValues = mapper.readerFor(ProductPromotionMasterSchema.class)
					.with(bootstrapSchema).readValues(file);

			readValues.readAll().stream().forEach(c -> {
				this.persistProductPromotionMaster(c);
			});

		} catch (IOException e) {
			LOGGER.error(e.toString());
		}
	}

	public void persistProductPromotionMaster(ProductPromotionMasterSchema pms) {
		LOGGER.debug("called persistProductPromotionMaster() ");

		PromotionProductEntity p = (PromotionProductEntity) promotionService.findByCode(pms.get_PROMOTION_CODE()).get().getPromotionProduct();
		Optional<ProductEntity> oc = productService.findByCode(pms.get_PRODUCT_UPC());

		p.getProductPromotion().setPromotionCode(pms.get_PROMOTION_CODE());
		p.addProduct(oc.get());

		promotionService.save(p.getProductPromotion());
	}

}
