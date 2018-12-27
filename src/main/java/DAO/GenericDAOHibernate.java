package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelos.Ruta;
import modelos.Usuario;


public class GenericDAOHibernate<T> implements GenericDAO<T> {

	private Class<T> PersistentClass;
	
	public GenericDAOHibernate(Class<T> entidad) {
		this.PersistentClass = entidad;
	}
	
	public Class<T> getPersistentClass() {
		return PersistentClass;
	}
	
	public void setPersistentClass(Class<T> persistentClass) {
		PersistentClass = persistentClass;
	}
	
	@PersistenceContext
	private EntityTransaction etx;
	
		
	 @Override
	public T persistir(T entity) {
				EntityManager em = EMF.getEMF().createEntityManager();
				try {
					etx = em.getTransaction();
					etx.begin();
					em.persist(entity);
					etx.commit();
				}
				catch (RuntimeException e)		{
					if ( etx != null && etx.isActive() ) etx.rollback();
					throw e; // escribir en un log o mostrar un mensaje
				} 
				finally {
					em.close();
				}
				return entity;
			}

	@Override
	public void borrar(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		try {
			etx = em.getTransaction();
			etx.begin();
//			em.remove(entity);
			em.remove(em.contains(entity) ? entity : em.merge(entity));
			etx.commit();
		} catch (RuntimeException e) {
				if ( etx != null && etx.isActive() ) etx.rollback();
			throw e; // escribir en un log o mostrar un mensaje
		} 
		finally {
			em.close();
		}
	}
	
	@Override
	public List<T> recuperarTodosOrdenados(String columnOrder) {
		EntityManager em = EMF.getEMF().createEntityManager();
		Query consulta= em.createQuery("from "+ this.getPersistentClass().getSimpleName()+" order by "+columnOrder);
		List<T> resultado = (List<T>)consulta.getResultList();
		return resultado;	
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public void borrarTodos() {
		EntityManager em = EMF.getEMF().createEntityManager();
		Query consulta = em.createQuery("FROM "+this.getPersistentClass().getSimpleName());
		List <T> lista = consulta.getResultList();
		for (T ent : lista) {
			this.borrar(ent);
		}
	}	

	@Override
	public T actualizar(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		entity = em.merge(entity); // Borr� la T de adelante porque me tiraba error
		etx.commit();
		em.close();
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listar() {
			EntityManager em = EMF.getEMF().createEntityManager();
			return em.createQuery("from "+ this.getPersistentClass().getSimpleName()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> listarRutasPorUsuario(long id) {
			EntityManager em = EMF.getEMF().createEntityManager();
//			Query consulta= em.createQuery("from "+ this.getPersistentClass().getSimpleName() + " where usuarioCreador= :id",Ruta.class);
			Query consulta= em.createQuery("from "+ this.getPersistentClass().getSimpleName() + " where usuarioCreador= :id");
			consulta.setParameter("id", em.getReference(Usuario.class, id));
			List<T> resultado = consulta.getResultList();
			if (resultado.isEmpty()) {
				return null;
			}else {
				return resultado;
			}
	}
	
	// Se pasa el id del usuario que pidio listar las rutas publicas ajenas.
	@SuppressWarnings("unchecked")
	@Override
	public List<T> listarRutasPublicas(long id) {
			EntityManager em = EMF.getEMF().createEntityManager();
//			Query consulta= em.createQuery("from "+ this.getPersistentClass().getSimpleName() + " where usuarioCreador <> :id",Ruta.class);
			Query consulta= em.createQuery("from "+ this.getPersistentClass().getSimpleName() + " where usuarioCreador <> :id");
			consulta.setParameter("id", em.getReference(Usuario.class, id));
			List<T> resultado = consulta.getResultList();
			if (resultado.isEmpty()) {
				return null;
			}else {
				return resultado;
			}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T encontrarPorMail(String mail) {
		EntityManager em = EMF.getEMF().createEntityManager();
		Query consulta= em.createQuery("from " + this.getPersistentClass().getSimpleName() + " where mail= :mail");
		List<T> resultado = consulta.getResultList();
		if (resultado.isEmpty()) {
			return null;
		}else {
			return (T) resultado.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T encontrarPorId(long id) {
		EntityManager em = EMF.getEMF().createEntityManager();
//		Query consulta= em.createQuery("from " + this.getPersistentClass().getSimpleName() + " where id_persona="+id);
		Query consulta= em.createQuery("from " + this.getPersistentClass().getSimpleName() + " where id= :idEntidad");
		consulta.setParameter("idEntidad", id);
		List<T> resultado = consulta.getResultList();
		if (resultado.isEmpty()) {
			return null;
		}else {
			return (T) resultado.get(0);
		}
	}
	
	@Override
	public boolean existeUsuario(String mail) {
		EntityManager em = EMF.getEMF().createEntityManager();
		Query consulta= em.createQuery("from " + this.getPersistentClass().getSimpleName() + " where mail= :mail");
		consulta.setParameter("mail", mail);
		if (consulta.getResultList().size() == 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean existeActividad(String nombre) {
		EntityManager em = EMF.getEMF().createEntityManager();
		Query consulta = em.createQuery("from "+this.getPersistentClass().getSimpleName()+" where nombreActividad = :nombre");
		consulta.setParameter("nombre", nombre);
		if (consulta.getResultList().size() == 0) {
			return false;
		}else {
			return true;
		}
	}	
	
	@Override
	public boolean existeRuta(String nombreRuta) {
		EntityManager em = EMF.getEMF().createEntityManager();
		Query consulta = em.createQuery("from "+this.getPersistentClass().getSimpleName()+" where nombreRuta LIKE " + "'"+nombreRuta+"'");
//		consulta.setParameter("nombreRuta", nombreRuta);
		if (consulta.getResultList().size() == 0 ) {
			return false;
		}else {
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T loginUsuario(String mail, String pass) {
		EntityManager em = EMF.getEMF().createEntityManager();
		Query consulta= em.createQuery("from "+ this.getPersistentClass().getSimpleName() + " where mail= :mail and pass = :pass");
		consulta.setParameter("mail", mail);
		consulta.setParameter("pass", pass);
		List<T> result = consulta.getResultList();
		if (result.isEmpty()) {
			return null;
		}else {
			return result.get(0);
		}
//		T resultado =  (T) consulta.getSingleResult();
//		return resultado;
}
	
		
			
}
