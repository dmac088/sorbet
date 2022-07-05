package io.nzbee.integration.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.context.WebApplicationContext;
import com.zaxxer.hikari.HikariDataSource;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.security.DataSourceBeanSecurity;

@Configuration
public class ConfigControllerTest {

	public static String TEST_USERNAME = "nob@nob";
	public static String TEST_PASSWORD = "nob";
	
	@Bean
	public DataSourceBeanMochi dataSourceBeanMochi() {
		return new DataSourceBeanMochi();
	}
	
	@Bean
    @ConfigurationProperties("spring.datasource.mochi.application")
    public DataSourceProperties mochiDataSourceProperties() {
		return new DataSourceProperties();
    }
	
	@Bean
    @ConfigurationProperties("spring.datasource.mochi.owner")
    public DataSourceProperties mochiDataSourcePropertiesOwner() {
		return new DataSourceProperties();
    }
	
	@Bean(name = "mochiDataSourceOwner")
	public HikariDataSource mochiDataSourceOwner() {
		return dataSourceBeanMochi().dataSource(mochiDataSourcePropertiesOwner());
	}
	
	@Bean(name = "mochiDataSource")
	public HikariDataSource mochiDataSource() {
		return dataSourceBeanMochi().dataSource(mochiDataSourceProperties());
	}
	
	@Bean(name = "securityDataSource")
	public HikariDataSource securityDataSource() {
		return dataSourceBeanSecurity().dataSource(securityDataSourceProperties());
	}
	
	@Bean(name = "mochiEntityManagerFactory") 
    public LocalContainerEntityManagerFactoryBean mochiEntityManagerFactory() {
       return dataSourceBeanMochi().entityManagerFactory(mochiDataSource());
    }
	
	@Bean(name = "mochiTransactionManager")
    public PlatformTransactionManager mochiTransactionManager() {
		return dataSourceBeanMochi().TransactionManager(mochiEntityManagerFactory());
    }
	
	@Bean
	public DataSourceBeanSecurity dataSourceBeanSecurity() {
		return new DataSourceBeanSecurity();
	}
	
	@Bean
    @ConfigurationProperties("spring.datasource.security.application")
    public DataSourceProperties securityDataSourceProperties() {
        return new DataSourceProperties();
    }
	

	public static String obtainAccessToken(String username, String password, WebApplicationContext webApplicationContext) throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
		ResultActions result = mockMvc.perform(post("/oauth/token").header("Authorization",
				"Basic c3ByaW5nLXNlY3VyaXR5LW9hdXRoMi1yZWFkLXdyaXRlLWNsaWVudDpzcHJpbmctc2VjdXJpdHktb2F1dGgyLXJlYWQtd3JpdGUtY2xpZW50LXBhc3N3b3JkMTIzNA==")
				.content(EntityUtils
						.toString(new UrlEncodedFormEntity(Arrays.asList(new BasicNameValuePair("username", username),
								new BasicNameValuePair("password", password),
								new BasicNameValuePair("client_id", "spring-security-oauth2-read-write-client"),
								new BasicNameValuePair("grant_type", "password")))))
				.with(csrf()).contentType(MediaType.APPLICATION_FORM_URLENCODED).characterEncoding("utf-8")
				.accept(MediaType.ALL)).andExpect(status().isOk());

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();
	}
	
}
