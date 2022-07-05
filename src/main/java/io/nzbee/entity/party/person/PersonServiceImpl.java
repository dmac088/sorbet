package io.nzbee.entity.party.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IPersonRepository personRepository; 
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_READER')")
	public List<PersonEntity> findAll() {
		List<PersonEntity> Persons = new ArrayList<>();
		Iterator<PersonEntity> i = personRepository.findAll().iterator();
		while(i.hasNext()) {
			  Persons.add(i.next());
		}
		return Persons;
	}
	
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_READ')")
	public Optional<PersonEntity> findById(Long id) {
		return personRepository.findById(id);
	}

	@Override
	public void save(PersonEntity p) {
		personRepository.save(p);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_UPDATE')")
	public void update(PersonEntity p) {
		personRepository.save(p);
	}
	
	@Override
	@PreAuthorize("hasAuthority('PERSON_DELETE')")
	public void delete(PersonEntity p) {
		personRepository.delete(p);
	}

	@Override
	@PreAuthorize("hasAuthority('PERSON_READ')")
	public Optional<PersonEntity> findByUsernameAndRole(String userName, String roleType) {
		return personRepository.findByUsernameAndRoleWithBagAndItems(userName, roleType);
	}
	
	@Override
	public boolean userExists(String userName, String roleType) {
		return personRepository.findByUsernameAndRole(userName, roleType).isPresent();
	}


}
