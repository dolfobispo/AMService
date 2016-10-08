package br.com.pp.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pp.repository.entity.ManagerRepository;
import br.com.pp.repository.entity.ProjetoEntity;

public class ProjetoRepository {
	private final EntityManager entityManager;
	
	public ProjetoRepository(){
		
		entityManager= ManagerRepository.getInstance();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public ProjetoEntity cadastrar(ProjetoEntity projetoEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(projetoEntity);
		this.entityManager.getTransaction().commit();
		return projetoEntity;
	}
	
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void alterar(ProjetoEntity projetoEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(projetoEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * RETORNA TODAS AS PESSOAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<ProjetoEntity> todosProjetos(){
		
		return this.entityManager.createQuery("SELECT p FROM UsuarioEntity p").getResultList();
	}
	
	/**
	 * CONSULTA UMA PESSOA CADASTRA PELO CÓDIGO
	 * */
	public ProjetoEntity getProjetoById(Integer id){
		
		return this.entityManager.find(ProjetoEntity.class, id);
	}
	
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void excluir(Integer id){
		
		ProjetoEntity projeto = this.getProjetoById(id);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(projeto);
		this.entityManager.getTransaction().commit();
		
	}
}
