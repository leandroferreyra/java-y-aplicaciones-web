package DAO;


import modelos.Administrador;

public class AdministradorDAOHibernate extends GenericDAOHibernate<Administrador> implements AdministradorDAO {
	
	public AdministradorDAOHibernate() {
		super(Administrador.class);
	}

}
