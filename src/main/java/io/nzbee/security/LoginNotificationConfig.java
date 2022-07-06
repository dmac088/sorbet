package io.nzbee.security;

import com.google.common.io.Resources;
import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import ua_parser.Parser;

import java.io.File;
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
        final InputStream resource = Resources.getResource("classpath:maxmind/GeoLite2-City.mmdb").openStream();
        return new DatabaseReader.Builder(resource)
                .build();
    }
}
