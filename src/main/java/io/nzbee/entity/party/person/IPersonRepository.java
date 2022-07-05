package io.nzbee.entity.party.person;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {


	@Query(	  " SELECT new io.nzbee.entity.party.person.PersonDomainDTO(pu.username, " 
			+ "															cst.customerNumber,"
			+ "															u.enabled) "
			+ " FROM PersonEntity p "
			+ " JOIN p.personParty pp "
			+ " JOIN pp.partyUser pu " 
			+ " JOIN pp.partyRoles pr " 
			+ " JOIN pr.roleCustomer cst " 
			+ " JOIN pp.partyUser u "
			+ " WHERE p.personId = :id ")
	Optional<PersonDomainDTO> findDTOByPartyId(Long id);
	
	
	@Query(	  " SELECT p "
			+ " FROM PersonEntity p "
			+ " JOIN FETCH p.personParty pp "
			+ " JOIN pp.partyUser u "
			+ " JOIN FETCH pp.partyRoles pr"
			+ " JOIN FETCH pr.roleType rt "
			+ " LEFT JOIN FETCH pp.bag b "
			+ " LEFT JOIN FETCH b.bagItems "
			+ " WHERE u.username = :userName "
			+ " AND rt.roleTypeDesc = :roleType ")
	Optional<PersonEntity> findByUsernameAndRoleWithBagAndItems(String userName, String roleType);
	
	
	@Query(	  " SELECT p "
			+ " FROM PersonEntity p "
			+ " JOIN FETCH p.personParty pp "
			+ " JOIN pp.partyUser u "
			+ " JOIN FETCH pp.partyRoles pr"
			+ " JOIN FETCH pr.roleType rt "
			+ " WHERE u.username = :userName "
			+ " AND rt.roleTypeDesc = :roleType ")
	Optional<PersonEntity> findByUsernameAndRole(String userName, String roleType);
	
}
