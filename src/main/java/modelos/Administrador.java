package modelos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class Administrador extends Persona implements Serializable{

	private static final long serialVersionUID = 1L;

	public Administrador() {}

	
	
//	Metodos del administrador
	public void habilitarUsuario(int pos) {
		//Habilita usuario
	}
	
	public void deshabilitarUsuario(int pos) {
		//Deshabilita usuario
	}
	
	public ArrayList<Usuario> listarUsuarios(){
		//Lista usuarios
		return null;
	}
	
	public void agregarActividad(String nombre) {
			Actividad nuevaActividad = new Actividad();
			nuevaActividad.setNombreActividad(nombre);
//			actividades.add(nuevaActividad);
	}
	
	public void modificarNombreActividad(int actividad, String nombre) {
			//Modifica el nombre de una actividad
	}
	
	public void eliminarActividad(int actividad) {
			//Elimina una actividad
	}
	
	
}
