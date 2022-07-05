package io.nzbee.entity.party;

import java.util.Set;


public interface IPartyDao{
	
	Set<Party> findAllByRoleName(String roleClassType);

}
