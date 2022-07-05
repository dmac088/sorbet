package io.nzbee.security.user.role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userRoleService")
public class UserRoleServiceImpl implements IUserRoleService  {

    @Autowired
    private IUserRoleRepository userRoleRepository;


    @Override
    public UserRole findByName(String roleName) {
    	return userRoleRepository.findByName(roleName);
    }
    
}