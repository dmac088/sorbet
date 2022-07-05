package io.nzbee.integration.entity;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.util.FileStorageProperties;
import io.nzbee.util.FileStorageServiceUpload;

@Configuration
//we need to import other configuration classes to create the spring context 
//we cannot arbitrarily create beans in this config class since the configuration classes are 
//not simply services or components but actual configurations, also we don't want to component scan
//io.nzbee.* since this will load things (services, components, configs) we don't want into the spring context
@Import({DataSourceBeanMochi.class,
		 DataSourceBeanSecurity.class,
		 FileStorageProperties.class,
		 FileStorageServiceUpload.class,
		 //Globals.class
		 })
public class ConfigEntityTests {
	
	
}
