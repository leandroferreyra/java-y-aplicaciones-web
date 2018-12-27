package modelos;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
public class Ruta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ruta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//	@Column
	@ManyToOne(optional=false)
	@JoinColumn(name="usuarioCreador") // Nombre que le pone en la bbdd
	private Usuario usuarioCreador; 
	
	@ManyToMany(mappedBy="rutasRealizadas")
	private List<Usuario> usuarioRecorrio;
	
	@Column
	private String nombreRuta;
	
	@Column
	private String descripcion;
	
	@Column
	private String privacidad;
	
	@Column
	private String recorrido;
	
	@Column
	private String formato;
	
	@Column
	private String distancia;
	
	@Column
	private DificultadEnum dificultad;
	
	@Column
	private Actividad actividad;
	
	@Column
	private String tiempo;
	
	@Column
	private Date fechaRealizacion;
	
	@Column
//	private List<Foto> fotos;
	private String fotos;
	
	@Column
	private int cantidadUsuarios;
	
	@Column
	private double puntajePromedio;
	
	@OneToMany(mappedBy="ruta")
	private List<Nota> notas;
	
	@Column
	private boolean modificada;

	@OneToMany(mappedBy="ruta")
	private List<Valoracion> valoraciones; 
	
	public Ruta() {}

	
	//Getters y setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<Usuario> getUsuariosRecorrio() {
		return this.usuarioRecorrio;
	}

	public Usuario getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(Usuario usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

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

//	public List<Foto> getFotos() {
	public String getFotos() {
		return fotos;
	}

//	public void setFotos(List<Foto> fotos) {
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

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public boolean isModificada() {
		return modificada;
	}

	public void setModificada(boolean modificada) {
		this.modificada = modificada;
	}

	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

	
}
