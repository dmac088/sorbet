package io.nzbee.integration.entity.beans.party;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.ErrorKeys;
import io.nzbee.domain.valueObjects.Locale;
import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.entity.PartyAddressEntity;
import io.nzbee.entity.party.address.type.AddressTypeEntity;
import io.nzbee.entity.party.address.type.IAddressTypeService;
import io.nzbee.entity.party.person.PersonEntity;
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

@Service(value = "partyEntityBeanFactory")
public class PartyEntityBeanFactory implements IPartyEntityBeanFactory {

	@Autowired
	private IUserRoleService userRoleService;

	@Autowired
	private IPartyTypeService partyTypeService;

	@Autowired
	private IAddressTypeService addressTypeService;

	@Autowired
	private IRoleTypeRepository roleTypeRepository;

	private String TEST_NEW_USERNAME = "mackdada";
	private String TEST_NEW_PASSWORD = "mackdad1234";
	private Boolean TEST_ISENABLED = false;
	private String TEST_CUSTOMERID = "9832145731";
	private String TEST_GIVENNAME = "Test Given Name";
	private String TEST_FAMILYNAME = "Test Family Name";

	@Override
	public PersonEntity getBean() {
		// create a new user
		Optional<PartyTypeEntity> opte = partyTypeService.findByPartyTypeDesc(Constants.partyTypePerson);
		UserRole ur = userRoleService.findByName(Constants.partyRoleCustomer);
		User u = new User();
		u.addUserRole(ur);
		ur.addUser(u);
		u.setUsername(TEST_NEW_USERNAME);
		u.setPassword(TEST_NEW_PASSWORD);
		u.setEnabled(false);
		u.setUsing2FA(false);

		RoleEntity re = new RoleEntity();
		CustomerEntity c = new CustomerEntity();
		re.setRoleCustomer(c);
		c.setCustomerRole(re);
		c.setCustomerNumber(TEST_CUSTOMERID);
		re.setRoleStart(LocalDateTime.now());

		RoleTypeEntity roleType = roleTypeRepository.findByRoleTypeDesc(Constants.partyRoleCustomer).get();
		re.setRoleType(roleType);

		Party pty = new Party();
		pty.setPartyType(opte.get());
		pty.setUser(u);

		PersonEntity p = new PersonEntity();

		p.setPersonParty(pty);
		pty.setPartyPerson(p);

		p.setGivenName(TEST_GIVENNAME);
		p.setFamilyName(TEST_FAMILYNAME);
		p.setEnabled(TEST_ISENABLED);

		p.getPersonParty().setUser(u);
		u.setParty(p.getPersonParty());
		p.getPersonParty().addRole(re);
		re.setRoleParty(p.getPersonParty());

		// by default we create a new bag for the customer on sign-up
		BagEntity b = new BagEntity();
		b.setBagCreatedDateTime(LocalDateTime.now());
		b.setBagUpdatedDateTime(LocalDateTime.now());
		p.getPersonParty().setBag(b);
		b.setParty(p.getPersonParty());

		// we need to add some empty addresses just like we create an empty bag
		PartyAddressEntity ba = new PartyAddressEntity();
		PartyAddressEntity ma = new PartyAddressEntity();
		Optional<AddressTypeEntity> obat = addressTypeService.findByCode(Constants.billingAddressCode);
		Optional<AddressTypeEntity> omat = addressTypeService.findByCode(Constants.shippingAddressCode);

		AddressTypeEntity bat = obat.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.addressTypeNotFound,
				Locale.localize(Constants.localeENGB, Constants.currencyHKD), Constants.billingAddressCode));
		AddressTypeEntity mat = omat.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.addressTypeNotFound,
				Locale.localize(Constants.localeENGB, Constants.currencyHKD), Constants.shippingAddressCode));

		ba.setType(bat);
		ma.setType(mat);

		p.getPersonParty().addAddress(ba);
		p.getPersonParty().addAddress(ma);

		return p;
	}
	
	@Override
	public User getUserBean() {
		// create a new user
		Optional<PartyTypeEntity> opte = partyTypeService.findByPartyTypeDesc(Constants.partyTypePerson);
		UserRole ur = userRoleService.findByName(Constants.partyRoleCustomer);
		User u = new User();
		u.addUserRole(ur);
		ur.addUser(u);
		u.setUsername(TEST_NEW_USERNAME);
		u.setPassword(TEST_NEW_PASSWORD);
		u.setEnabled(false);
		u.setUsing2FA(false);

		RoleEntity re = new RoleEntity();
		CustomerEntity c = new CustomerEntity();
		re.setRoleCustomer(c);
		c.setCustomerRole(re);
		c.setCustomerNumber(TEST_CUSTOMERID);
		re.setRoleStart(LocalDateTime.now());

		RoleTypeEntity roleType = roleTypeRepository.findByRoleTypeDesc(Constants.partyRoleCustomer).get();
		re.setRoleType(roleType);

		Party pty = new Party();
		pty.setPartyType(opte.get());
		pty.setUser(u);

		PersonEntity p = new PersonEntity();

		p.setPersonParty(pty);
		pty.setPartyPerson(p);

		p.setGivenName(TEST_GIVENNAME);
		p.setFamilyName(TEST_FAMILYNAME);
		p.setEnabled(TEST_ISENABLED);

		p.getPersonParty().setUser(u);
		u.setParty(p.getPersonParty());
		p.getPersonParty().addRole(re);
		re.setRoleParty(p.getPersonParty());

		// by default we create a new bag for the customer on sign-up
		BagEntity b = new BagEntity();
		b.setBagCreatedDateTime(LocalDateTime.now());
		b.setBagUpdatedDateTime(LocalDateTime.now());
		p.getPersonParty().setBag(b);
		b.setParty(p.getPersonParty());

		// we need to add some empty addresses just like we create an empty bag
		PartyAddressEntity ba = new PartyAddressEntity();
		PartyAddressEntity ma = new PartyAddressEntity();
		Optional<AddressTypeEntity> obat = addressTypeService.findByCode(Constants.billingAddressCode);
		Optional<AddressTypeEntity> omat = addressTypeService.findByCode(Constants.shippingAddressCode);

		AddressTypeEntity bat = obat.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.addressTypeNotFound,
				Locale.localize(Constants.localeENGB, Constants.currencyHKD), Constants.billingAddressCode));
		AddressTypeEntity mat = omat.orElseThrow(() -> new EntityNotFoundException(ErrorKeys.addressTypeNotFound,
				Locale.localize(Constants.localeENGB, Constants.currencyHKD), Constants.shippingAddressCode));

		ba.setType(bat);
		ma.setType(mat);

		p.getPersonParty().addAddress(ba);
		p.getPersonParty().addAddress(ma);

		return u;
		
	}

}
