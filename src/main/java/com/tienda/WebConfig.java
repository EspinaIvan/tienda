package com.tienda;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


public class WebConfig implements WebMvcConfigurer {

	 @Bean
	    public LocaleResolver localeResolver() {
	        SessionLocaleResolver resolver = new SessionLocaleResolver();
	        resolver.setDefaultLocale(new Locale("es")); // Establece el idioma predeterminado
	        return resolver;
	    }

	    @Bean
	    public LocaleChangeInterceptor localeChangeInterceptor() {
	        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	        interceptor.setParamName("lang"); // Este es el nombre del parámetro en la URL para cambiar el idioma
	        return interceptor;
	    }

	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(localeChangeInterceptor());
	    }
}
