package io.nzbee.entity.product.department;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
    private IDepartmentDao departmentDao;
	
	@Autowired
    private IDepartmentRepository departmentRepository;

	@Override
	public Optional<DepartmentEntity> findById(Long id) {
		return departmentRepository.findById(id);
	}

	@Override
	public Optional<DepartmentEntity> findByCode(String code) {
		return departmentRepository.findByDepartmentCode(code);
	}

	@Override
	public Optional<DepartmentDomainDTO> findByCode(String locale, String code) {
		return departmentDao.findByCode(locale, code);
	}

	@Override
	public List<DepartmentEntity> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public void save(DepartmentEntity t) {
		departmentRepository.save(t);
	}

}
