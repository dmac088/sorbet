package io.nzbee.entity.party.organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.nzbee.entity.party.Party;

@Entity
@Table(name = "organisation", schema = "mochi")
public class OrganizationEntity   {
	
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="pty_id")
	private Party organisationParty;
	
	@Id
	@Column(name="pty_id")
	private Long organisationId;
	
	@Column(name="org_nme")
	private String organizationName;
	
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Party getOrganisatoinParty() {
		return organisationParty;
	}

	public void setOrganisatoinParty(Party organisationParty) {
		this.organisationParty = organisationParty;
	}
	
}
