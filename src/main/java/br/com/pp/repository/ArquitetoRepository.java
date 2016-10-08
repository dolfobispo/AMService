package br.com.pp.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pp.repository.entity.ArquitetoEntity;
import br.com.pp.repository.entity.ManagerRepository;
import br.com.pp.repository.entity.ProjetoEntity;

public class ArquitetoRepository {
private final EntityManager entityManager;
	
	public ArquitetoRepository(){
		
		entityManager= ManagerRepository.getInstance();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public void salvar(ArquitetoEntity arquitetoEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(arquitetoEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void alterar(ArquitetoEntity arquitetoEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(arquitetoEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * RETORNA TODAS AS PESSOAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<ArquitetoEntity> todosArquitetos(){
		
		return this.entityManager.createQuery("SELECT p FROM ArquitetoEntity p").getResultList();
	}
	
	/**
	 * CONSULTA UMA PESSOA CADASTRA PELO CÓDIGO
	 * */
	public ArquitetoEntity getPessoa(Integer id){
		
		return this.entityManager.find(ArquitetoEntity.class, id);
	}
	
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void excluir(Integer id){
		
		ArquitetoEntity arquiteto = this.getPessoa(id);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(arquiteto);
		this.entityManager.getTransaction().commit();
		
	}

}
