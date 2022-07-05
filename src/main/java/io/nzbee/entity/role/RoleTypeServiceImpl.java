package io.nzbee.entity.role;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="roleTypeService")
public class RoleTypeServiceImpl implements IRoleTypeService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IRoleTypeRepository roleTypeRepository;
	
	@Override
	public Optional<RoleTypeEntity> findByRoleTypeId(Long id) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByRoleTypeId parameters : {}", id);
		return roleTypeRepository.findByRoleTypeId(id);
	}

	@Override
	public Optional<RoleTypeEntity> findByRoleTypeDesc(String desc) {
		LOGGER.debug("call " + getClass().getSimpleName() + ".findByRoleTypeDesc parameters : {}", desc);
		return roleTypeRepository.findByRoleTypeDesc(desc);
	}

	
	
}
