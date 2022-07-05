package io.nzbee.entity.bag.status;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IBagItemStatusRepository extends JpaRepository<BagItemStatus, Long>  {

	Optional<BagItemStatus> findByBagStatusCode(String code);
	
}
