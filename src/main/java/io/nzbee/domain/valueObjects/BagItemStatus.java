package io.nzbee.domain.valueObjects;

public class BagItemStatus {

	private String statusCode;

	public BagItemStatus(String statusCode) {
		super();
		this.statusCode = statusCode;
	}
	
	public Boolean sameAs(BagItemStatus other) {
		return this.statusCode.equals(other.statusCode);
	}
	
	@Override
	public String toString() {
        return statusCode;
    }
	
}
