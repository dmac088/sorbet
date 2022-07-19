package io.nzbee.entity.party.person;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.entity.PartyAddressEntity;
import io.nzbee.entity.party.address.type.AddressTypeEntity;
import io.nzbee.entity.party.address.type.IAddressTypeService;
import io.nzbee.entity.party.type.IPartyTypeService;
import io.nzbee.entity.party.type.PartyTypeEntity;
import io.nzbee.entity.role.IRoleTypeRepository;
import io.nzbee.entity.role.RoleEntity;
import io.nzbee.entity.role.RoleTypeEntity;
import io.nzbee.entity.role.customer.CustomerEntity;
import io.nzbee.exceptions.EntityNotFoundException;
import io.nzbee.security.user.User;
import io.nzbee.security.user.role.IUserRoleService;
import io.nzbee.security.user.role.UserRole;
import io.nzbee.view.customer.CustomerDTOIn;
import io.nzbee.view.customer.CustomerDTOOut;

public class CustomerViewMapperImpl implements ICustomerViewMapper {

	@Autowired
	private IRoleTypeRepository roleTypeRepository;
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired 
	private IPartyTypeService partyTypeService;
	
	@Autowired
	private IAddressTypeService addressTypeService;
	
	
	@Override
	public CustomerDTOOut toView(PersonEntity person) {
		CustomerDTOOut c = new CustomerDTOOut();
		c.setCustomerId(((CustomerEntity) person.getPersonParty().getPartyRoles().stream().filter(r -> r.getRoleType().getRoleTypeDesc().equals(Constants.partyRoleCustomer)).findAny().get().getRoleCustomer()).getCustomerNumber());
		c.setGivenName(person.getGivenName());
		c.setFamilyName(person.getFamilyName());
		return c;
	}
	
	@Override
	public Customer toDomain(CustomerDTOIn customer) {
		return new Customer(customer.getUserName(),
							customer.getCustomerId(),
							customer.isEnabled());
		
	}

	@Override
	public PersonEntity toEntity(CustomerDTOIn dtoObject, String locale) {
		//in our domain world a customer is a person
		
		Optional<PartyTypeEntity> opte = partyTypeService.findByPartyTypeDesc(Constants.partyTypePerson);
		UserRole ur = userRoleService.findByName(Constants.partyRoleCustomer);
		User u = new User();
		u.addUserRole(ur);
		ur.addUser(u);
		u.setUsername(dtoObject.getUserName());
		u.setPassword(dtoObject.getPassword());
		u.setEnabled(false);
		u.setUsing2FA(false);
		
		RoleEntity re = new RoleEntity();
		CustomerEntity c = new CustomerEntity();
		re.setRoleCustomer(c);
		c.setCustomerRole(re);
		c.setCustomerNumber(dtoObject.getCustomerId());
		re.setRoleStart(new Date());
		
		RoleTypeEntity roleType = roleTypeRepository.findByRoleTypeDesc(Constants.partyRoleCustomer).get();
		re.setRoleType(roleType);
		
		Party pty = new Party();
		pty.setPartyType(opte.get());
		pty.setUser(u);
		
		PersonEntity p = new PersonEntity();
			
		p.setPersonParty(pty);
		pty.setPartyPerson(p);
		
		p.setGivenName(dtoObject.getGivenName());
		p.setFamilyName(dtoObject.getFamilyName());
		p.setEnabled(dtoObject.isEnabled());
		
		p.getPersonParty().setUser(u);
		u.setParty(p.getPersonParty());
		p.getPersonParty().addRole(re);
		re.setRoleParty(p.getPersonParty());
		
		//by default we create a new bag for the customer on sign-up
		BagEntity b = new BagEntity();
		b.setBagCreatedDateTime(LocalDateTime.now());
		b.setBagUpdatedDateTime(LocalDateTime.now());
		p.getPersonParty().setBag(b);
		b.setParty(p.getPersonParty());
		
		//we need to add some empty addresses just like we create an empty bag
		PartyAddressEntity ba = new PartyAddressEntity();
		PartyAddressEntity ma = new PartyAddressEntity();
		Optional<AddressTypeEntity> obat = addressTypeService.findByCode(Constants.billingAddressCode);
		Optional<AddressTypeEntity> omat = addressTypeService.findByCode(Constants.shippingAddressCode);
		
		AddressTypeEntity bat = obat.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.addressTypeNotFound, locale, Constants.billingAddressCode));
		AddressTypeEntity mat = omat.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.addressTypeNotFound, locale, Constants.shippingAddressCode));
		
		ba.setType(bat);
		ma.setType(mat);
		
		p.getPersonParty().addAddress(ba);
		p.getPersonParty().addAddress(ma);
		
		return p;
	}

}
