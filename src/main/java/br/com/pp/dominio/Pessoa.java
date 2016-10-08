package br.com.pp.dominio;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pessoa {

	private int id;
	private String nome;
	private String sexo;
	private String telefone;
	
	public Pessoa(){
		
	}
	
	public Pessoa(int codigo, String nome, String sexo,String telefone) {
		super();
		this.id = codigo;
		this.nome = nome;
		this.sexo = sexo;
		this.telefone=telefone;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	public String getSexo(){
		return sexo;
	}
	public void setSexo(String sexo){
		this.sexo =  sexo;
	}
	

}
