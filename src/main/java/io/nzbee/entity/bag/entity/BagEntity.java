package io.nzbee.entity.bag.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import io.nzbee.entity.bag.item.entity.BagItemEntity;
import io.nzbee.entity.party.Party;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "bag", schema = "mochi")
@TypeDef(
	    name = "list-array",
	    typeClass = ListArrayType.class
	)
public class BagEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "bagIdGenerator")
	@SequenceGenerator(name="bagIdGenerator", sequenceName = "bag_bag_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="bag_id")
	private Long bagId;
	
	@OneToOne
	@JoinColumn(name="pty_id")
	private Party party;

	@OneToMany(	mappedBy="bag",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<BagItemEntity> bagItems = new HashSet<BagItemEntity>();
		
	@Column(name="bag_crd_dt")
	private LocalDateTime bagCreatedDateTime;

	@Column(name="bag_upd_dt")
	private LocalDateTime bagUpdatedDateTime;
	
	@Type(type = "list-array")
	    @Column(
	        name = "coupons",
	        columnDefinition = "text[]"
	)
	private List<String> couponCodes = new ArrayList<String>();

	public Long getBagId() {
		return bagId;
	}
	
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Set<BagItemEntity> getBagItems() {
		return bagItems;
	}

	public void setBagItems(Set<BagItemEntity> bagItems) {
		this.bagItems = bagItems;
	}
	
	public void addItem(BagItemEntity bi) {
		this.getBagItems().add(bi);
		bi.setBag(this);
	}
	
	public void removeItem(BagItemEntity bi) {
		this.getBagItems().remove(bi);
		bi.setBag(null);
	}
	
	public LocalDateTime getBagCreatedDateTime() {
		return bagCreatedDateTime;
	}

	public void setBagCreatedDateTime(LocalDateTime bagCreatedDateTime) {
		this.bagCreatedDateTime = bagCreatedDateTime;
	}

	public LocalDateTime getBagUpdatedDateTime() {
		return bagUpdatedDateTime;
	}

	public void setBagUpdatedDateTime(LocalDateTime bagUpdatedDateTime) {
		this.bagUpdatedDateTime = bagUpdatedDateTime;
	}

	public List<String> getCouponCodes() {
		return couponCodes;
	}

	public void setCouponCodes(List<String> couponCodes) {
		this.couponCodes = couponCodes;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BagEntity)) return false;
        return bagId != null && bagId.equals(((BagEntity) o).getBagId());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getBagId());
    }

	
}
