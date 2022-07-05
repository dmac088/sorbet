package io.nzbee.entity.party.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "party_type", schema = "mochi")
public class PartyTypeEntity {

	@Id
	@Column(name="pty_typ_id")
	private Long PartyTypeId;

	@Column(name="pty_typ_desc")
	private String partyTypeDesc;

	public PartyTypeEntity() {
	}
	
	public PartyTypeEntity(Long partyTypeId) {
		this.PartyTypeId = partyTypeId;
	}

	public Long getPartyTypeId() {
		return PartyTypeId;
	}

	public void setPartyTypeId(Long partyTypeId) {
		PartyTypeId = partyTypeId;
	}

	public String getPartyTypeDesc() {
		return partyTypeDesc;
	}

	public void setPartyTypeDesc(String partyTypeDesc) {
		this.partyTypeDesc = partyTypeDesc;
	}

}
