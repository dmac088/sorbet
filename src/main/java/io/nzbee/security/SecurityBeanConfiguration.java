package io.nzbee.security;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.io.Resources;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;


@Configuration
public class SecurityBeanConfiguration {
 
	@Bean(name="GeoIPCountry")
    public DatabaseReader databaseReader() throws IOException, GeoIp2Exception {
		final InputStream resource = Resources.getResource("classpath:maxmind/GeoLite2-Country.mmdb").openStream();
		return new DatabaseReader.Builder(resource).build();
    }
	
}