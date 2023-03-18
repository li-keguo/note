package com.example.i18n.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * I18nConfuration.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Configuration
public class I18nConfiguration {
    
    @Bean
    public LocaleResolver localeResolver() {
        final AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setSupportedLocales(Stream.of(Locale.US, Locale.SIMPLIFIED_CHINESE).collect(Collectors.toList()));
        return localeResolver;
    }
    
}
