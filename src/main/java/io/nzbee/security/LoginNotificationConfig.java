package io.nzbee.security;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua_parser.Parser;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class LoginNotificationConfig {

    @Bean
    public Parser uaParser() throws IOException {
        return new Parser();
    }

    @Bean(name="GeoIPCity")
    public DatabaseReader databaseReader() throws IOException {
        final InputStream resource = getClass().getClassLoader().getResourceAsStream("maxmind/GeoLite2-City.mmdb");
        return new DatabaseReader.Builder(resource)
                .build();
    }
}
