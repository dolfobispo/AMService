package br.com.pp.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_arquiteto_projeto")
public class ArquitetoProjetoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="id_pessoa")
	private Integer idPessoa;
	@Column(name="id_projeto")
	private Integer idProjeto;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	public Integer getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(Integer idProjeto) {
		this.idProjeto = idProjeto;
	}
}
