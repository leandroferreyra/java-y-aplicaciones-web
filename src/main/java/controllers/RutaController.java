package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DAO.NotaDAOHibernate;
import DAO.RutaDAOHibernate;
import DAO.UsuarioDAOHibernate;
import DAO.ValoracionDAOHibernate;
import modelos.Nota;
import modelos.Ruta;
import modelos.RutaDTO;
import modelos.Usuario;
import modelos.UsuarioDTO;
import modelos.Valoracion;
import modelos.ValoracionDTO;


@Path("/rutas")
public class RutaController {

	RutaDAOHibernate rdao = new RutaDAOHibernate();
	ValoracionDAOHibernate vdao = new ValoracionDAOHibernate();
	UsuarioDAOHibernate udao = new UsuarioDAOHibernate();
	NotaDAOHibernate ndao = new NotaDAOHibernate();
	
	//Listo las rutas del usuario
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ruta> listar(@QueryParam("id") long id) {
		List<Ruta> rutasReturn = new ArrayList<Ruta>();
		List<Ruta> rutas= new ArrayList<Ruta>();
		rutas = rdao.listarRutasPorUsuario(id);
		if (rutas == null) {
			return null;
		} else {
			for(int i = 0; i < rutas.size(); i++) {
				Ruta r = new Ruta();
				r.setId(rutas.get(i).getId());
				r.setNombreRuta(rutas.get(i).getNombreRuta());
				rutasReturn.add(r);
			}
			return rutasReturn;
		}
	}
	
	//Listo las rutas ajenas del usuario
		@GET
		@Path("/publicas")
		@Produces(MediaType.APPLICATION_JSON)
		public List<RutaDTO> listarRutasPublicas(@QueryParam("id") long id) {
			List<RutaDTO> rutasReturn = new ArrayList<RutaDTO>();
			List<Ruta> rutas= new ArrayList<Ruta>();
			rutas = rdao.listarRutasPublicas(id);
			if (rutas == null) {
				return null;
			} else {
				for(int i = 0; i < rutas.size(); i++) {
					if (rutas.get(i).getPrivacidad().equals("P�blica")) {
						RutaDTO r = new RutaDTO();
						List<ValoracionDTO> valoracionesDTO = new ArrayList<ValoracionDTO>();
						List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
						r.setId(rutas.get(i).getId());
						r.setNombreRuta(rutas.get(i).getNombreRuta());
						if (rutas.get(i).getValoraciones().size() > 0) {
							for (int x = 0; x < rutas.get(i).getValoraciones().size(); x++) {
								ValoracionDTO valoracionDTO = new ValoracionDTO();
								valoracionDTO.setValoracion(rutas.get(i).getValoraciones().get(x).getValoracion());
								valoracionDTO.setIdUsuario((int) rutas.get(i).getValoraciones().get(x).getUsuario().getId());
								valoracionesDTO.add(valoracionDTO);
							}
						}
						if (rutas.get(i).getUsuariosRecorrio().size() > 0) {
							for (int x = 0; x < rutas.get(i).getUsuariosRecorrio().size(); x++) {
								UsuarioDTO usuarioDTO = new UsuarioDTO();
								usuarioDTO.setId(rutas.get(i).getUsuariosRecorrio().get(x).getId());
								usuarioDTO.setNombreUsuario(rutas.get(i).getUsuariosRecorrio().get(x).getNombreUsuario());
								usuarioDTO.setMail(rutas.get(i).getUsuariosRecorrio().get(x).getMail());
								usuariosDTO.add(usuarioDTO);
							}
						}
						r.setUsuarios(usuariosDTO);
						r.setValoraciones(valoracionesDTO);
						r.setActividad(rutas.get(i).getActividad());
						r.setFormato(rutas.get(i).getFormato());
						r.setDificultad(rutas.get(i).getDificultad());
						r.setDistancia(rutas.get(i).getDistancia());
						r.setPuntajePromedio(rutas.get(i).getPuntajePromedio());
						r.setCantidadUsuarios(rutas.get(i).getCantidadUsuarios());
						rutasReturn.add(r);
					}
				}
				return rutasReturn;
			}
		}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ruta obtenerRuta(@PathParam("id") long id) throws FileNotFoundException {
		Ruta ruta= rdao.encontrarPorId((int) id);
		Ruta rutaReturn = new Ruta();
		if (ruta == null) {
			System.out.println("Ruta con id " + id + " no encontrado");
			return null;
		}else {
			rutaReturn.setId(ruta.getId());
			rutaReturn.setNombreRuta(ruta.getNombreRuta());
			rutaReturn.setDescripcion(ruta.getDescripcion());
			rutaReturn.setFechaRealizacion(ruta.getFechaRealizacion());
			rutaReturn.setRecorrido(ruta.getRecorrido());
			rutaReturn.setTiempo(ruta.getTiempo());
			rutaReturn.setPrivacidad(ruta.getPrivacidad());
			rutaReturn.setDificultad(ruta.getDificultad());
			rutaReturn.setFormato(ruta.getFormato());
			rutaReturn.setDistancia(ruta.getDistancia());
			rutaReturn.setModificada(ruta.isModificada());
			rutaReturn.setActividad(ruta.getActividad());
			rutaReturn.setCantidadUsuarios(ruta.getUsuariosRecorrio().size());
			int valoracion = 0;
			for(int i = 0; i < ruta.getValoraciones().size(); i++ ) {
				valoracion =+ ruta.getValoraciones().get(i).getValoracion();
			}	
			if (valoracion == 0) {
				rutaReturn.setPuntajePromedio(0);
			} else {
				rutaReturn.setPuntajePromedio(valoracion/ruta.getValoraciones().size());
			}
			rutaReturn.setFotos(ruta.getFotos());
			return rutaReturn;
		}
	}
	
	//Creo una ruta
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Ruta ruta) throws IOException{
		if(rdao.existeRuta(ruta.getNombreRuta())) {
			 return Response.status(Response.Status.CONFLICT).build();
		}else {
			rdao.persistir(ruta);
			return Response.status(Response.Status.CREATED).build();
		}
	}
	
	@PUT
	@Path("/valorarRuta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response valorarRuta(Valoracion valoracion){
		 System.out.println("");
		 Ruta r = rdao.encontrarPorId(valoracion.getRuta().getId());
		 if (r != null) {
			 List<Valoracion> valoraciones = r.getValoraciones();
			 if (valoraciones.size() > 0) {
				 Iterator<Valoracion> valoracionesIterator = valoraciones.iterator();
				 while(valoracionesIterator.hasNext()) {
					 Valoracion v = valoracionesIterator.next();
					 if (v.getRuta().getId() == r.getId() && v.getUsuario().getId() == valoracion.getUsuario().getId()) {
						 return Response.status(Response.Status.CONFLICT).entity("[]").build(); // Si ese usuario ya punteo a esa ruta, no puede hacerlo de nuevo.
					 }
				 }
			 }
			 valoraciones.add(valoracion);
			 r.setValoraciones(valoraciones);
			 r.setModificada(true);
			 vdao.persistir(valoracion);
			 rdao.actualizar(r);
			 return Response.ok().entity(r).build();
		 } else {
			 return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		 }
	}
	
//	Usuario que recorre ruta
	@POST
	@Path("/recorrioRuta/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response recorrerRuta(@PathParam("id") int id, @QueryParam("checked") boolean checked, Usuario usuario){
		Usuario usu= udao.encontrarPorId(usuario.getId());
		Ruta ruta = rdao.encontrarPorId(id);

		if (checked) {
			usu.getRutasRealizadas().add(ruta);
			ruta.getUsuariosRecorrio().add(usu);
			ruta.setCantidadUsuarios(ruta.getCantidadUsuarios()+1); 
		} else {
			usu.getRutasRealizadas().remove(ruta);
			ruta.getUsuariosRecorrio().remove(usu);
		}
		udao.actualizar(usu);
		ruta.setModificada(true);
		rdao.actualizar(ruta);
		return Response.status(Response.Status.CREATED).build();
//		}
	 }
	
	@PUT
	@Path("/agregarNota/{idUsuario}/{idRuta}") // id del usuario que escribio la nota
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response agregarNota(@PathParam("idUsuario") int idUsuario,@PathParam("idRuta") int idRuta, Nota nota) {
		Ruta r = rdao.encontrarPorId(idRuta);
		Usuario u = udao.encontrarPorId(idUsuario);
		if (r == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			Nota n= new Nota();
			n.setUsuario(u);
			n.setRuta(r);
			n.setDescripcion(nota.getDescripcion());
			n.setTipoNota(nota.getTipoNota());
			ndao.persistir(n);
			r.setModificada(true); // Ver esto
			rdao.actualizar(r);
			return Response.status(Response.Status.OK).build();
		}
	}
	
	//Elimino una actividad
	@DELETE
	@Path("{idRuta}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("idRuta") Integer idRuta){
		Ruta ruta= rdao.encontrarPorId(idRuta);
		if (ruta != null){
//			if (ruta.getUsuariosRecorrio().size() > 0 || ruta.getValoraciones().size() > 0 || ruta.getNotas().size() > 0) {
			if (ruta.isModificada()) {
				return Response.status(Response.Status.CONFLICT).build();
			} else {
				rdao.borrar(ruta);
				return Response.noContent().build();
			}
		} else {
			String mensaje = "No existe ruta con ese id";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
	
//	Modifico una ruta dada mediante el id
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editarRuta(@PathParam("id") int id, Ruta ruta){
			 Ruta r = rdao.encontrarPorId(id);
			 if (r != null) {
				 boolean existe = rdao.existeRuta(ruta.getNombreRuta()) && ruta.getId()!=r.getId();
				 if (existe) {
					 return Response.status(Response.Status.CONFLICT).build();
				 }else {
					 r.setNombreRuta(ruta.getNombreRuta());
					 r.setDescripcion(ruta.getDescripcion());
					 r.setPrivacidad(ruta.getPrivacidad());
					 r.setDificultad(ruta.getDificultad());
					 r.setFotos(ruta.getFotos());
					 r.setRecorrido(ruta.getRecorrido());
					 r.setFormato(ruta.getFormato());
					 r.setDistancia(ruta.getDistancia());
					 r.setActividad(ruta.getActividad());
					 r.setTiempo(ruta.getTiempo());
					 r.setFechaRealizacion(ruta.getFechaRealizacion());
					 rdao.actualizar(r);
					 return Response.ok().entity(ruta).build();
				 }
			 } else {
				 return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
			 }
		 
	}
	
}
