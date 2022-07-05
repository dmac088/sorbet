package io.nzbee.resources.bag;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.nzbee.view.bag.BagView;

public class BagResource extends RepresentationModel<BagResource> {

	private final BagView data;
	
	@JsonCreator
	public BagResource(@JsonProperty("bag") BagView bag) {
		this.data = bag;
		
	}
	
	public BagView getData() {
		return data;
	}
}
