package br.com.pp.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pp.repository.entity.ManagerRepository;
import br.com.pp.repository.entity.PessoaEntity;



public class PessoaRepository {

	
	
	private final EntityManager entityManager;
	
	public PessoaRepository(){
		
		entityManager= ManagerRepository.getInstance();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public PessoaEntity cadastrar(PessoaEntity pessoaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(pessoaEntity);
		this.entityManager.getTransaction().commit();
		return pessoaEntity;
	}
	
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void alterar(PessoaEntity pessoaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(pessoaEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * RETORNA TODAS AS PESSOAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<PessoaEntity> todasPessoas(){
		
		return this.entityManager.createQuery("SELECT p FROM PessoaEntity p ORDER BY p.nome").getResultList();
	}
	
	/**
	 * CONSULTA UMA PESSOA CADASTRA PELO CÓDIGO
	 * */
	public PessoaEntity getPessoaById(Integer id){
		
		return this.entityManager.find(PessoaEntity.class, id);
	}
	@SuppressWarnings("unchecked")
	public PessoaEntity getPessoa(PessoaEntity pessoaEntity){
		List<PessoaEntity>temp= this.entityManager.createQuery("SELECT p FROM PessoaEntity p where p.nome = :nome and p.telefone = :telefone and p.sexo= :sexo")
				.setParameter("nome",pessoaEntity.getNome()).
				setParameter("telefone",pessoaEntity.getTelefone())
				.setParameter("sexo",pessoaEntity.getSexo()).getResultList();
		if(!temp.isEmpty()){
			return temp.get(0);
			
		}
		return null;

	}
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void excluir(Integer id){
		
		PessoaEntity pessoa = this.getPessoaById(id);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(pessoa);
		this.entityManager.getTransaction().commit();
		
	}
}
