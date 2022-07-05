package io.nzbee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.ContextRefreshedEvent;
import io.nzbee.cache.CacheConfiguration;
import io.nzbee.util.FileStorageProperties;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({ FileStorageProperties.class, Globals.class, CacheConfiguration.class})
public class App {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	// @Value("${spring.profiles.active}")
	// protected String springProfilesActive;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOGGER.info("=======================================");
		// LOG.info("App running with active profiles: {}", springProfilesActive);
		LOGGER.info("=======================================");
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}