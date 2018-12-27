package DAO;


import modelos.Valoracion;

public class ValoracionDAOHibernate extends GenericDAOHibernate<Valoracion> implements ValoracionDAO {
	
	public ValoracionDAOHibernate() {
		super(Valoracion.class);
	}

}
