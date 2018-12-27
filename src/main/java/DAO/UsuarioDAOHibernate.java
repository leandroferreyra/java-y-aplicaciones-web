package DAO;


import modelos.Usuario;

public class UsuarioDAOHibernate extends GenericDAOHibernate<Usuario> implements UsuarioDAO {

	public UsuarioDAOHibernate() {
		super(Usuario.class);
	}

}
