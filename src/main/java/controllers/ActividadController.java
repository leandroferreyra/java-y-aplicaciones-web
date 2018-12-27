package controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DAO.ActividadDAOHibernate;
import modelos.Actividad;

@Path("/actividades")
public class ActividadController {

		ActividadDAOHibernate adao = new ActividadDAOHibernate();
		
		//Listo todas las actividades
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Actividad> listar(){
			List<Actividad> actividades = adao.listar();
			if (actividades.isEmpty()) {
				return null;
			}else {
				return actividades;
			}
		}
		
		//Obtengo una actividad 
		@GET
		@Path("{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Actividad obtenerActividad(@PathParam("id") long id) {
			Actividad act= adao.encontrarPorId((int) id); 
			if (act == null) {
				System.out.println("Actividad con id " + id + " no encontrada");
				return null;
			}else {
				System.out.println("Retorno actividad: " + act.getNombreActividad());
				return act;
			}
		}
		
		//Creo una actividad
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response crear(Actividad actividad){
			if(adao.existeActividad(actividad.getNombreActividad())) {
				 return Response.status(Response.Status.CONFLICT).build();
			}else {
				adao.persistir(actividad);
				return Response.status(Response.Status.CREATED).build();
			}
		}
		
		
//		Actualizado una actividad dada mediante el id
		@PUT
		@Path("{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response editarActividad(@PathParam("id") int id, Actividad actividad){
				 System.out.println(actividad.getId());
				 Actividad a = adao.encontrarPorId(id);
				 if (a != null) {
					 boolean existe = adao.existeActividad(actividad.getNombreActividad());
					 if (existe) {
						 return Response.status(Response.Status.CONFLICT).build();
					 }else {
						 a.setNombreActividad(actividad.getNombreActividad());
						 adao.actualizar(a);
						 return Response.ok().entity(actividad).build();
					 }
				 } else {
					 return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
				 }
			 
		}
		
		//Elimino una actividad
		@DELETE
		@Path("{id}")
		@Produces(MediaType.TEXT_PLAIN)
		public Response borrar(@PathParam("id") Integer id){
			Actividad aux = adao.encontrarPorId(id);
			if (aux != null){
				adao.borrar(aux);
				return Response.noContent().build();
			} else {
				String mensaje = "No existe actividad con ese id";
				return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
			}
		}
		

		
}
