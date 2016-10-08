package br.com.pp.repository.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class ManagerRepository {
	private static ManagerRepository instancia;
	private static EntityManagerFactory entityManagerFactory;
	private ManagerRepository(){
		
	}
	public static synchronized EntityManager  getInstance(){
		if (instancia!=null ){
			instancia = new ManagerRepository();
			
		}
		entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_estudo");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		return entityManager;
	}
}
