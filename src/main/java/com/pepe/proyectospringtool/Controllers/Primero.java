package com.pepe.proyectospringtool.Controllers;

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
	
	@RequestMapping("/")
	@ResponseBody
	public String saludo() {
		return "Buenas noches :D";
	}
	
	@RequestMapping("/cerrar")
	@ResponseBody
	public void cerrar() {
		SpringApplication.exit(contexto);
	}
}
