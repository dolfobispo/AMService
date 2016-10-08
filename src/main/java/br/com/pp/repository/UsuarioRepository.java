package br.com.pp.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.pp.repository.entity.ManagerRepository;
import br.com.pp.repository.entity.UsuarioEntity;

public class UsuarioRepository {
	private final EntityManager entityManager;
	
	public UsuarioRepository(){
		
		entityManager= ManagerRepository.getInstance();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public UsuarioEntity cadastrar(UsuarioEntity usuarioEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(usuarioEntity);
		this.entityManager.getTransaction().commit();
		return usuarioEntity;
	}
	
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void alterar(UsuarioEntity usuarioEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(usuarioEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * RETORNA TODAS AS PESSOAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> todosUsuarios(){
		
		return this.entityManager.createQuery("SELECT p FROM UsuarioEntity p").getResultList();
	}
	
	/**
	 * CONSULTA UMA PESSOA CADASTRA PELO CÓDIGO
	 * */
	public UsuarioEntity getUsuarioById(Integer id){
		
		return this.entityManager.find(UsuarioEntity.class, id);
	
	}
	@SuppressWarnings("unchecked")
	public UsuarioEntity getUsuario(UsuarioEntity usuario){
		List<UsuarioEntity> temp = this.entityManager.createQuery("SELECT p FROM UsuarioEntity p where p.email = :email").setParameter("email",usuario.getEmail()).getResultList();
		if(!temp.isEmpty()){
			return temp.get(0);
		}
		return null;
		
	}
	@SuppressWarnings("unchecked")
	public UsuarioEntity logar(UsuarioEntity usuario){
		List<UsuarioEntity> temp = this.entityManager.createQuery("SELECT p FROM UsuarioEntity p where p.email = :email and p.senha =:senha").setParameter("email",usuario.getEmail()).setParameter("senha",usuario.getSenha()).getResultList();
		if(!temp.isEmpty()){
			return temp.get(0);
		}
		return null;
		
	}
	
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void excluir(Integer id){
		
		UsuarioEntity usuario = this.getUsuarioById(id);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(usuario);
		this.entityManager.getTransaction().commit();
		
	}

}
