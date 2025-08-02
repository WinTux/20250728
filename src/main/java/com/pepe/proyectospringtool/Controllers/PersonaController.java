package com.pepe.proyectospringtool.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pepe.proyectospringtool.Modelos.Persona;

@Controller
@RequestMapping("/persona") 
public class PersonaController {
	@PostMapping // http://localhost:7001/persona [POST]
	@ResponseBody
	public String prueba(@RequestBody Persona p) {
		System.out.printf("La persona %s %s tiene %d años.\n", p.getNombre(),p.getApellido(),p.getEdad());
		return "Listo";
	}
}
