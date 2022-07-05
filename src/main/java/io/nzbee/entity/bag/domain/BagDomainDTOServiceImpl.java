package io.nzbee.entity.bag.domain;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.bag.entity.BagEntity;

public class BagDomainDTOServiceImpl implements IBagDomainDTOService {

	@Autowired 
	private IBagDomainDTORepository bagRepository;
	
	@Autowired 
	private IBagDomainDTODao bagDao;
	
	@Override
	public Optional<BagDomainDTO> findByCode(String currency, String userName) {
		return bagDao.findByCode(currency, userName);
	}

	@Override
	public void save(BagEntity b) {
		bagRepository.save(b);
	}

}
