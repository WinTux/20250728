package com.pepe.proyectospringtool.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.pepe.proyectospringtool.Filter.ControlConexionFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class AppConfiguration {
	@Autowired
	private ControlConexionFilter filtro;
	@Bean
	FilterRegistrationBean<Filter> miFiltroRegBean(){
		FilterRegistrationBean<Filter> fil 
		= new FilterRegistrationBean<Filter>();
		fil.setFilter(filtro);
		fil.addUrlPatterns("/estudiante/*");
		fil.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return fil;
	}
}
