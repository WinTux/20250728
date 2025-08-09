package com.pepe.proyectospringtool.Controllers;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.pepe.proyectospringtool.Modelos.Estudiante;

@Controller
public class EstudianteController {
	@Autowired
	ObjectMapper objectMapper;
	
	private static Map<String,Estudiante> estudiantes =
			new HashMap<>();
	static {
		Estudiante e1 = new Estudiante(1,"Pepe","Perales");
		Estudiante e2 = new Estudiante(2,"Ana","Sosa");
		Estudiante e3 = new Estudiante(3,"Sofía","Rocha");
		estudiantes.put("1", e1);
		estudiantes.put("2", e2);
		estudiantes.put("3", e3);
	}
	@GetMapping("/estudiante") // http://localhost:7001/estudiante [GET]
	public ResponseEntity<Object> getEstudiantes(){
		return new ResponseEntity<>(estudiantes.values(), HttpStatus.OK);
	}
	@GetMapping("/estudiante/{id}") // http://localhost:7001/estudiante [GET]
	public ResponseEntity<Object> getEstudianteById(@PathVariable("id") String id){
		return ResponseEntity.ok(estudiantes.get(id));
	}
	
	@PostMapping("/estudiante") // http://localhost:7001/estudiante [POST]
	public ResponseEntity<Object> nuevoEstudiante(@RequestBody Estudiante estudiante){
		estudiantes.put(estudiante.getId()+"", estudiante);
		URI ubicacionRecurso = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estudiante.getId()).toUri();
		return ResponseEntity.created(ubicacionRecurso).build(); 
	}
	
	@PutMapping("/estudiante/{id}")
	public ResponseEntity<Object> modificarEstudiante(@PathVariable("id") String id, @RequestBody Estudiante estudiante){
		estudiantes.remove(id);
		estudiante.setId(Integer.parseInt(id));
		estudiantes.put(id, estudiante);
		return new ResponseEntity<>("Se actualizaron los datos del estudiante " +id, HttpStatus.OK);
	}
	
	@PatchMapping("/estudiante/{id}") // http://localhost:7001/estudiante/12 [PATCH]
	public ResponseEntity<String> modificarEstudianteConPatch(@PathVariable("id") String id, @RequestBody Map<String,Object> atributosModificados){
		Estudiante estOriginal = estudiantes.get(id);
		atributosModificados.forEach((atributo,valorNuevo)->{
			Field campo = ReflectionUtils.findField(Estudiante.class, atributo);
			if(campo != null) {
				campo.setAccessible(true);
				ReflectionUtils.setField(campo, estOriginal, valorNuevo);
			}
		});
		estudiantes.remove(id);
		estudiantes.put(id, estOriginal);
		return new ResponseEntity<>("Se modificó el estudiante (PATCH)", HttpStatus.OK);
	}
	@PatchMapping(path="/estudiante/patch/{id}", consumes="application/json-patch+json")
	public ResponseEntity<String> modificarEstudianteConJsonPatch(@PathVariable("id") String id, @RequestBody JsonPatch atributosModificados){
		try {
			Estudiante estOriginal = estudiantes.get(id);
			JsonNode patcheado = atributosModificados
			.apply(objectMapper.convertValue(estOriginal, JsonNode.class));
			Estudiante estActualizado = objectMapper.treeToValue(patcheado, Estudiante.class);
			estudiantes.remove(id);
			estudiantes.put(id, estActualizado);
			return new ResponseEntity<>("Se modificó el estudiante (JSON PATCH)", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error fatal al modificar al estudiante "+id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/estudiante/status")
	public ResponseEntity<String> headersPersonalizados(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Modo-Empresa", "CRISIS");
		headers.add("Permiso-cifrado", "true");
		headers.add("Cantidad-almacenes", "245");
		return new ResponseEntity<>("Respuesta con cabeceras propias",headers, HttpStatus.OK);
	}
}
