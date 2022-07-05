package io.nzbee.resources.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.services.GenericResponse;
import io.nzbee.resources.customer.CustomerResource;
import io.nzbee.resources.customer.CustomerResourceAssembler;
import io.nzbee.resources.customer.address.CustomerAddressResource;
import io.nzbee.resources.customer.address.CustomerAddressResourceAssembler;
import io.nzbee.security.events.OnRegistrationCompleteEvent;
import io.nzbee.view.customer.CustomerDTOIn;
import io.nzbee.view.customer.CustomerDTOOut;
import io.nzbee.view.customer.ICustomerViewService;
import io.nzbee.view.customer.address.CustomerAddressDTOIn;
import io.nzbee.view.customer.address.CustomerAddressDTOOut;
import io.nzbee.view.ports.ICustomerAddressPortService;


@RestController
@RequestMapping("/api")
public class CustomerController {
	
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ICustomerViewService customerViewService;
    
    @Autowired
    private ICustomerAddressPortService addressService;
 
    @Autowired
    private CustomerResourceAssembler customerResourceAssembler;
    
    @Autowired
    private CustomerAddressResourceAssembler customerAddressResourceAssembler;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public CustomerController() {
        super();
    }

    @GetMapping(value = "/username")
    public String currentUserName(Principal principal) {
    	LOGGER.debug("call " + getClass().getSimpleName() + ".currentUserName");
        return principal.getName();
    }
    
    @PostMapping("/Customer/Signup")
    public GenericResponse registerNewCustomer(@RequestBody final CustomerDTOIn customer, final HttpServletRequest request) {
      LOGGER.debug("Signing up a new customer with information: {}", customer);
        
        customerViewService.registerNewCustomer(customer, getClientIP(request), getAppUrl(request), request.getLocale());
        
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(customer, request.getLocale(), request.getRequestURI()));
        
        return new GenericResponse("success");
    }
    
    @GetMapping("/registrationConfirmation")
    public GenericResponse confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
        final String result = customerViewService.validateVerificationToken(token);
        if (result.equals("valid")) {
        	customerViewService.authWithoutPassword(token);
            return new GenericResponse("success");
        }
        return new GenericResponse("failure");
    }
    
    @GetMapping("/Customer")
	public ResponseEntity<CustomerResource> getCustomer(Principal customer) {   	
    	CustomerDTOOut c = customerViewService.findByUsername(customer.getName());
    	return ResponseEntity.ok(customerResourceAssembler.toModel(c));
	}
    
    @GetMapping("/Customer/Address/{addressTypeCode}")
	public ResponseEntity<CustomerAddressResource> getCustomerAddress(Principal customer, @PathVariable String addressTypeCode) {   	
    	CustomerAddressDTOOut a = addressService.findByUsernameAndType(customer.getName(), addressTypeCode); 
    	return ResponseEntity.ok(customerAddressResourceAssembler.toModel(a));
	}
    
    @PostMapping("/Customer/Address/Update")
    public GenericResponse updateCustomerAddres(@RequestBody final CustomerAddressDTOIn address, Principal customer, final HttpServletRequest request) {
        LOGGER.debug("Updating customer address with information: {}", address);
        
        addressService.save(address, customer.getName());
        
        return new GenericResponse("success");
    }
       
  
    private String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
    
    private String getAppUrl(HttpServletRequest request) {
        return "https://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    
}
