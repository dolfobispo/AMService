package br.com.pp.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pp.dominio.Usuario;
import br.com.pp.repository.entity.ManagerRepository;
import br.com.pp.repository.entity.UsuarioProjetoEntity;

public class UsuarioProjetoRepository {
private final EntityManager entityManager;
	
	public UsuarioProjetoRepository(){
		
		entityManager= ManagerRepository.getInstance();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public void cadastrar(UsuarioProjetoEntity usuarioProjetoEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(usuarioProjetoEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void alterar(UsuarioProjetoEntity usuarioProjetoEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(usuarioProjetoEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * RETORNA TODAS AS PESSOAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<UsuarioProjetoEntity> todosUsuariosProjetos(Usuario usuario){
		
		return this.entityManager.createQuery("FROM UsuarioProjetoEntity p where p.id_usuario = :id").setParameter("id",usuario.getId()).getResultList();
	}
	
	/**
	 * CONSULTA UMA PESSOA CADASTRA PELO CÓDIGO
	 * */
	public UsuarioProjetoEntity getUsuarioProjeto(Integer id){
		
		return this.entityManager.find(UsuarioProjetoEntity.class, id);
	}
	
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void excluir(Integer id){
		
		UsuarioProjetoEntity usuarioProjeto = this.getUsuarioProjeto(id);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(usuarioProjeto);
		this.entityManager.getTransaction().commit();
		
	}
}
