package model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cpfCnpj;
	
	@Column(nullable = false)
	private String ramoAtuacao;
	
	public Fornecedor() {
		super();
	}
	
	public Fornecedor(String nome, String cpfCnpj, String ramoAtuacao) {
		super();
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.ramoAtuacao = ramoAtuacao;
	}
	
	public Fornecedor(int id, String nome, String cpfCnpj, String ramoAtuacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.ramoAtuacao = ramoAtuacao;
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
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getRamoAtuacao() {
		return ramoAtuacao;
	}
	public void setRamoAtuacao(String ramoAtuacao) {
		this.ramoAtuacao = ramoAtuacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpfCnpj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(cpfCnpj, other.cpfCnpj);
	}
	
	
}
