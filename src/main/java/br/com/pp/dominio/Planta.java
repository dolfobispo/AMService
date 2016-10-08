package br.com.pp.dominio;

public class Planta {
	private int id;
	private String imagem;
	private String aprovacao;
	public String getAprovacao() {
		return aprovacao;
	}
	public void setAprovacao(String aprovacao) {
		this.aprovacao = aprovacao;
	}
	private String justificativa;
	private Projeto idProjeto;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public Projeto getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(Projeto idProjeto) {
		this.idProjeto = idProjeto;
	}
}
