package io.nzbee.resources.search;

import java.util.Objects;
import org.springframework.hateoas.RepresentationModel;
import io.nzbee.search.facet.IFacet;

public class SearchFacetModel extends RepresentationModel<SearchFacetModel> {

	private IFacet searchFacet;

	public SearchFacetModel(IFacet searchFacet) {
		this.searchFacet = searchFacet;
	}

	public IFacet getData() {
		return searchFacet;
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     IFacet sf = (IFacet) o;
	     return searchFacet.getValue() == sf.getValue();
	}

	@Override
	public int hashCode() {
		return Objects.hash(searchFacet.getValue());
	}
}
