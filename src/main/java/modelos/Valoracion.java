package modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
public class Valoracion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_valoracion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@Column
	private int valoracion;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ruta_id")
	private Ruta ruta;
	
	@Column(name="usuario_id")
	private Usuario usuario;
	
	public Valoracion() {}
	
	//Getters y setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
