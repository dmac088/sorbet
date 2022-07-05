package io.nzbee.entity.party;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.nzbee.entity.bag.entity.BagEntity;
import io.nzbee.entity.party.organization.OrganizationEntity;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.party.type.PartyTypeEntity;
import io.nzbee.entity.role.RoleEntity;
import io.nzbee.security.user.User;

@Entity
@Table(name = "party", schema = "mochi")
public class Party {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "partyIdGenerator")
	@SequenceGenerator(name="partyIdGenerator", sequenceName = "party_pty_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="pty_id")
	private Long partyId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pty_typ_id")
	private PartyTypeEntity partyType;

	@OneToMany(	
				fetch = FetchType.LAZY, 
				mappedBy="roleParty",
				cascade = CascadeType.ALL,
				orphanRemoval = true
			  )
	private Set<RoleEntity> partyRoles = new HashSet<RoleEntity>();
	
	@OneToOne(	mappedBy="userParty", 
				fetch = FetchType.LAZY, 
				cascade = CascadeType.ALL,
				optional = false)
    private User partyUser;
	
	
	@OneToOne(	mappedBy="party",
				fetch = FetchType.LAZY,
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private BagEntity bag;
	
	@OneToOne(	mappedBy="personParty",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private PersonEntity partyPerson;
	

	@OneToOne(	mappedBy="organisationParty",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private OrganizationEntity partyOrganisation;
	

	public Party() {
		
	}
	
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	public User getUser() {
		return partyUser;
	}

	public void setUser(User partyUser) {
		this.partyUser = partyUser;
	}

	public Party(Long id) {
		
	}
	
	public PartyTypeEntity getPartyType() {
		return partyType;
	}

	public void setPartyType(PartyTypeEntity partyType) {
		this.partyType = partyType;
	}
	
	public Set<RoleEntity> getPartyRoles() {
		return this.partyRoles;
	}

	public void setPartyRoles(Set<RoleEntity> partyRole) {
		this.partyRoles = partyRole;
	}
	
	public void addRole(RoleEntity role) {
		this.partyRoles.add(role);
		role.setRoleParty(this);
	}
	
	public void removeRole(RoleEntity role) {
		this.partyRoles.remove(role);
		role.setRoleParty(null);
	}
	
	public void addUser(User user) {
		this.partyUser = user;
		user.setParty(this);
	}

	public BagEntity getBag() {
		return bag;
	}

	public void setBag(BagEntity bag) {
		this.bag = bag;
	}
	
	public PersonEntity getPartyPerson() {
		return partyPerson;
	}

	public OrganizationEntity getPartyOrganisation() {
		return partyOrganisation;
	}

	public void setPartyPerson(PersonEntity partyPerson) {
		this.partyPerson = partyPerson;
	}

	public void setPartyOrganisation(OrganizationEntity partyOrganisation) {
		this.partyOrganisation = partyOrganisation;
	}
	
	
	
} 
