package com.pepe.proyectospringtool.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Primero {
	
	@Autowired
	private ApplicationContext contexto;
	
	private static final Logger logger = LoggerFactory.getLogger(Primero.class);
	
	@RequestMapping("/")
	@ResponseBody
	public String saludo() {
		logger.info("Se alcanzó al método saludo() retornando un saludo.");
		return "Buenas noches :D";
	}
	
	@RequestMapping("/cerrar")
	@ResponseBody
	public void cerrar() {
		logger.info("Se alcanzó el método cerrar() por lo que el cliente no recibe respuesta alguna.");
		SpringApplication.exit(contexto);
	}
}
