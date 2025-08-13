package com.pepe.proyectospringtool.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class EstudianteInterceptor implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(EstudianteInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("Se ejecuta método Pre Handle; método interceptado: "+request.getMethod());
		if(request.getMethod().equals("PUT")) {
			logger.info(String.format("Usuario: %s, host: %s desde la direccion %s:%d hacia la URI %s; el end-point final es: %s",
					request.getRemoteUser(),
					request.getRemoteHost(),
					request.getRemoteAddr(),
					request.getRemotePort(),
					request.getRequestURI(),
					request.getRequestURI().split("/")[2]));
		}
		//request.setAttribute("metodoPeticion", "Registro");
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("Se ejecuta método Post Handle");
		//String metodo = (String) request.getAttribute("metodoPeticion");
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
		System.out.println("Se ejecuta Request y Response completo");
	}
}
