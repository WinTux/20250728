package com.pepe.proyectospringtool.Services;

import java.util.List;

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
}
