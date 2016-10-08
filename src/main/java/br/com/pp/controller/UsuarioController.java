package br.com.pp.controller;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.pp.dominio.Pessoa;
import br.com.pp.dominio.Usuario;
import br.com.pp.repository.PessoaRepository;
import br.com.pp.repository.UsuarioRepository;
import br.com.pp.repository.entity.PessoaEntity;
import br.com.pp.repository.entity.UsuarioEntity;


/**
 * Essa classe vai expor os nosso métodos para serem acessasdos via http
 * 
 * @Path - Caminho para a chamada da classe que vai representar o nosso serviço
 * */
@Path("/usuario")
public class UsuarioController {
		
	private final  PessoaRepository repository = new PessoaRepository();
	private final UsuarioRepository usuarioRepository = new UsuarioRepository();

	/**
	 * @throws Exception 
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra uma nova pessoa
	 * */
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public  Usuario cadastrar(Usuario usuario) throws Exception{
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		PessoaEntity pessoaEntity=new PessoaEntity();
		pessoaEntity.setNome(usuario.getPessoa().getNome());
		pessoaEntity.setSexo(usuario.getPessoa().getSexo());
		pessoaEntity.setTelefone(usuario.getPessoa().getTelefone());
				
		try {
			if(repository.getPessoa(pessoaEntity)!=null){
				throw new Exception("usuario já cadastrado");
				
			}
			PessoaEntity pessoaEnt=repository.cadastrar(pessoaEntity);
			Pessoa p = new Pessoa();
			p.setId(pessoaEnt.getId());
			p.setNome(pessoaEnt.getNome());
			p.setSexo(pessoaEnt.getSexo());
			p.setTelefone(pessoaEnt.getTelefone());
			usuarioEntity.setEmail(usuario.getEmail());
			usuarioEntity.setSenha(usuario.getSenha());
			usuarioEntity.setIdPessoa(p.getId());
			if(usuarioRepository.getUsuario(usuarioEntity)!=null){
				throw new Exception("usuario já cadastrado");
			}
			UsuarioEntity usuEntity=usuarioRepository.cadastrar(usuarioEntity);
			
			
			return new Usuario(usuEntity.getId(), usuarioEntity.getEmail(),
					usuarioEntity.getSenha(),p);
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
	
	}
	
	/**
	 * Essse método altera uma pessoa já cadastrada
	 * **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String alterar( Usuario usuario){
		
		PessoaEntity entity = new PessoaEntity();
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		
		try {
			entity.setId(usuario.getPessoa().getId());
			entity.setNome(usuario.getPessoa().getNome());
			entity.setSexo(usuario.getPessoa().getSexo());
			entity.setTelefone(usuario.getPessoa().getTelefone());
			repository.alterar(entity);
			usuarioEntity.setId(usuario.getId());
			usuarioEntity.setEmail(usuario.getEmail());
			usuarioEntity.setSenha(usuario.getSenha());
			usuarioEntity.setIdPessoa(usuario.getPessoa().getId());
			usuarioRepository.alterar(usuarioEntity);
			
			return "Registro alterado com sucesso!";
			
		} catch (Exception e) {
			
			return "Erro ao alterar o registro ";
		}

	}
	/**
	 * Esse método lista todas pessoas cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todosUsuarios")
	public List<Usuario> todosUsuarios(){
		
		List<Usuario> usuarios =  new ArrayList<Usuario>();
		
		List<UsuarioEntity> listaEntityUsuarios = usuarioRepository.todosUsuarios();
		
		for (UsuarioEntity entity : listaEntityUsuarios) {
			PessoaEntity pEntity=repository.getPessoaById(entity.getIdPessoa());
			Pessoa pessoa= new Pessoa(pEntity.getId(),pEntity.getNome(),pEntity.getSexo(),pEntity.getTelefone());
			
			
			usuarios.add(new Usuario(entity.getId(),entity.getEmail(),entity.getSenha(),pessoa));
		}
		
		return usuarios;
	}
	@POST
	@Produces("application/json; charset=UTF-8")
	@Path("/buscarUsuario")
	public Usuario buscarUsuario(Usuario usuario){
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		
		usuarioEntity.setEmail(usuario.getEmail());
		UsuarioEntity uEntity =usuarioRepository.getUsuario(usuarioEntity);
		PessoaEntity pEntity= repository.getPessoaById(uEntity.getId());
		Pessoa pessoa= new Pessoa(pEntity.getId(),pEntity.getNome(),pEntity.getSexo(),pEntity.getTelefone());
		System.out.println(uEntity.getId());
		
		Usuario usu= new Usuario(uEntity.getId(),uEntity.getEmail(),uEntity.getSenha(),pessoa);
		return usu;
	}
	@POST
	@Produces("application/json; charset=UTF-8")
	@Path("/logar")
	public Usuario logar(Usuario usuario){
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		
		usuarioEntity.setEmail(usuario.getEmail());
		usuarioEntity.setSenha(usuario.getSenha());
		UsuarioEntity uEntity =usuarioRepository.logar(usuarioEntity);
		PessoaEntity pEntity= repository.getPessoaById(uEntity.getIdPessoa());
		Pessoa pessoa= new Pessoa(pEntity.getId(),pEntity.getNome(),pEntity.getSexo(),pEntity.getTelefone());
		Usuario usu= new Usuario(uEntity.getId(),uEntity.getEmail(),uEntity.getSenha(),pessoa);
		return usu;
	}
	
	
	/**
	 * Esse método busca uma pessoa cadastrada pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getUsuarioById/{id}")
	public Pessoa getUsuarioById(@PathParam("id") Integer id){
		
		PessoaEntity entity = repository.getPessoaById(id);
		
		if(entity != null)
			return new Pessoa(entity.getId(), entity.getNome(),entity.getSexo(),entity.getTelefone());
		
		return null;
	}
	
	/**
	 * Excluindo uma pessoa pelo código
	 * */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{id}")	
	public String excluir(@PathParam("id") Integer id){
		
		try {
			
			repository.excluir(id);
			
			return "Registro excluido com sucesso!";
			
		} catch (Exception e) {
		
			return "Erro ao excluir o registro! " + e.getMessage();
		}
		
	}
	
}
