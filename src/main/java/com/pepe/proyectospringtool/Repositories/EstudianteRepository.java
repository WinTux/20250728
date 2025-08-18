package com.pepe.proyectospringtool.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pepe.proyectospringtool.Modelos.Est;

@Repository
public interface EstudianteRepository extends JpaRepository<Est, Integer>{
	List<Est> getByCarreraid(int cid);
	List<Est> findByApellidoAndEstado(String ap, Boolean estado);
}
