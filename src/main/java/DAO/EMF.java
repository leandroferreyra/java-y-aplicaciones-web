package DAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static final EntityManagerFactory em =  Persistence.createEntityManagerFactory("Persistencia2018");
	
	public static EntityManagerFactory getEMF() {
		return em;
	}
	
}

