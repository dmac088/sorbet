package io.nzbee.resources.customer.address;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.controllers.CustomerController;
import io.nzbee.view.customer.address.CustomerAddressDTOOut;

@Component
public class CustomerAddressResourceAssembler extends RepresentationModelAssemblerSupport<CustomerAddressDTOOut, CustomerAddressResource> {

	public CustomerAddressResourceAssembler() {
		super(CustomerController.class, CustomerAddressResource.class);
	}

	@Override
	public CustomerAddressResource toModel(CustomerAddressDTOOut c) {
		CustomerAddressResource cr = new CustomerAddressResource(c);
		cr.add(linkTo(methodOn(CustomerController.class).getCustomerAddress(null, null)).withSelfRel());
		cr.add(linkTo(methodOn(CustomerController.class).updateCustomerAddress(null, null, null)).withRel("updateAddress"));
		return cr;
	}

}
