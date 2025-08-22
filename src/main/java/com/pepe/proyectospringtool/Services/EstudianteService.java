package com.pepe.proyectospringtool.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepe.proyectospringtool.Modelos.Est;
import com.pepe.proyectospringtool.Repositories.EstudianteRepository;

@Service
public class EstudianteService {
	@Autowired
	private EstudianteRepository estudianteRepo;
	
	public List<Est> lista(){
		return estudianteRepo.findAll();
	}
	public List<Est> getEstudiantesDeCarrera(int idCarrera){
		return estudianteRepo.getByCarreraid(idCarrera);
	}
	public List<Est> getEstudiantesPorApellidoyEstado(String apellido, boolean estado){
		return estudianteRepo.findByApellidoAndEstado(apellido, estado);
	}
	public List<Est> hallarPorNombreApellido(String nombre, String apellido){
		return estudianteRepo.findByNombreAndApellido(nombre, apellido);
	}
	
	public Optional<Est> hallarEstudiante(int matricula) {
		return estudianteRepo.findById(matricula);
	}
	
	public void registrar(Est est) {
		estudianteRepo.save(est);
		System.out.println("> "+ est.getNombre()+" "+est.getApellido()+" fue registrado");
	}
	
	public void eliminar(Est est) {
		estudianteRepo.delete(est);
	}
}
