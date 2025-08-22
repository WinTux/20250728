package com.pepe.proyectospringtool.Controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pepe.proyectospringtool.Exceptions.EstudianteNoEncontradoException;
import com.pepe.proyectospringtool.Modelos.Est;
import com.pepe.proyectospringtool.Services.EstudianteService;

@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstController {
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	private EstudianteService estServ;
	
	@GetMapping // https://localhost:7001/api/v1/estudiantes [GET]
	public ResponseEntity<Object> getEsetudiantes(){
		List<Est> estudiantes = estServ.lista();
		return new ResponseEntity<>(estudiantes, HttpStatus.OK);
	}
	@GetMapping(value="/{ap}") // https://localhost:7001/api/v1/estudiantes/{ap} [GET]
	public ResponseEntity<Object> getEestudianteActivoPorApellido(@PathVariable("ap") String apellido){
		List<Est> estudiantes = estServ.getEstudiantesPorApellidoyEstado(apellido, true);
		return new ResponseEntity<>(estudiantes, HttpStatus.OK);
	}
	@GetMapping(value="/{nom}/{ap}") // https://localhost:7001/api/v1/estudiantes/{nom}/{ap} [GET]
	public ResponseEntity<Object> getEstudiatesNombreApellido(@PathVariable("nom") String nombre, @PathVariable("ap") String apellido){
		List<Est> estudiantes = estServ.hallarPorNombreApellido(nombre, apellido);
		return new ResponseEntity<>(estudiantes, HttpStatus.OK);
	}
	
	@PostMapping // https://localhost:7001/api/v1/estudiantes [POST]
	public ResponseEntity<Object> nuevoEstudiante(@RequestBody Est estudiante){
		estServ.registrar(estudiante);
		URI ubicacionRecurso = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(estudiante.getMatricula()).toUri();
		return ResponseEntity.created(ubicacionRecurso).build();
	}
	@PutMapping("/{matri}") // https://localhost:7001/api/v1/estudiantes/{matri} [PUT]
	public ResponseEntity<Object> modificarEstudiante(@PathVariable("matri") int matricula, @RequestBody Est estudiante){
		Optional<Est> estudianteOpcional = estServ.hallarEstudiante(matricula);
		if(!estudianteOpcional.isPresent())
			throw new EstudianteNoEncontradoException();
		Est estudianteOriginal = estudianteOpcional.get();
		estudianteOriginal.setNombre(estudiante.getNombre());
		estudianteOriginal.setApellido(estudiante.getApellido());
		estudianteOriginal.setFechanacimiento(estudiante.getFechanacimiento());
		estudianteOriginal.setCarreraid(estudiante.getCarreraid());
		estudianteOriginal.setPassword(estudiante.getPassword());
		estudianteOriginal.setEmail(estudiante.getEmail());
		estudianteOriginal.setEstado(estudiante.getEstado());
		estServ.registrar(estudianteOriginal);
		return new ResponseEntity<>("Se modificó el recurso",HttpStatus.OK);
	}
	@DeleteMapping("/{matri}") // https://localhost:7001/api/v1/estudiantes/{matri} [DELETE]
	public ResponseEntity<Object> eliminarEstudiante(@PathVariable("matri") int matricula){
		Optional<Est> estudiante = estServ.hallarEstudiante(matricula);
		if(!estudiante.isPresent())
			throw new EstudianteNoEncontradoException();
		Est estDDBB = estudiante.get();
		estServ.eliminar(estDDBB);
		return new ResponseEntity<>("Se eliminó al estudiante "+matricula, HttpStatus.OK);
	}
}
