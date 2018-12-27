package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import DAO.UsuarioDAOHibernate;
import modelos.Usuario;
import modelos.UsuarioDTO;

@Path("/usuarios")
public class UsuarioController {
	
	UsuarioDAOHibernate udao = new UsuarioDAOHibernate();
	
	//Listo todos los usuarios
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsuarioDTO> listar(){
		List<Usuario> usuarios = udao.listar();
		List<UsuarioDTO> usuariosReturn = new ArrayList<UsuarioDTO>();
		for(int i = 0; i < usuarios.size(); i++) {
			UsuarioDTO u = new UsuarioDTO();
			u.setId(usuarios.get(i).getId());
			u.setNombreUsuario(usuarios.get(i).getNombreUsuario());
			u.setApeynom(usuarios.get(i).getApeynom());
			u.setDni(usuarios.get(i).getDni());
			u.setDomicilio(usuarios.get(i).getDomicilio());
			u.setFechaNacimiento(usuarios.get(i).getFechaNacimiento());
			u.setMail(usuarios.get(i).getMail());
			u.setHabilitado(usuarios.get(i).getHabilitado());
			usuariosReturn.add(u);
		}
		if (usuarios.isEmpty()) {
			return null;
		}else {
			return usuariosReturn;
		}
	}
	
	//Obtengo un usuario 
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioDTO obtenerUsuario(@PathParam("id") long id) {
		Usuario usu= udao.encontrarPorId((int) id); 
		if (usu == null) {
			return null;
		}else {
			UsuarioDTO u = new UsuarioDTO();
			u.setApeynom(usu.getApeynom());
			u.setDni(usu.getDni());
			u.setDomicilio(usu.getDomicilio());
			u.setFechaNacimiento(usu.getFechaNacimiento());
			u.setHabilitado(usu.getHabilitado());
			u.setMail(usu.getMail());
			u.setNombreUsuario(usu.getNombreUsuario());
			u.setSexo(usu.getSexo());
			u.setId(usu.getId());
			u.setPass(usu.getPass());
			return u;
		}
	}
	
//	Actualizado un usuario dado mediante el id
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editarUsuario(@PathParam("id") int id, Usuario usuario){
		 Usuario u = udao.encontrarPorId(id);
		 if (u != null) {
				 u.setNombreUsuario(usuario.getNombreUsuario());
				 u.setApeynom(usuario.getApeynom());
				 u.setDni(usuario.getDni());
				 u.setDomicilio(usuario.getDomicilio());
				 u.setFechaNacimiento(usuario.getFechaNacimiento());
				 u.setSexo(usuario.getSexo());
				 u.setPass(usuario.getPass());
				 u.setHabilitado(usuario.getHabilitado());
				 udao.actualizar(u);
				 return Response.ok().entity(usuario).build();
		 } else {
			 return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		 }
	}
	
	
//	Creo un usuario
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Usuario usuario){
		if(udao.existeUsuario(usuario.getMail())) {
			return Response.status(Response.Status.CONFLICT).entity("Usuario existe").build();
		}else {
			usuario.setPass(UUID.randomUUID().toString().substring(0,8));
			udao.persistir(usuario);
			return Response.status(Response.Status.OK).entity(usuario.getPass()).build();
		}
	 }
	
//	Elimino un usuario
	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Integer id){
		Usuario aux = udao.encontrarPorId(id);
		if (aux != null){
			udao.borrar(aux);
			return Response.noContent().build();
		} else {
			String mensaje = "No existe el usuario con ese id";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
	
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public int loginUsuario(@QueryParam("mail") String mail, @QueryParam("pass") String pass) {
		Usuario result= udao.loginUsuario(mail, pass);
		if (result == null) {
			return 0;
   		}else {
   			if (result.getHabilitado() == true) {    
   				return (int) result.getId();
   			} else {
   				return 999;
   			}
   		}
	}
	

	
	
}
