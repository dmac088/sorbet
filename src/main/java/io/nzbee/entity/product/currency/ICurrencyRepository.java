package io.nzbee.entity.product.currency;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICurrencyRepository extends JpaRepository<Currency, Long> {
	
	Optional<Currency> findByCode(String code); 
	
	List<Currency> findAll();
}
