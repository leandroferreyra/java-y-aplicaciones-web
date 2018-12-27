package modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RutaDTO {
	

	private long id;
//	private Usuario usuarioCreador; 
//	private List<Usuario> usuarioRecorrio;
	private String nombreRuta;
	private String descripcion;
	private String privacidad;
	private String recorrido;
	private String formato;
	private String distancia;
	private DificultadEnum dificultad;
	private Actividad actividad;
	private String tiempo;
	private Date fechaRealizacion;
	private String fotos;
	private int cantidadUsuarios;
	private double puntajePromedio;
	private List<Nota> notas;
	private boolean modificada;
	private List<ValoracionDTO> valoraciones;
	private List<UsuarioDTO> usuarios;
	
	public RutaDTO() {}

	
	//Getters y setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public Usuario getUsuarioCreador() {
//		return usuarioCreador;
//	}
//
//	public void setUsuarioCreador(Usuario usuarioCreador) {
//		this.usuarioCreador = usuarioCreador;
//	}

	public String getNombreRuta() {
		return nombreRuta;
	}

	public void setNombreRuta(String nombreRuta) {
		this.nombreRuta = nombreRuta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrivacidad() {
		return privacidad;
	}

	public void setPrivacidad(String privacidad) {
		this.privacidad = privacidad;
	}

	public String getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(String recorrido) {
		this.recorrido = recorrido;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public DificultadEnum getDificultad() {
		return dificultad;
	}

	public void setDificultad(DificultadEnum dificultad) {
		this.dificultad = dificultad;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}

	public int getCantidadUsuarios() {
		return cantidadUsuarios;
	}

	public void setCantidadUsuarios(int cantidadUsuarios) {
		this.cantidadUsuarios = cantidadUsuarios;
	}

	public double getPuntajePromedio() {
		return puntajePromedio;
	}

	public void setPuntajePromedio(double puntajePromedio) {
		this.puntajePromedio = puntajePromedio;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(ArrayList<Nota> notas) {
		this.notas = notas;
	}

	public boolean isModificada() {
		return modificada;
	}

	public void setModificada(boolean modificada) {
		this.modificada = modificada;
	}

	public List<ValoracionDTO> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<ValoracionDTO> valoraciones) {
		this.valoraciones = valoraciones;
	}


	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
	
	
}
