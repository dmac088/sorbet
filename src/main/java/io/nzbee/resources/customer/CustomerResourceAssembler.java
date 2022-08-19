package io.nzbee.resources.customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.Constants;
import io.nzbee.controllers.BagController;
import io.nzbee.controllers.CustomerController;
import io.nzbee.resources.ISimpleResourceAssembler;
import io.nzbee.view.customer.CustomerDTOOut;

@Component
public class CustomerResourceAssembler extends RepresentationModelAssemblerSupport<CustomerDTOOut, CustomerResource> implements ISimpleResourceAssembler<CustomerLinkResource> {

	public CustomerResourceAssembler() {
		super(CustomerController.class, CustomerResource.class);
	}

	private Link l0 = linkTo(methodOn(CustomerController.class).registerNewCustomer(null, null, null, null)).withRel("register");
	private Link l1 = linkTo(methodOn(CustomerController.class).getCustomerAddress(null, null)).withRel("address");
	private Link l2 = linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withRel("bag");
	private Link l3 = linkTo(methodOn(CustomerController.class).confirmRegistration(null, null, null)).withRel("confirm");
	private Link l4 = linkTo(methodOn(CustomerController.class).getCustomer(null)).withRel("customer");
	
	@Override
	public CustomerResource toModel(CustomerDTOOut c) {
		CustomerResource cr = new CustomerResource(c);
		
		cr.add(linkTo(methodOn(CustomerController.class).getCustomer(null)).withSelfRel());
		cr.add(l1);
		cr.add(l2);
		return cr;
	}

	@Override
	public CustomerLinkResource toModel() {
		CustomerLinkResource cr = new CustomerLinkResource();
		cr.add(Link.of(Constants.tokenUrl).withRel("token"));
		cr.add(l0);
		cr.add(l1);
		cr.add(l2);
		cr.add(l3);
		cr.add(l4);
		return cr;
	}

}
