package modelos;

import java.util.Date;

public class UsuarioDTO  {
	
	private long id;
	private String nombreUsuario;
	private String dni;
	private String apeynom;
	private String domicilio;
	private Date fechaNacimiento; 
	private String sexo;
	private String pass;
	private String mail;
	private boolean habilitado = true;

	public UsuarioDTO() {}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public boolean getHabilitado() {
		return habilitado;
	}
	
	public void setHabilitado(boolean h) {
		this.habilitado = h;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApeynom() {
		return apeynom;
	}

	public void setApeynom(String apeynom) {
		this.apeynom = apeynom;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

}
