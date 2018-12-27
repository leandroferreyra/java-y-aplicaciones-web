package modelos;

import javax.persistence.Entity;

public enum DificultadEnum {
	FACIL("Facil"), MODERADO("Moderado"), DIFICIL("Dificil"), MUYDIFICIL("Muy dificil"), EXPERTO("Experto");
	
	private String nombre; 
	
	private DificultadEnum(String nom) {
		this.nombre = nom;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
