package io.nzbee.security.events;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import io.nzbee.security.user.IUserService;
import io.nzbee.security.user.User;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private IUserService service;

    @Autowired
    private MessageSource messages;

//    @Autowired
//    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    // API

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
    	LOGGER.debug("call onApplicationEvent with parameter {}", event);
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
    	LOGGER.debug("call confirmRegistration with parameter {}", event);
        User u = service.findUserByUsername(event.getCustomer().getUserName());
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForUser(u, token);

        final SimpleMailMessage email = constructEmailMessage(event, event.getCustomer().getUserName(), token);
        LOGGER.debug("sending email with message: {}", email);
        
      //  mailSender.send(email);
    }


    private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final String userName, final String token) {
        final String recipientAddress = userName;
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/api/registrationConfirmation?token=" + token;
        final String message = messages.getMessage("message.regSuccLink", null, "You registered successfully. To confirm your registration, please click on the below link.", event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }
    

}
