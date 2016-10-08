package br.com.pp.dominio;

import java.util.List;

public class Usuario {
	private int id;
	private String email;
	private String senha;
	private Pessoa pessoa;
	private List<Projeto> projetos;
	public List<Projeto> getProjetos() {
		return projetos;
	}
	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	public Usuario(){
		
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario(int id, String email, String senha, Pessoa pessoa) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.pessoa = pessoa;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}
