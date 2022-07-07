package io.nzbee.security;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;


@Configuration
public class SecurityBeanConfiguration {
 
	@Bean(name="GeoIPCountry")
    public DatabaseReader databaseReader() throws IOException, GeoIp2Exception {
		final InputStream resource = getClass().getClassLoader().getResourceAsStream("maxmind/GeoLite2-Country.mmdb");
		return new DatabaseReader.Builder(resource).build();
    }
	
}