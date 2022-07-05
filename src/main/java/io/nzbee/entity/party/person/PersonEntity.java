package io.nzbee.entity.party.person;


import java.io.Serializable;
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
@Table(name = "person", schema = "mochi")
public class PersonEntity implements Serializable {
	
	private static final long serialVersionUID = -5851002761066421365L;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="pty_id")
	private Party personParty;

	@Id
	@Column(name="pty_id")
	private Long personId;
	
	@Column(name="psn_gvn_nm")
	private String givenName;
	
	@Column(name="psn_fml_nm")
	private String familyName;
	
	@Column(name="enb")
	private boolean enabled;

	public String getGivenName() {
		return givenName; 
	}	
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Party getPersonParty() {
		return personParty;
	}

	public void setPersonParty(Party personParty) {
		this.personParty = personParty;
	}
	
}
