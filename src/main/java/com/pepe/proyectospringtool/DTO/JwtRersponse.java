package com.pepe.proyectospringtool.DTO;

public class JwtRersponse {
	private String token;

	public JwtRersponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
