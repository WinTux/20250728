package com.pepe.proyectospringtool.Services;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	public boolean validarCredenciales(String username, String password) {
		return "pepe".equals(username) && "123456".equals(password);
	}
}
