package io.nzbee.domain.valueObjects;


public final class CategoryCode {

	private String categoryCode;

	public CategoryCode(String categoryCode) {
		super();
		this.categoryCode = categoryCode;
	}
	
	public Boolean sameAs(CategoryCode other) {
		return this.categoryCode.equals(other.categoryCode);
	}
	
	@Override
	public String toString() {
        return categoryCode;
    }

}
