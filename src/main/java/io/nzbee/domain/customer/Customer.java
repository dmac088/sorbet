package io.nzbee.domain.customer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;

//we don't bother about dealing with party, even though our data model and persistence layer cater
//for both persons and organizations in the "Role" of customers, we're only interested in running a 
//B2C business, therefore all customers will be Persons (at least through the web front end for now)
public class Customer {
    
	private Bag bag;

    private String customerId;
	
    private String userName;
    
    private String password;
    
	private String partyType;
    
    private boolean enabled;
    
    private boolean error;
    
    private Map<String, String> errors;

    public Customer(
			String userName,
			String customerId,
			boolean isEnabled) {

		this.userName = userName;
		this.customerId = customerId;
		this.partyType = Constants.partyTypePerson;
		this.enabled = isEnabled;
		this.bag = new Bag(this);
		this.error = false;
		this.errors = new LinkedHashMap<String, String>();
	}
    
	public String getCustomerID() {
		return customerId;
	}

	public String getUserName() {
        return userName;
    }
    
    public boolean isEnabled() {
		return enabled;
	}
	
	public String getPartyType() {
		return partyType;
	}
	
	public boolean isError() {
		return error;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setPassword(String password, String matchingPassword) {
		if(password.equals(matchingPassword)) {
			this.password = password;
			return;
		}
		this.error = true;
		this.errors.put("PWDMTCH", "The passwords do not match!");
	}
	
	public String getPassword() {
		return password;
	}
	
    public Bag getBag() {
		return bag;
	}


	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Customer pcDto = (Customer) o;
	     return this.customerId == pcDto.customerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}
	
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Customer [CustomerId=").append(customerId)
        				.append(", username=").append(userName)
        				.append(", role=").append("]");
        return builder.toString();
    }

}