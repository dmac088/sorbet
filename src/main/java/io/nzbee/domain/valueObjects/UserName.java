package io.nzbee.domain.valueObjects;

public class UserName {

	private String userName;

	public UserName(String userName) {
		super();
		this.userName = userName;
	}
	
	public Boolean sameAs(UserName other) {
		return this.userName.equals(other.userName);
	}

	@Override
	public String toString() {
        return userName;
    }
	
}
