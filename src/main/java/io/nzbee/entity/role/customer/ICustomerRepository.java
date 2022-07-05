package io.nzbee.entity.role.customer;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerRepository")
public interface ICustomerRepository  extends JpaRepository<CustomerEntity, Long> {

	List<CustomerEntity> findAll();
	
	Optional<CustomerEntity> findByCustomerNumber(String customerNumber);

}
