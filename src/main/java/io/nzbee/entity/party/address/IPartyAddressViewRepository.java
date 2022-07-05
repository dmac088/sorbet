package io.nzbee.entity.party.address;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import io.nzbee.entity.party.address.entity.PartyAddressEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartyAddressViewRepository extends JpaRepository<PartyAddressEntity, Long> {

	Optional<PartyAddressEntity> findByPartyPartyUserUsernameAndTypeAddressTypeCode(String userName, String addressTypeCode);

	@Query(	  " SELECT new io.nzbee.entity.party.address.PartyAddressViewDTO(a.addressId, "
			+ "																 a.addressLine1, "
			+ "																 a.addressLine2, "
			+ "																 a.addressLine3, "
			+ "																 a.addressCountry, "
			+ "																 a.addressPostCode,"
			+ "																 at.addressTypeId,"
			+ "																 at.addressTypeCode,"
			+ "																 at.addressTypeDesc, "
			+ "																 psn.personId, "
			+ "																 psn.givenName, "
			+ "																 psn.familyName, "
			+ "																 u.username, "
			+ "																 rc.customerNumber, "	
			+ "																 u.enabled "			
			+ ") "
			+ " FROM PartyAddressEntity a "
			+ " JOIN a.type at"
			+ " JOIN a.party p "
			+ " JOIN p.partyPerson psn "
			+ " JOIN p.partyRoles r "
			+ " JOIN r.roleType rt "
			+ " JOIN p.partyUser u "
			+ " JOIN r.roleCustomer rc "
			+ " WHERE u.username = :userName "
			+ " AND rt.roleTypeDesc = :roleName" 
			+ " AND at.addressTypeCode = :addressTypeCode ")
	Optional<PartyAddressViewDTO> findByUsernameAndRole(String userName, String roleName, String addressTypeCode);
	
}
 