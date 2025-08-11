package com.pepe.proyectospringtool.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pepe.proyectospringtool.Exceptions.EstudianteNoEncontradoException;

@ControllerAdvice
public class EstudianteExceptionController {
	@ExceptionHandler(value=EstudianteNoEncontradoException.class)
	public ResponseEntity<Object> unaExcepcion(EstudianteNoEncontradoException ex){
		return new ResponseEntity<>("No se encontro al estudiante (Excepcion EstudianteNoEncontrado)", HttpStatus.NOT_FOUND);
	}
}
