package io.nzbee.entity.role;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleRepository RoleRepository; 
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_READER')")
	public List<RoleEntity> getAllRoles() {
		List<RoleEntity> Roles = new ArrayList<>();
		Iterator<RoleEntity> i = RoleRepository.findAll().iterator();
		while(i.hasNext()) {
			Roles.add(i.next());
		}
		return Roles;
	}

	@Override
	@PreAuthorize("hasAuthority('ROLE_READ')")
	public Optional<RoleEntity> getRole(Long id) {
		Optional<RoleEntity> p = RoleRepository.findById(id);
		return p;
	}
	
	@PreAuthorize("hasAuthority('ROLE_CREATE')")
	public void addRole(RoleEntity Role) {
		RoleRepository.save(Role);
	}
	
	@PreAuthorize("hasAuthority('ROLE_UPDATE')")
	public void updateRole(String id, RoleEntity Role) {
		RoleRepository.save(Role);
	}
	
	@PreAuthorize("hasAuthority('ROLE_DELETE')")
	public void deleteRole(Long id) {
		RoleRepository.deleteById(id);
	}
	
}
