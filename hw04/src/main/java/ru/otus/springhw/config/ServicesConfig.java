package ru.otus.springhw.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.springhw.service.IOService;
import ru.otus.springhw.service.IOServiceStreams;

@Configuration
@EnableConfigurationProperties(AppProps.class)
public class ServicesConfig {
    @Bean
    public IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }

}
