package io.nzbee.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * Created by Hardik on 03/11/17.
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    private static final long serialVersionUID = 1L;

	public CustomOauthException(String msg) {
        super(msg);
    }
}