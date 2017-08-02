package com;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by aphisit on 01-Aug-17.
 */
@ComponentScan(basePackages = "com")
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true).ignoreAcceptHeader(true).useJaf(false).mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return  registrationBean;
    }
}
