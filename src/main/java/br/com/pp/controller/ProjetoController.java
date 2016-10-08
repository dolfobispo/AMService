package br.com.pp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import br.com.pp.dominio.Pessoa;
import br.com.pp.dominio.Planta;
import br.com.pp.dominio.Projeto;
import br.com.pp.dominio.Usuario;
import br.com.pp.repository.PlantaRepository;
import br.com.pp.repository.ProjetoRepository;
import br.com.pp.repository.UsuarioProjetoRepository;
import br.com.pp.repository.UsuarioRepository;
import br.com.pp.repository.entity.PlantaEntity;
import br.com.pp.repository.entity.ProjetoEntity;
import br.com.pp.repository.entity.UsuarioEntity;
import br.com.pp.repository.entity.UsuarioProjetoEntity;
@Path("/projeto")
public class ProjetoController {
	
	private final ProjetoRepository projetoRepository = new ProjetoRepository();
	private final UsuarioProjetoRepository usuarioProjetoRepository = new UsuarioProjetoRepository();
	private final PlantaRepository plantaRepository=new PlantaRepository();

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
	public  Projeto cadastrar(Usuario usuario) throws Exception{
		Projeto projeto=usuario.getProjetos().get(0);
		Planta planta =usuario.getProjetos().get(0).getPlantas().get(0);
		UsuarioProjetoEntity usuarioProjetoEntity=new UsuarioProjetoEntity();
		ProjetoEntity projetoEntity = new ProjetoEntity();
		//usuEntity.setId(usuario.getId());
		projetoEntity.setNome(projeto.getNome());
		projetoEntity.setDescricao(projeto.getDescricao());
				
		try {
			
			
			ProjetoEntity pjEntity= projetoRepository.cadastrar(projetoEntity);//cadastra projeto
			if(pjEntity!=null){
				
				usuarioProjetoEntity.setIdProjeto(pjEntity.getId());
				usuarioProjetoEntity.setIdUsuario(usuario.getId());
				usuarioProjetoRepository.cadastrar(usuarioProjetoEntity);//cadastra o projeto e o usuario na tabela usuario_projeto
				PlantaEntity plantaEntity=new PlantaEntity();
				plantaEntity.setAprovacao(planta.getAprovacao());
				plantaEntity.setImagem(planta.getImagem());
				plantaEntity.setJustificativa(planta.getJustificativa());
				plantaEntity.setIdProjeto(pjEntity.getId());
				PlantaEntity planEntity=plantaRepository.cadastrar(plantaEntity);//cadastra a planta 
				List<Planta> plantaList=new ArrayList<Planta>();
				Planta plantaRetorno=new Planta();
				plantaRetorno.setId(planEntity.getId());
				plantaRetorno.setIdProjeto(planta.getIdProjeto());
				plantaRetorno.setImagem(planEntity.getImagem());
				plantaRetorno.setJustificativa(planEntity.getJustificativa());
				plantaList.add(plantaRetorno);
				
				return new Projeto(pjEntity.getId(),pjEntity.getNome(),pjEntity.getDescricao(),plantaList);//retorna o projeto completo com tudo que foi cadastrado no método.
			}
			
			
		} catch (Exception e) {
			
			 throw new Exception("ops, um erro aconteceu.");
		}
		return null;
	}
	@PUT
	@Consumes("application/json; charset=UTF-8")
	@Path("/alterar")
	public void alterar(Projeto projeto){
		ProjetoEntity projetoEntity = new ProjetoEntity();
		projetoEntity.setId(projeto.getId());
		projetoEntity.setNome(projeto.getNome());
		projetoEntity.setDescricao(projeto.getDescricao());
		projetoRepository.alterar(projetoEntity);
	}
}
