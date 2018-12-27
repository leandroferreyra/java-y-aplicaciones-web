package modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Nota implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_nota")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_nota;
	
	@Column
	private TipoNota tipoNota; //Denuncia, alerta u opini�n.
	
	@Column(name="id_usuario")
	private Usuario usuario; //Usuario que realiz� la nota
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ruta_id")
	private Ruta ruta;  	 //Ruta a la cual se le hizo la nota
	
	@Column(name="descripcion")
	private String descripcion;
	
	
	//Getters y setters
	public long getId() {
		return id_nota;
	}
	public void setId(long id) {
		this.id_nota = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	
	public void setDescripcion(String d) {
		this.descripcion = d;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public TipoNota getTipoNota() {
		return this.tipoNota;
	}
	
	public void setTipoNota(TipoNota t) {
		this.tipoNota = t;
	}
	
	

}
