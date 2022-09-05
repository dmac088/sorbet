package io.nzbee.domain.promotion.value;


public final class CategoryCode {

	private String categoryCode;

	public CategoryCode(String categoryCode) {
		super();
		this.categoryCode = categoryCode;
	}
	
	public Boolean sameAs(CategoryCode other) {
		return this.categoryCode.equals(other.categoryCode);
	}

}
