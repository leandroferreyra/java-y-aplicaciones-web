package DAO;


import modelos.Actividad;

public class ActividadDAOHibernate extends GenericDAOHibernate<Actividad> implements ActividadDAO {

	public ActividadDAOHibernate() {
		super(Actividad.class);
	}
}
