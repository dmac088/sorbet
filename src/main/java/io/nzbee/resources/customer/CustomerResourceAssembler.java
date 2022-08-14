package io.nzbee.resources.customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.controllers.CustomerController;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.resources.controllers.BagController;
import io.nzbee.view.customer.CustomerDTOOut;

@Component
public class CustomerResourceAssembler extends RepresentationModelAssemblerSupport<CustomerDTOOut, CustomerResource> implements ISimpleResourceAssembler<CustomerLinkResource> {

	public CustomerResourceAssembler() {
		super(CustomerController.class, CustomerResource.class);
	}

	@Override
	public CustomerResource toModel(CustomerDTOOut c) {
		CustomerResource cr = new CustomerResource(c);
		cr.add(linkTo(methodOn(CustomerController.class).getCustomer(null)).withSelfRel());
		cr.add(linkTo(methodOn(CustomerController.class).getCustomerAddress(null, null)).withRel("address"));
		cr.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withRel("bag"));
		return cr;
	}

	@Override
	public CustomerLinkResource toModel() {
		CustomerLinkResource cr = new CustomerLinkResource();
		cr.add(linkTo(methodOn(CustomerController.class).registerNewCustomer(null, null, null, null)).withRel("register"));
		cr.add(linkTo(methodOn(CustomerController.class).getCustomerAddress(null, null)).withRel("address"));
		cr.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withRel("bag"));
		return cr;
	}

}
