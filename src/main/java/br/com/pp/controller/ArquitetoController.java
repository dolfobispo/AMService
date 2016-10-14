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
import br.com.pp.dominio.Planta;
import br.com.pp.dominio.Projeto;
import br.com.pp.dominio.Usuario;
import br.com.pp.repository.ArquitetoProjetoRepository;
import br.com.pp.repository.ArquitetoRepository;
import br.com.pp.repository.PessoaRepository;
import br.com.pp.repository.PlantaRepository;
import br.com.pp.repository.ProjetoRepository;
import br.com.pp.repository.UsuarioProjetoRepository;
import br.com.pp.repository.UsuarioRepository;
import br.com.pp.repository.entity.ArquitetoEntity;
import br.com.pp.repository.entity.ArquitetoProjetoEntity;
import br.com.pp.repository.entity.PessoaEntity;
import br.com.pp.repository.entity.PlantaEntity;
import br.com.pp.repository.entity.ProjetoEntity;
import br.com.pp.repository.entity.UsuarioEntity;
import br.com.pp.repository.entity.UsuarioProjetoEntity;

@Path("/arquiteto")
public class ArquitetoController {
	private final  PessoaRepository repository = new PessoaRepository();
	private final UsuarioRepository usuarioRepository = new UsuarioRepository();
	private final ArquitetoRepository arquitetoRepository = new ArquitetoRepository();
	private final ProjetoRepository projetoRepository= new ProjetoRepository();
	private final PlantaRepository plantaRepository = new PlantaRepository();
	/**
	 * método que implementa cadastra um arquiteto.A unica diferença entre um usuario
	 * e um arquiteto é que o id do usuario também tem que está na tabela arquiteto.
	 * @param usuario
	 * @return Usuario
	 * @throws Exception
	 */
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
			ArquitetoEntity arquitetoEntity = new ArquitetoEntity();
			arquitetoEntity.setIdUsuario(usuario.getId());
			arquitetoRepository.cadastrar(arquitetoEntity);
			return new Usuario(usuEntity.getId(), usuarioEntity.getEmail(),
					usuarioEntity.getSenha(),p);
			
		} catch (Exception e) {
			
			throw new Exception(""Nao foi possivel cadastrar usuario,e);
		}
		
	
	}
	
	/**
	 * Essse método altera um usuario já cadastrado.
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
	 * Esse método lista todos os usuarios cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todosArquitetos")
	public List<Usuario> todosUsuarios(){
		
		List<Usuario> usuarios =  new ArrayList<Usuario>();
		
		List<ArquitetoEntity> listaEntityUsuarios = arquitetoRepository.todosArquitetos();
		
		for (ArquitetoEntity entity : listaEntityUsuarios) {
			UsuarioEntity us=usuarioRepository.getUsuarioById(entity.getIdUsuario());
			PessoaEntity pEntity=repository.getPessoaById(us.getIdPessoa());
			Pessoa pessoa= new Pessoa(pEntity.getId(),pEntity.getNome(),pEntity.getSexo(),pEntity.getTelefone());
			
			
			usuarios.add(new Usuario(us.getId(),us.getEmail(),us.getSenha(),pessoa));
		}
		
		return usuarios;
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
	@POST
	@Produces("application/json; charset=UTF-8")
	@Path("/buscarProjetos")
	public List<Projeto>buscarProjetos(Usuario usuario){
	ArquitetoProjetoRepository arquitetoProjetoRepository = new ArquitetoProjetoRepository();
		List<ArquitetoProjetoEntity> listUsuProj= arquitetoProjetoRepository.todosArquitetosProjetos(usuario);
		List<PlantaEntity> plantaRepos = plantaRepository.todasPlantas(usuario);
		List<Projeto> projetos = new ArrayList<Projeto>();
		for (ArquitetoProjetoEntity usuarioProjetoEntity : listUsuProj) {
			List<Planta>plantas = new ArrayList<Planta>();
			ProjetoEntity p=projetoRepository.getProjetoById(usuarioProjetoEntity.getIdProjeto());
			if(plantaRepos!=null){
				for (PlantaEntity plantaEntity : plantaRepos) {
					Planta planta = new Planta();
					planta.setId(plantaEntity.getId());
					planta.setIdProjeto(plantaEntity.getIdProjeto());
					planta.setAprovacao(plantaEntity.getAprovacao());
					planta.setImagem(plantaEntity.getImagem());
					planta.setJustificativa(plantaEntity.getJustificativa());
					plantas.add(planta);
				}
			}
			
			
			Projeto projeto = new Projeto();
			
			
			projeto.setId(p.getId());
			projeto.setNome(p.getNome());
			projeto.setDescricao(p.getDescricao());
			
			projeto.setPlantas(plantas);
			
			
			projetos.add(projeto);
		}
		return projetos;
	}
	
	/**
	 * Esse método busca um usuario cadastrado pelo id
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getArquitetoById/{id}")
	public Usuario getArquitetoById(@PathParam("id") Integer id){
		
		UsuarioEntity entity = usuarioRepository.getUsuarioById(id);
		PessoaEntity p = repository.getPessoaById(entity.getId());
		Pessoa pessoa = new Pessoa();
		pessoa.setId(p.getId());
		pessoa.setNome(p.getNome());
		pessoa.setSexo(pessoa.getSexo());
		pessoa.setTelefone(pessoa.getTelefone());
		
		if(entity != null){
			return new Usuario(entity.getId(), entity.getEmail(),entity.getSenha(), pessoa);
		}
		return null;
	}
	/**
	 * Excluindo um usuario pelo código
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
