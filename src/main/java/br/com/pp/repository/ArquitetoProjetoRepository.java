package br.com.pp.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pp.repository.entity.ArquitetoProjetoEntity;
import br.com.pp.repository.entity.ManagerRepository;
import br.com.pp.repository.entity.ProjetoEntity;

public class ArquitetoProjetoRepository {
private final EntityManager entityManager;
	
	public ArquitetoProjetoRepository(){
		
		entityManager= ManagerRepository.getInstance();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public void salvar(ArquitetoProjetoEntity arquitetoProjetoEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(arquitetoProjetoEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void alterar(ArquitetoProjetoEntity arquitetoProjetoEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(arquitetoProjetoEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * RETORNA TODAS AS PESSOAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<ArquitetoProjetoEntity> todosArquitetosProjetos(){
		
		return this.entityManager.createQuery("SELECT p FROM ArquitetoProjetosEntity p").getResultList();
	}
	
	/**
	 * CONSULTA UMA PESSOA CADASTRA PELO CÓDIGO
	 * */
	public ArquitetoProjetoEntity getArquitetoProjetos(Integer id){
		
		return this.entityManager.find(ArquitetoProjetoEntity.class, id);
	}
	
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void excluir(Integer id){
		
		ArquitetoProjetoEntity arquitetoProjeto = this.getArquitetoProjetos(id);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(arquitetoProjeto);
		this.entityManager.getTransaction().commit();
		
	}
}
