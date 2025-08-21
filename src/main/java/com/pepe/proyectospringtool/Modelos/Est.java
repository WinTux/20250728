package com.pepe.proyectospringtool.Modelos;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="estudiante", schema="pregrado")
public class Est {
	@Id
	private int matricula;
	private String nombre, apellido;
	private Date fechanacimiento;
	private int carreraid;
	@Column(name="pass")
	private String password;
	private String email;
	private boolean estado;
	public Est() {
		super();
		matricula = -1;
		nombre = apellido = "NN";
		fechanacimiento = new Date(1970,1,1);
		carreraid = -1;
		password = email = "";
		estado = false;
	}
	public Est(int matricula, String nombre, String apellido, Date fechanacimiento, int carreraid, String password,
			String email, Boolean estado) {
		super();
		this.matricula = matricula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechanacimiento = fechanacimiento;
		this.carreraid = carreraid;
		this.password = password;
		this.email = email;
		this.estado = estado;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	public int getCarreraid() {
		return carreraid;
	}
	public void setCarreraid(int carreraid) {
		this.carreraid = carreraid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
