package br.com.pp.dominio;

import java.util.List;

public class Projeto {
	private int id;
	private String nome;
	private String descricao;
	private List<Planta> plantas;
	public List<Planta> getPlantas() {
		return plantas;
	}
	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public Projeto(){
		
	}
	public Projeto(int id, String nome, String descricao,List<Planta> plantas) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.plantas=plantas;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
