package com.pepe.proyectospringtool.Controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/home") // localhost:7001/home
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class.getName());
	
	//@GetMapping // localhost:7001/home [GET]
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		logger.warning("Es posible que el recurso no se muestre correctamente.");
		return "home/index";
	}
}
