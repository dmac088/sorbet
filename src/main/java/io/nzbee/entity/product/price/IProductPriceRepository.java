package io.nzbee.entity.product.price;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductPriceRepository extends JpaRepository<ProductPriceEntity, Long> {

	List<ProductPriceEntity> findAll();
	
	Optional<ProductPriceEntity> findByTypeCodeAndProductProductUPCAndCurrencyCode(String code, String upcCode, String currency);

	Optional<ProductPriceEntity> findByTypeCodeAndProductProductIdAndCurrencyCode(String priceTypeCode, Long productId,
			String currencyCode);
}