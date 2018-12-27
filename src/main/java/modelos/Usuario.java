package modelos;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Usuario extends Persona implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
//	@OneToMany(mappedBy="usuarioCreador",cascade = CascadeType.ALL)
	@OneToMany(mappedBy="usuarioCreador")
	private List<Ruta> rutasCreadas; //Rutas creadas por el usuario
	
	@ManyToMany
	private List<Ruta> rutasRealizadas; // Rutas marcadas por el usuario como que las hizo.
	
	public Usuario() {}
	

	public List<Ruta> getRutasCreadas(){
			return this.rutasCreadas;
	}
	
	public List<Ruta> getRutasRealizadas(){
			return this.rutasRealizadas;
	}
	
	public void subirRuta(Ruta ruta) {
			this.rutasCreadas.add(ruta);
	}
	
	public void modificarRuta(Ruta ruta) {
			if (!this.rutasCreadas.contains(ruta) ) {
				System.out.println("La ruta no existe");
			}else { 
				this.rutasCreadas.add(ruta); // Las validaciones son por html (Nombre, descripcion, privacidad, dificultad, fotos)	
			}
	}
	
	public void eliminarRuta(int i) {
			Ruta rutaEliminar = this.rutasCreadas.get(i);
			if (rutaEliminar.isModificada()) { // Si fue modifica, se marca como privada dejando de ser visible, pero no se elimina.
				rutaEliminar.setPrivacidad("Privada");
			}else {
				this.rutasCreadas.remove(i);
			}
	}
	
	public void marcarRuta(Ruta ruta) {
			this.rutasRealizadas.add(ruta);
	}
	
	public void valorarRutaPublica(int i, int valoracion) {
			//Valora ruta p�blica
	}
	
	public void agregarNotaRutaPropia(String nota, int i) {
			//Agrega nota en ruta propia
	}
	
	public void agregarNotaRutaPublica(String nota, int i) {
			//Agrega nota en ruta p�blica
	}
	

}
