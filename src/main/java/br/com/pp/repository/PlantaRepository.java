package br.com.pp.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pp.dominio.Usuario;
import br.com.pp.repository.entity.ManagerRepository;
import br.com.pp.repository.entity.PlantaEntity;


public class PlantaRepository {
private final EntityManager entityManager;
	
	public PlantaRepository(){
		
		entityManager= ManagerRepository.getInstance();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public PlantaEntity cadastrar(PlantaEntity plantaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(plantaEntity);
		this.entityManager.getTransaction().commit();
		return plantaEntity;
	}
	
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void alterar(PlantaEntity plantaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(plantaEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * RETORNA TODAS AS PESSOAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<PlantaEntity> todasPlantas(Usuario usuario){
		
		return this.entityManager.createQuery("SELECT p FROM PlantaEntity p where =:id").setParameter("id",usuario.getId()).getResultList();
	}
	
	/**
	 * CONSULTA UMA PESSOA CADASTRA PELO CÓDIGO
	 * */
	public PlantaEntity getPlanta(Integer id){
		
		return this.entityManager.find(PlantaEntity.class, id);
	}
	
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void excluir(Integer id){
		
		PlantaEntity planta = this.getPlanta(id);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(planta);
		this.entityManager.getTransaction().commit();
		
	}
}
