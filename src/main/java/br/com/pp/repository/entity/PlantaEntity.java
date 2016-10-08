package br.com.pp.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tb_arquiteto")
public class PlantaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="imagem")
	private String imagem;
	@Column(name="justificativa")
	private String justificativa;
	@Column(name="aprovacao")
	private String aprovacao;
	@Column(name="id_projeto")
	private int idProjeto;
	public int getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getAprovacao() {
		return aprovacao;
	}
	public void setAprovacao(String aprovacao) {
		this.aprovacao = aprovacao;
	}
	
}
