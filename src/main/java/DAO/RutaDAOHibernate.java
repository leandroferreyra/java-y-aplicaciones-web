package DAO;


import modelos.Ruta;

public class RutaDAOHibernate extends GenericDAOHibernate<Ruta> implements RutaDAO{

	public RutaDAOHibernate() {
		super(Ruta.class);
	}
}
