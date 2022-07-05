package io.nzbee.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = "/api/**";
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http	
        		.anonymous().and().authorizeRequests()
        		.antMatchers(HttpMethod.GET,"/api/username").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/Discovery/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/hkpost").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/Customer/Signup").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/Product/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/ProductCategory/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/BrandCategory/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/LayoutCategory/**").permitAll() 
        		.antMatchers(HttpMethod.POST,"/api/Product/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/ProductAttribute/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/Brand/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/registrationConfirmation/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/Brand/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/BrandFacet/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/Category/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/Category/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/CategoryFacet/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/Tag/**").permitAll()	
        		.antMatchers(HttpMethod.POST,"/api/TagFacet/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/NavFacet/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/CategoryAttribute/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/images/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/CreateSearchIndex").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/Search/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/Search/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/RefreshShippingProducts/**").permitAll()
        		.and().requestMatchers()
                .antMatchers(SECURED_PATTERN).and().authorizeRequests()
                .antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
                .anyRequest().access(SECURED_READ_SCOPE);
        
    }
}