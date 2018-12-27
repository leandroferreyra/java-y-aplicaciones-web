package DAO;

import modelos.Nota;

public class NotaDAOHibernate extends GenericDAOHibernate<Nota> implements NotaDAO {
	
	public NotaDAOHibernate() {
		super(Nota.class);
	}

}
