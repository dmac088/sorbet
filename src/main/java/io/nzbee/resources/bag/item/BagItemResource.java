package io.nzbee.resources.bag.item;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.nzbee.view.bag.item.BagItemViewOut;

public class BagItemResource extends RepresentationModel<BagItemResource> {

	private final BagItemViewOut data;
	
	@JsonCreator
	public BagItemResource(@JsonProperty("bag") BagItemViewOut bag) {
		this.data = bag;
		
	}
	
	public BagItemViewOut getData() {
		return data;
	}
}
