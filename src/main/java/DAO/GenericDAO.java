package DAO;

import java.util.List;



public interface GenericDAO<T> {


		public void borrar(T entity);
		public void borrarTodos();
		public T actualizar(T entity);
		public T persistir(T entity);
		public List<T> listar();
		public List<T> recuperarTodosOrdenados(String columnOrder);
		public T encontrarPorId(long id);
		public T encontrarPorMail(String mail);
		public boolean existeUsuario(String email);
		public boolean existeActividad(String nombre);
		boolean existeRuta(String nombreRuta);
		public T loginUsuario(String mail, String pass);
		public List<T> listarRutasPorUsuario(long id);
		public List<T> listarRutasPublicas(long id);
		
		
}
