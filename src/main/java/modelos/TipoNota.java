package modelos;

public enum TipoNota {
	DENUNCIA("Denuncia"), ALERTA("Alerta"), OPINION("Opinion");
	
	private String nombre;
	
	private TipoNota(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nom) {
		this.nombre = nom;
	}
}
