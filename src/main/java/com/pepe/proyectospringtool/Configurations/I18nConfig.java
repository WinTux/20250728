package com.pepe.proyectospringtool.Configurations;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class I18nConfig {
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
		resolver.setDefaultLocale(Locale.US);
		return resolver;
	}
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource mensajeSource
		= new ReloadableResourceBundleMessageSource();
		mensajeSource.setBasename("classpath:mensajes");
		mensajeSource.setDefaultEncoding("UTF-8");
		return mensajeSource;
	}
}
