package io.nzbee.entity.role.supplier;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private ISupplierRepository supplierRespository;

	@Override
	public Optional<SupplierEntity> findById(Long id) {
		return supplierRespository.findById(id);
	}

	@Override
	public Optional<SupplierEntity> findByCode(String code) {
		return supplierRespository.findBySupplierNumber(code);
	}

	@Override
	public void save(SupplierEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SupplierEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SupplierEntity t) {
		// TODO Auto-generated method stub
		
	}

}
