package com.pepe.proyectospringtool.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pepe.proyectospringtool.Interceptors.EstudianteInterceptor;

@Component
public class EstudianteInterceptorConfiguration implements WebMvcConfigurer{
	@Autowired
	private EstudianteInterceptor interceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registro) {
		registro.addInterceptor(interceptor);
	}
}
